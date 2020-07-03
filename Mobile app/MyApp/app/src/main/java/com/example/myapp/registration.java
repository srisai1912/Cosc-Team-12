package com.example.myapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.text.TextUtils.isEmpty;

public class registration extends AppCompatActivity {
    private Button registerButton;
    private Button loginButton;
    public static String eid;
    RadioGroup gender;
    EditText firstname, lastname, email, password, aadhar, phone, college, deptqualified, qualification, cgpa, achievements, collegebatch, previousoffice, previousposition, yearsofservice, dob, currentaddress, permanentaddress;
    private RequestQueue queue;
    JsonObjectRequest objectRequest;
    static final String Key_firstname = "First_Name";
    static final String Key_lastname = "Last_Name";
    static final String Key_email = "EmailId";
    static final String Key_password = "Passw";
    static final String Key_aadhar = "Aadhar_Passport_No";
    static final String Key_phone = "Phone_No";
    static final String Key_college = "Graduated_College";
    static final String Key_deptqualified = "Dept_Qualified";
    static final String Key_qualification = "Qualification";
    static final String Key_cgpa = "CGPA";
    static final String Key_achievements = "achievements";
    static final String Key_collegebatch = "College_Batch";
    static final String Key_previousoffice = "Previous_office";
    static final String Key_previousposition = "previous_position";
    static final String Key_yearsofservice = "years_of_service";
    static final String Key_gender = "Gender";
    static final String Key_dob = "DOB";
    static final String Key_currentaddress = "Current_address";
    static final String Key_permanentaddress = "permanent_address";

    private String first_name;
    private String last_name;
    private String emailid;
    private String passw1;
    private String aadhar_passport_no;
    private String phone_no;
    private String graduated_college;
    private String dept_qualified;
    private String qualification1;
    private String cgpa1;
    private String achievements1;
    private String college_batch;
    private String previous_office;
    private String previous_position1;
    private String years_of_service1;
    private String gender1;
    private String dob1;
    private String current_address;
    private String permanent_address1;
    JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstname = (EditText) findViewById(R.id.firstName);
        lastname = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.emailID);
        password = (EditText) findViewById(R.id.password);
        aadhar = (EditText) findViewById(R.id.aadharNumber);
        phone = (EditText) findViewById(R.id.phoneNumber);
        college = (EditText) findViewById(R.id.collegeName);
        deptqualified = (EditText) findViewById(R.id.deptqual);
        qualification = (EditText) findViewById(R.id.qualifications);
        cgpa = (EditText) findViewById(R.id.cgpa);
        achievements = (EditText) findViewById(R.id.achievements);
        collegebatch = (EditText) findViewById(R.id.collegeBatch);
        previousoffice = (EditText) findViewById(R.id.previousOffice);
        previousposition = (EditText) findViewById(R.id.previousPosition);
        yearsofservice = (EditText) findViewById(R.id.experience);
        gender = (RadioGroup) findViewById(R.id.radioGroup);
        dob = (EditText) findViewById(R.id.dob);
        currentaddress = (EditText) findViewById(R.id.currentAddress);
        permanentaddress = (EditText) findViewById(R.id.permanentAddress);
        registerButton = (Button) findViewById(R.id.buttonRegister);
        loginButton = (Button) findViewById(R.id.buttonLogin);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name = firstname.getText().toString().trim();
                last_name = lastname.getText().toString().trim();
                emailid = email.getText().toString().trim();
                passw1 = password.getText().toString().trim();
                aadhar_passport_no = aadhar.getText().toString().trim();
                phone_no = phone.getText().toString().trim();
                graduated_college = college.getText().toString().trim();
                dept_qualified = deptqualified.getText().toString().trim();
                qualification1 = qualification.getText().toString().trim();
                cgpa1 = cgpa.getText().toString().trim();
                achievements1 = achievements.getText().toString().trim();
                college_batch = collegebatch.getText().toString().trim();
                previous_office = previousoffice.getText().toString().trim();
                previous_position1 = previousposition.getText().toString().trim();
                years_of_service1 = yearsofservice.getText().toString().trim();
                gender1 = ( (RadioButton) findViewById(gender.getCheckedRadioButtonId()) ).getText().toString();
                dob1 = dob.getText().toString().trim();
                current_address = currentaddress.getText().toString().trim();
                permanent_address1 = permanentaddress.getText().toString().trim();
             if(isEmpty(first_name)||isEmpty(last_name)||isEmpty(emailid)||isEmpty(passw1)||isEmpty(aadhar_passport_no)||isEmpty(phone_no)||isEmpty(graduated_college )||isEmpty( dept_qualified )||isEmpty(qualification1)||isEmpty(  cgpa1 )||isEmpty(achievements1)||isEmpty(college_batch)||isEmpty(previous_office)||isEmpty(previous_position1)||isEmpty( years_of_service1 )||isEmpty( gender1)||isEmpty(dob1)||isEmpty(current_address)||isEmpty(permanent_address1))
             {
                 Toast toast = Toast.makeText(getApplicationContext(),"Enter All Details", Toast.LENGTH_LONG);
                 toast.show();
             }
             else
                 userRegister();

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(registration.this, login.class);
                startActivity(i1);

            }
        });

    }

    public void userRegister() {
            String URL = "https://admintesting.herokuapp.com/userreg";
            data = new JSONObject();
            try {
                data.put(Key_firstname, first_name);
                data.put(Key_lastname, last_name);
                data.put(Key_email, emailid);
                data.put(Key_password, passw1);
                data.put(Key_aadhar, aadhar_passport_no);
                data.put(Key_phone, phone_no);
                data.put(Key_college, graduated_college);
                data.put(Key_deptqualified, dept_qualified);
                data.put(Key_qualification, qualification1);
                data.put(Key_cgpa, cgpa1);
                data.put(Key_achievements, achievements1);
                data.put(Key_collegebatch, college_batch);
                data.put(Key_previousoffice, previous_office);
                data.put(Key_previousposition, previous_position1);
                data.put(Key_yearsofservice, years_of_service1);
                data.put(Key_gender, gender1);
                data.put(Key_dob, dob1);
                data.put(Key_currentaddress, current_address);
                data.put(Key_permanentaddress, permanent_address1);
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
                    });
            objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));

            queue.add(objectRequest);
        }
    }




