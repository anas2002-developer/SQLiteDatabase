package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "anas2002db";
    private static final int DATABASE_VERSION = 1;

    //table name
    private static final String TABLE_CONTACTS = "CONTACTS";

    //columns
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_NUMBER = "CONTACT_NO";

    //constructor
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //method for creating table
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_CONTACTS+
                "("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_NAME+" TEXT,"+
                KEY_NUMBER+ " TEXT )");

    }

    //method updating database schema
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContact(String name, String number){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_NUMBER,number);

        db.insert(TABLE_CONTACTS,null,values);
    }

    public ArrayList<ContactModel> fetchcontact(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_CONTACTS,null);

        ArrayList<ContactModel> arrContact = new ArrayList<>();

        while(cursor.moveToNext()){
                ContactModel model = new ContactModel();
                model.ID = cursor.getInt(0);
                model.NAME = cursor.getString(1);
                model.CONTACT_NO = cursor.getString(2);

                arrContact.add(model);
        }

        return arrContact;

    }

    public void updateContact(ContactModel contactModel){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_NUMBER,contactModel.CONTACT_NO);
        db.update(TABLE_CONTACTS,updateValues,KEY_ID+" = "+contactModel.ID, null);
    }

    public void deleteContact(int ID){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CONTACTS,KEY_ID+" >= ? ", new String[]{
                String.valueOf(ID)
        }
        );
    }
}
