package com.shashank.upso_iocl;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class admin extends AppCompatActivity {

    private TextView Info22;
    private Button Login22;
    private int counter22=3;
    private Timer timer22;
    Button click22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        click22 = findViewById(R.id.button22);

        /*click22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.shashank.upso_iocl.fetchData process = new com.shashank.upso_iocl.fetchData();
                process.execute();
            }
        });*/


        final EditText Password22=findViewById(R.id.editText22);
        Info22= findViewById(R.id.textView22);
        Login22= findViewById(R.id.button22);
        Info22.setText("No of attempts remaining:"+counter22);

       Login22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Password22.getText().toString());
            }

        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        timer22 = new Timer();
        Log.i("Main", "Invoking logout timer");
        LogOutTimerTask logoutTimeTask = new LogOutTimerTask();
        timer22.schedule(logoutTimeTask, 50); //auto logout in 5o milliseconds
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer22 != null) {
            timer22.cancel();
            Log.i("Main", "cancel timer");
            timer22 = null;
        }
    }

    private class LogOutTimerTask extends TimerTask {

        @Override
        public void run() {

            //redirect user to login screen
            Intent i = new Intent(admin.this, parse.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }

    private void validate ( String userPassword){
        if(userPassword.equals("54321")){
            Intent intent= new Intent(admin.this, parse.class);
            startActivity(intent);
        }else{
            counter22--;

            Info22.setText("No of attempts remaining: "+counter22);
            if(counter22==0){
                Login22.setEnabled(false);
            }
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
                AlertDialog.Builder builder = new AlertDialog.Builder(admin.this);

                builder.setMessage("Are you sure you want to logout");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(admin.this, parse.class);
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
