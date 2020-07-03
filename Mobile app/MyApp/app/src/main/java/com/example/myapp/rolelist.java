package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class rolelist extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<carditem> cardItems;
    private Button applyButton;
    private RequestQueue queue;
    private static final String URL="https://admintesting.herokuapp.com/seevacantroles";
    static String accessTkn;
    static String eid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rolelist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List of Roles");
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardItems=new ArrayList<>();
        applyButton = (Button) findViewById(R.id.button1);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.accessTkn =accessTkn;
                details.eid=eid;
                Intent i1=new Intent(rolelist.this,details.class);
                startActivity(i1);

            }
        });

        StringRequest stringRequest= new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray array = new JSONArray(s);
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject j=array.getJSONObject(i);
                                carditem item =new carditem(
                                        j.getString("Role ID"),
                                        j.getString("Role"),
                                        j.getString("Prerequisites"),
                                        j.getString("Department")

                                );
                                cardItems.add(item);
                            }
                            adapter=new MyCardAdapter(cardItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (Exception e) {
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
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer "+accessTkn);
                return params;
            }
        };
        queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);


    }

}
