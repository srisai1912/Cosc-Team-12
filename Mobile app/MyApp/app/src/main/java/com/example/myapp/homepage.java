package com.example.myapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class homepage extends AppCompatActivity {
    private Button button1;
    private TextView tev1;
    private TextView tev2;
    static String accessTkn;
    private RequestQueue queue1;
    private RequestQueue queue;
    private final String CHANNEL_ID="STATUS UPDATE";
    private final int notificationId = 01;
    JsonObjectRequest objectRequest;
    static final String Key_Appid = "Application_id";
    private String app_id;
    JSONObject data;
    AlertDialog.Builder builder;
    static String eid;
    String a[]=new String[3];
    String b[]=new String[3];
    String c[]=new String[3];
    private static final String URL="https://admintesting.herokuapp.com/seestatus?EmailId=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        getSupportActionBar().setTitle("Homepage");
        builder = new AlertDialog.Builder(this);
        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(homepage.this, MainActivity.class);
                startActivity(i1);
            }
        });
        tev1 = (TextView) findViewById(R.id.textView1);
        tev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rolelist.accessTkn = accessTkn;
                rolelist.eid = eid;
                Intent i2 = new Intent(homepage.this, rolelist.class);
                startActivity(i2);

            }
        });
        tev2 = (TextView) findViewById(R.id.textView2);
        tev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.accessTkn = accessTkn;
                status.eid = eid;
                Intent i3 = new Intent(homepage.this, status.class);
                startActivity(i3);
            }
        });
        String url =URL+eid;
        StringRequest stringRequest= new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            final JSONArray array = new JSONArray(s);
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject j=array.getJSONObject(i);
                                c[i]=j.getString("Application_id1");
                                a[i]=j.getString("id_Status");
                                b[i]=j.getString("prev_status");
                            }
                            for(int k=0;k<array.length();k++)
                            {
                                if(a[k].equalsIgnoreCase(b[k])) {
                                    continue;
                                }else
                                {
                                    prev_updation(array.length());
                                    builder.setMessage("The Status of your Application has been updated.")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent i1 = new Intent(getApplicationContext(), homepage.class);
                                                    startActivity(i1);
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.setTitle("Status Update");
                                    alert.show();

                                  

                                break;
                                }
                            }
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
                });
        queue1 = Volley.newRequestQueue(this);
        queue1.add(stringRequest);
    }
    public void prev_updation(int i) {
        for(int k=0;k<i;k++) {
            app_id=c[k];
            String url1 = "https://admintesting.herokuapp.com/updatestatus";
            data = new JSONObject();
            try {
                data.put(Key_Appid, app_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            queue = Volley.newRequestQueue(this);
            objectRequest = new JsonObjectRequest(Request.Method.POST,
                    url1,
                    data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast toast = Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });
            objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));

            queue.add(objectRequest);
        }
    }



}