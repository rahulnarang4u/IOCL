package com.shashank.upso_iocl;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;


public class MainActivity2 extends AppCompatActivity {
    Button click;
    public static TextView data;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        click = findViewById(R.id.button_parse);
        data = findViewById(R.id.text_view_result);

       /* click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.shashank.upso_iocl.fetchData process = new com.shashank.upso_iocl.fetchData();
                process.execute();
            }
        });*/

    }

    public void mAdmin(View view)
    {
        Intent intent = new Intent(MainActivity2.this, admin.class);
        startActivity(intent);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.data) {
            Intent i = new Intent(MainActivity2.this, SecondActivity.class);
            startActivity(i);

        }
    }
    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);

                builder.setMessage("Are you sure you want to logout");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
