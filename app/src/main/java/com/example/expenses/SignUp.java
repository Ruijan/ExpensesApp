package com.example.expenses;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.Response;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button signupButton = findViewById(R.id.signup);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            RequestQueue queue = Volley.newRequestQueue(view.getContext());
            String baseUrl ="http://pixelnos-ledger-api.herokuapp.com/BackEnd/index.php?action=connection/";

            StringRequest signUpRequest = new StringRequest(Request.Method.POST, baseUrl + "SignUp",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            System.out.println("Response is: "+ response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("That didn't work!" + error.getMessage());
                }
            }){
                protected Map<String, String> getParams() {
                    EditText email = ((EditText) findViewById(R.id.email));
                    EditText password = ((EditText) findViewById(R.id.password));
                    EditText firstName = ((EditText) findViewById(R.id.firstName));
                    EditText LastName = ((EditText) findViewById(R.id.lastName));
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("email", email.getText().toString()); //Add the data you'd like to send to the server.
                    MyData.put("password", password.getText().toString()); //Add the data you'd like to send to the server.
                    MyData.put("first_name", firstName.getText().toString()); //Add the data you'd like to send to the server.
                    MyData.put("last_name", LastName.getText().toString()); //Add the data you'd like to send to the server.
                    return MyData;
                }
            };
            StringRequest signInRequest = new StringRequest(Request.Method.POST, baseUrl + "SignIn",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            if(response == "Connected"){
                                Intent dashboard = new Intent(view.getContext(), DashBoard.class);
                                startActivity(dashboard);

                            }
                            System.out.println("Response is: "+ response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("That didn't work!" + error.getMessage());
                }
            }){
                protected Map<String, String> getParams() {
                    EditText email = ((EditText) findViewById(R.id.email));
                    EditText password = ((EditText) findViewById(R.id.password));
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("email", email.getText().toString()); //Add the data you'd like to send to the server.
                    MyData.put("password", password.getText().toString()); //Add the data you'd like to send to the server.
                    return MyData;
                }
            };
            queue.add(signUpRequest);
            queue.add(signInRequest);
            }
        });
    }

}
