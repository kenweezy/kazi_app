package com.app.kenweezy.kazi_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kenweezy on 5/7/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {




    private static final String DATABASE_NAME="kazi_app.db";
    private static final int SCHEMA_VERSION=1;
    private static final String EMPLOYEE_TABLE="CREATE TABLE employee(eid INTEGER PRIMARY KEY AUTOINCREMENT,first_name TEXT,last_name TEXT,phone TEXT,email TEXT,password TEXT);";
    private static final String PROJECTS_TABLE="CREATE TABLE projects(pid INTEGER PRIMARY KEY AUTOINCREMENT,project_name TEXT,task TEXT,comments TEXT,date TEXT);";
    private static final String TASK_TABLE="CREATE TABLE tasks(tid INTEGER PRIMARY KEY AUTOINCREMENT,eid INTEGER,pid INTEGER,date_done TEXT,task TEXT,time_taken TEXT,comments TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("CREATE TABLE restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, type TEXT, notes TEXT, feed TEXT, lat REAL, lon REAL, phone TEXT);");
        db.execSQL(EMPLOYEE_TABLE);
        db.execSQL(PROJECTS_TABLE);
        db.execSQL(TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+EMPLOYEE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+PROJECTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+TASK_TABLE);

        onCreate(db);
    }

    public Cursor getAll(String orderBy) {

        return(getReadableDatabase()
                .rawQuery("SELECT _id, name, address, type, notes, lat, lon, phone FROM restaurants ORDER BY "+orderBy,
                        null));
    }

    public Cursor getById(String id) {
        String[] args={id};

        return(getReadableDatabase()
                .rawQuery("SELECT _id, name, address, type, notes, feed, lat, lon, phone FROM restaurants WHERE _ID=?",
                        args));
    }

    public void insertEmployee(String fname, String lname,
                       String phone, String email,
                       String pass) {
        ContentValues cv=new ContentValues();

        cv.put("first_name", fname);
        cv.put("last_name", lname);
        cv.put("phone", phone);
        cv.put("email", email);
        cv.put("password", pass);


        this.getWritableDatabase().insert("employee", null, cv);
    }

    public void insertProjects(String pname, String task,
                               String comments,String date) {
        ContentValues cv=new ContentValues();

        cv.put("project_name", pname);
        cv.put("task", task);
        cv.put("comments", comments);
        cv.put("date", date);



        this.getWritableDatabase().insert("projects", null, cv);
    }

    public String getSinlgeEmployee(String emailing)
    {
        Cursor cursor=this.getReadableDatabase().query("employee", null, " email=?", new String[]{emailing}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "EMAIL DOES NOT EXIST";
        }
        cursor.moveToFirst();
        String password1= cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        return password1;
    }


}
