package util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBUtil extends SQLiteOpenHelper {

    public static final String DATA_NAME = "student.db";
    public static final String TABLE_NAME = "student";
    public static final String ID = "ID", NAME = "NAME", LAST_NAME = "LAST_NAME", GRADE = "GRADE";

    public DBUtil(Context context) {
        super(context, DATA_NAME, null, 1);
        Log.v("test", "in DBUtil constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("test", "in DBUtil onCreate");
        db.execSQL("CREATE TABLE " + TABLE_NAME
        + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LAST_NAME TEXT, GRADE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("test", "in DBUtil onUpgrade");
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
    }

    public boolean insertData(String fName, String lName, int grade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, fName);
        contentValues.put(LAST_NAME, lName);
        contentValues.put(GRADE, grade);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1) return false;
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public Cursor getID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID=?" ,new String[]{String.valueOf(id)} );
        return cursor;
    }
}
