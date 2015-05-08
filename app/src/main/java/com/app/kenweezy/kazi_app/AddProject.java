package com.app.kenweezy.kazi_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddProject extends ActionBarActivity {
    EditText date;
    EditText task;
    EditText comments;
    Spinner spin;
    ArrayAdapter<CharSequence> adapter;
    String spinerItem;
    Button add;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        db=new DatabaseHelper(this);

        date=(EditText) findViewById(R.id.dte);
        task=(EditText) findViewById(R.id.tasks);
        comments=(EditText) findViewById(R.id.coments);
        spin=(Spinner) findViewById(R.id.spinner);
        add=(Button) findViewById(R.id.add);

        adapter=ArrayAdapter.createFromResource(this,R.array.projects,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinerItem=parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datess=date.getText().toString();
                String taskss=task.getText().toString();
                String comentss=comments.getText().toString();

                if(spinerItem.isEmpty()||datess.isEmpty()||taskss.isEmpty()||comentss.isEmpty()){

                    Toast.makeText(getApplicationContext(), "PLEASE PROVIDE VALUES TO ALL FIELDS", Toast.LENGTH_LONG).show();
                    return;
                }
                else{

                    db.insertProjects(spinerItem,taskss,comentss,datess);
                    Toast.makeText(getApplicationContext(), "Task Successfully Added", Toast.LENGTH_LONG).show();
                    Intent i=new Intent("com.app.kenweezy.kazi_app.ADDPROJECT");
                    startActivity(i);

                }
            }
        });

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
      /*  else if(id==R.id.action_addTask){
            Intent i=new Intent("com.app.kenweezy.kazi_app.ADDTASK");
            startActivity(i);
        }*/

        return super.onOptionsItemSelected(item);
    }
}

