package com.app.kenweezy.kazi_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;

import com.app.kenweezy.kazi_app.R;


public class LoginRedirect extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_redirect);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loginmenu, menu);
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
        else if(id==R.id.action_addProject){


            Intent i=new Intent("com.app.kenweezy.kazi_app.ADDPROJECT");
            startActivity(i);
        }
        else if(id==R.id.action_addTask){
            Intent i=new Intent("com.app.kenweezy.kazi_app.ADDTASK");
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}

