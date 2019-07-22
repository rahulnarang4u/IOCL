package com.shashank.upso_iocl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class parse extends AppCompatActivity {

    Button click222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parse);

        click222 = findViewById(R.id.button_parse22);
        click222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.shashank.upso_iocl.fetchData process = new com.shashank.upso_iocl.fetchData();
                process.execute();
            }
        });
    }
}
