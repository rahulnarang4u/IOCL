package com.shashank.upso_iocl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class last extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        TextView lastgettext =  findViewById(R.id.lastgetdata);
        Intent i = getIntent();
        String no = i.getStringExtra("complaint_no");
        String success = "Success, Your Complaint ID is:"+no;
        lastgettext.setText(success);

    }

}


