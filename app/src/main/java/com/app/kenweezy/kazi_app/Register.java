package com.app.kenweezy.kazi_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.kenweezy.kazi_app.R;


public class Register extends ActionBarActivity {
    EditText fn;
    EditText ln;
    EditText pn;
    EditText em;
    EditText pas;
    Button regBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

         db=new DatabaseHelper(this);

        fn=(EditText) findViewById(R.id.firstName);
        ln=(EditText) findViewById(R.id.lastName);
        pn=(EditText) findViewById(R.id.phone);
        em=(EditText) findViewById(R.id.email);
        pas=(EditText) findViewById(R.id.password);
        regBtn=(Button) findViewById(R.id.register);



        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=fn.getText().toString();
                String lname=ln.getText().toString();
                String pnumber=pn.getText().toString();
                String email=em.getText().toString();
                String paswd=pas.getText().toString();

                if(fname.equals("")||lname.equals("")||pnumber.equals("")||email.equals("")||paswd.equals("")){


                    Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_LONG).show();
                    return;

                }
                else{

                    db.insertEmployee(fname,lname,pnumber,email,paswd);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();


                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id==R.id.action_login){


            Intent i=new Intent("com.app.kenweezy.kazi_app.LOGIN");
            startActivity(i);
        }
        else if(id==R.id.action_register){
            Intent i=new Intent("com.app.kenweezy.kazi_app.REGISTER");
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}