package com.example.jholababa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NyDatabaseHelper extends SQLiteOpenHelper {

//    private static final String TAG = "NyDatabaseHelper";
    private Context context;
    private static final String DATABASE_NAME = "HomeWorkersList.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "home_workers_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "person_name";
    private static final String COLUMN_OCCUPATION = "person_occupation";
    private static final String COLUMN_PHONE = "person_phone";


    NyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_OCCUPATION + " TEXT, " +
                COLUMN_PHONE + " INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
    }

    //INSERTING DATA

    void addPerson(String name, String occupation, int phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
//***
            cv.put(COLUMN_NAME, name);
            cv.put(COLUMN_OCCUPATION, occupation);
            cv.put(COLUMN_PHONE, phone);
            long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context,"Task Failed!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Added Successfully!",Toast.LENGTH_SHORT).show();
        }
    }

    //    DISPLAY DATA

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


//    UPDATE DATA

    void updateData(String row_id, String name, String occupation, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_OCCUPATION, occupation);
        cv.put(COLUMN_PHONE, phone);

        long result = db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Task Failed!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Updated Successfully!",Toast.LENGTH_SHORT).show();
        }
    }

//      DELETE ONE ROW
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Task Failed!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Deleted Successfully!",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
