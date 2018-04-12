package com.example.android.mysql;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import util.DBUtil;

public class SQLActivity extends AppCompatActivity {

    private EditText fNameET, lNameET, gradeET, idET;
    private String fName, lName, grade, id;
    private DBUtil dbUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        init();
        dbUtil = new DBUtil(this);
    }

    private void init() {
        fNameET = findViewById(R.id.first_name);
        lNameET = findViewById(R.id.last_name);
        gradeET = findViewById(R.id.grade);
        idET = findViewById(R.id.id);
    }

    private boolean isFillAll() {
        if(     !fNameET.getText().toString().equals("")
                && !lNameET.getText().toString().equals("")
                && isNumeric(gradeET.getText().toString())) {
            return true;
        }
        else return false;
    }

    public static boolean isNumeric(String str)
    {
        try { Long d = Long.parseLong(str);}
        catch(NumberFormatException nfe) { return false;}
        return true;
    }

    public void save_click(View view) {
        if(isFillAll()) {
            fName = fNameET.getText().toString();
            lName = lNameET.getText().toString();
            grade = gradeET.getText().toString();
            boolean isOK = dbUtil.insertData(fName, lName, Integer.parseInt(grade));
            if(isOK) Toast.makeText(this, "insert data successfully", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "insert data error", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "fill all the fields!", Toast.LENGTH_SHORT).show();
    }

    public void get_click(View view) {
        Cursor cursor = dbUtil.getData();
        String str = "";
        while(cursor.moveToNext()) {
            str+="id: "+cursor.getString(0)+"\n";
            str+="name: "+cursor.getString(1)+"\n";
            str+="last name: "+cursor.getString(2)+"\n";
            str+="grade: "+cursor.getString(3)+"\n";
            str+="----------------\n";

        }
        showData(str);
    }

    public void get_id_click(View view) {
        Cursor cursor = dbUtil.getID(idET.getText().toString());
        String str = "";
        while(cursor.moveToNext()) {
            str+="id: "+cursor.getString(0)+"\n";
            str+="name: "+cursor.getString(1)+"\n";
            str+="last name: "+cursor.getString(2)+"\n";
            str+="grade: "+cursor.getString(3)+"\n";
            str+="----------------\n";

        }
        showData(str);
    }

    public void showData(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(DBUtil.DATA_NAME);
        builder.setMessage(str);
        builder.show();
    }
}
