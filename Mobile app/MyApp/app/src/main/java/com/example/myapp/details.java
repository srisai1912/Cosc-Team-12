package com.example.myapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.text.TextUtils.isEmpty;


public class details extends AppCompatActivity {
    private Button button1;

    static String accessTkn;
    EditText roleid, prefsub, research;
    private RequestQueue queue;
    JsonObjectRequest objectRequest;
    static final String Key_roleid = "Roll_id";
    static final String Key_prefsub = "preferred_subj";
    static final String Key_email = "EmailId";
    static final String Key_research = "Research_details";
    private String role_id;
    static String eid;
    private String pref_sub;
    private String research_details;
    JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button1=(Button)findViewById(R.id.button1);
        roleid= (EditText) findViewById(R.id.roleid);

        prefsub= (EditText) findViewById(R.id.prefsubject);
        research= (EditText) findViewById(R.id.researchdet);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role_id= roleid.getText().toString().trim();
                pref_sub= prefsub.getText().toString().trim();
                research_details= research.getText().toString().trim();
                if(isEmpty(role_id)||isEmpty(pref_sub)||isEmpty(research_details))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Enter All Details", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                userDetails();
            }
        });

    }
    public void userDetails()  {

        String URL = "https://admintesting.herokuapp.com/appdetails";
        data = new JSONObject();
        try {
            data.put(Key_roleid,role_id);
            data.put(Key_email,eid);
            data.put(Key_prefsub,pref_sub);
            data.put(Key_research,research_details);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue = Volley.newRequestQueue(this);
        objectRequest = new JsonObjectRequest(Request.Method.POST,
                URL,
                data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast toast = Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_LONG);
                            toast.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast toast = Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
                        toast.show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+accessTkn);
                return params;
            }
        };

        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        queue.add(objectRequest);
    }
}


