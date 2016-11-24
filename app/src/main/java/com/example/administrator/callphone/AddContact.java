package com.example.administrator.callphone;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/22.
 */
public class AddContact extends AppCompatActivity {
    private TextView nameText, numberText;
    private Button addbutton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontact_itme);

        nameText = (TextView) findViewById(R.id.phoneName);
        numberText = (TextView) findViewById(R.id.phoneNumber);
        addbutton = (Button) findViewById(R.id.addbutton);

//        nameText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                nameText.setText(nameText.getText());
//            }
//        });
//
//        numberText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                numberText.setText(numberText.getText());
//            }
//        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
                Intent intent = new Intent();
                intent.setClass(AddContact.this, Contacts.class);
                startActivity(intent);
            }
        });
    }

    private void addContact(){
        ContentValues values = new ContentValues();

        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactID = ContentUris.parseId(rawContactUri);

        //向Uri添加联系人姓名
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactID);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, nameText.getText().toString());
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        //向Uri添加联系人号码
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactID);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, numberText.getText().toString());
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        Toast.makeText(this,"联系人添加成功",Toast.LENGTH_SHORT).show();

    }
}
