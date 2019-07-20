package com.shashank.upso_iocl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {


    private EditText  sapcode,  remarks, name,  priority,vsat_ip;
    public int value;

    Random random = new Random();
    int x = random.nextInt(900) + 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btn_submit;
        Button summarize;
        sapcode =  findViewById(R.id.sapcode);
        final String date_of_issue = Calendar.getInstance().getTime().toString();
        remarks =  findViewById(R.id.remarks);
        priority =  findViewById(R.id.priority);
        name =  findViewById(R.id.name);
        vsat_ip =  findViewById(R.id.vsat_ip);
        final Spinner myspinner = findViewById(R.id.type);
        final Spinner myspinner1 = findViewById(R.id.d_office);
        final Spinner myspinner2 = findViewById(R.id.issue);
        final Spinner myspinner3 = findViewById(R.id.auto_vendor);
        final Spinner myspinner4 = findViewById(R.id.vsat_vendor);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(SecondActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Type));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        myspinner.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<>(SecondActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.D_office));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        myspinner1.setAdapter(myAdapter1);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(SecondActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Issue));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        myspinner2.setAdapter(myAdapter2);

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(SecondActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.auto_vendor));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        myspinner3.setAdapter(myAdapter3);

        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<>(SecondActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.vsat_vendor));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        myspinner4.setAdapter(myAdapter4);


        btn_submit =  findViewById(R.id.btn_submit);
        summarize =  findViewById(R.id.summarize);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String data = "{\"sapcode\":\""+Integer.parseInt(sapcode.getText().toString())+"\",\"complaint_no\":\""+(Integer.parseInt(sapcode.getText().toString())+x)+"\",\"auto_vendor\":\""+myspinner3.getSelectedItem().toString()+"\",\"d_office\":\""+myspinner1.getSelectedItem().toString()+"\",\"issue\":\""+myspinner2.getSelectedItem().toString()+"\",\"remarks\":\""+remarks.getText().toString()+"\",\"priority\":\""+priority.getText().toString()+"\",\"name\":\""+name.getText().toString()+"\",\"type\":\""+myspinner.getSelectedItem().toString()+"\",\"vsat_ip\":\""+vsat_ip.getText().toString()+"\",\"vsat_vendor\":\""+myspinner4.getSelectedItem().toString()+"\",\"date_of_issue\":\""+date_of_issue+"\"}";
                fetchData process = new fetchData();
                process.execute();
                Submit(data);


            }
        });
        summarize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                summarize(value);


            }
        });
    }

    private void summarize( int value) {
        this.value = value;


        Intent i = new Intent(SecondActivity.this, last.class);
        i.putExtra("complaint_no",String.valueOf(value) );
        startActivity(i);


    }

    private void Submit(String data)
    {
        value = (Integer.parseInt(sapcode.getText().toString())+x);
         RequestQueue requestQueue;

        final String savedata= data;
        String URL="http://192.168.1.11:5000/complaint";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objres=new JSONObject(response);
                    Toast.makeText(getApplicationContext(),objres.toString(),Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_LONG).show();

                }
                Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                Log.v("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @SuppressLint("LongLogTag")
            @Override
            public byte[] getBody() {
                try {
                    return savedata == null ? null : savedata.getBytes("utf-8");

                } catch (UnsupportedEncodingException uee) {
                    //Log.v("Unsupported Encoding while trying to get the bytes", data);
                    return null;
                }
            }

        };

        requestQueue.add(stringRequest);


    }


}