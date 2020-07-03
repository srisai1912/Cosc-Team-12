package com.example.myapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    Button btn;

    EditText email, password;
    private TextView Tv;
    private RequestQueue queue;
    JsonObjectRequest objectRequest;
    private static final String Key_Email = "EmailId";
    private static final String Key_Password = "Passw";
    private String emailid;
    private String passw;

    JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn = (Button) findViewById(R.id.button);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
                queue.add(objectRequest);
            }
        });


        Tv=(TextView)findViewById(R.id.textView10);
        Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(login.this, registration.class);
                startActivity(i3);
            }
        });
    }

    public void userLogin() {
        emailid = email.getText().toString().trim();
        passw = password.getText().toString().trim();
        String URL = "https://admintesting.herokuapp.com/userlogin";
        data = new JSONObject();
        try {
            data.put(Key_Email, emailid);
            data.put(Key_Password, passw);
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
                                homepage.accessTkn = response.getString("access_token");
                                homepage.eid = emailid;
                                opennext();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));


    }

    private void opennext() {
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }
}
