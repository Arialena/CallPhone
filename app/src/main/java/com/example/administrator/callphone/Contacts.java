package com.example.administrator.callphone;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/22.
 */
public class Contacts extends AppCompatActivity {

    private Button hoitoryButton, contactButton, callButton;
    private ImageButton addImageButton;
    private ListView listView;
    private ArrayList<Map<String, String>> list;
    private int[] img = {R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactlist_view);


        //获取读写联系人
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 124);

        listView = (ListView) findViewById(R.id.contactListView);

        ContactAdapter contactAdapter = new ContactAdapter(this, getDate(),img);
        listView.setAdapter(contactAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map =(HashMap<String, String>) list.get(position);
                String str = map.get("number");
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + str));
                try {
                    startActivity(intent);
                }catch (SecurityException e){

                }
            }
        });

        hoitoryButton = (Button) findViewById(R.id.history);
        contactButton = (Button) findViewById(R.id.contact);
        callButton = (Button) findViewById(R.id.call);
        addImageButton = (ImageButton) findViewById(R.id.addImageButton);

        //页面跳转按钮
        hoitoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Contacts.this, HistoryView.class);
                startActivity(intent);
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Contacts.this, Calling.class);
                startActivity(intent);
            }
        });

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Contacts.this, AddContact.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<Map<String, String>> getDate(){

        Cursor cursor = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        int contactIdIndex = 0;
        int nameIdex = 0;

        if (cursor.getCount() > 0) {
            contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            nameIdex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        }

        Random random = new Random();
        list = new ArrayList<>();

        for (int i = 0; i < img.length; i++) {
            int idImg = random.nextInt(img.length);
           // int idImg = img[i];
            while (cursor.moveToNext()){

                Map<String, String> map = new HashMap<>();

                String contactId = cursor.getString(contactIdIndex);
                String name = cursor.getString(nameIdex);

                map.put("img", idImg + "");
                map.put("name", "姓名:" + name);

                Cursor phones = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);

                int phoneIndex = 0;
                if (phones.getCount() > 0) {
                    phoneIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                }

                while (phones.moveToNext()) {
                    String phoneNumber = phones.getString(phoneIndex);
                    map.put("number", phoneNumber);

                    list.add(map);
                }
            }
        }
        return list;
    }
}
