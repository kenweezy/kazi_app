/*package com.app.kenweezy.navigation;

/**
 * Created by kenweezy on 5/6/2015.
 *//*
public class Login {
}
*/
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


public class Login extends ActionBarActivity {

    EditText em;
    EditText pas;
    Button lgn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        em=(EditText) findViewById(R.id.email);
        pas=(EditText) findViewById(R.id.password);
        lgn=(Button) findViewById(R.id.login);
        db=new DatabaseHelper(this);


        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailAd=em.getText().toString();
                String passwrd=pas.getText().toString();
                String storedPassword=db.getSinlgeEmployee(emailAd);

                if(passwrd.equals(storedPassword)){

                    Toast.makeText(getApplicationContext(), "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
                    Intent i=new Intent("com.app.kenweezy.kazi_app.LOGINREDIRECT");
                    startActivity(i);
                }

                else if(passwrd.equals("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Your details", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Password Incorrect", Toast.LENGTH_LONG).show();
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