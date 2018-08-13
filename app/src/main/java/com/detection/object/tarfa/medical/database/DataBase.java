package com.detection.object.tarfa.medical.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tarfa on 6/16/18.
 */

public class DataBase extends SQLiteOpenHelper {
    //  name of database
    public static final String DATABASE_NAME = "medical.db";
    private static final int DATABASE_VERSION = 9;

    // columns of the user table
    public static final String TABLE_USER = "user";

    public static final String ID_USER = "id_user";
    public static final String First_Name = "first_name";
    public static final String FAMILY_NAME = "family_name";
    public static final String NUMBER = "number";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL,family_name TEXT NOT NULL,number_file INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    /**
     * to insert new person in the database @ ^_^
     * @param name
     * @param family
     * @param number
     * @return
     */
    public boolean insertNewPerson(String name,String family,int number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name );
        contentValues.put("family_name", family);
        contentValues.put("number_file", number);

        long result = db.insert(TABLE_USER, null, contentValues);

        if (result == -1){
            return false;
        } else {
            Log.e("insertNewPerson: ","yes insertd" );
            return true;
        }

    }

}
