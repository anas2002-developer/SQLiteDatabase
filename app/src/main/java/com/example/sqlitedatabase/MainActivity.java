package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper dbHelper = new MyDBHelper(this);

        //insert
//        dbHelper.addContact("anas","7060997580");
//        dbHelper.addContact("aman","7500431307");
//        dbHelper.addContact("rihan","4455668899");
//        dbHelper.addContact("subhan","1100223344");


        //update
        ContactModel contactModel = new ContactModel();
        contactModel.ID=1;  //for checking
        contactModel.CONTACT_NO="1234567890";  //for modifying
        dbHelper.updateContact(contactModel);

        //delete
        dbHelper.deleteContact(7);

        //fetch
        ArrayList<ContactModel> arrContact = dbHelper.fetchcontact();

        for (int i=0;i<arrContact.size();i++){
            Log.d("Contact info : ",
                    "Name : "+arrContact.get(i).NAME +",\n"
                            +"Phone No. : "+arrContact.get(i).CONTACT_NO);
        }



    }
}