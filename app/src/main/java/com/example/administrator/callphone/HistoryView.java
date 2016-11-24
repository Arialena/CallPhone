package com.example.administrator.callphone;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class HistoryView extends AppCompatActivity {

    private Button hoitoryButton, contactButton, callButton;
    private ListView listView;
    private ArrayList<Map<String, String>> list;
    private int[] img2 = {R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);

        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.READ_CALL_LOG,
                                android.Manifest.permission.WRITE_CALL_LOG,
                android.Manifest.permission.CALL_PHONE,}, 124);

        listView = (ListView) findViewById(R.id.historyListView);
        hoitoryButton = (Button) findViewById(R.id.history);
        contactButton = (Button) findViewById(R.id.contact);
        callButton = (Button) findViewById(R.id.call);

        HistoryAdapter historyAdapter = new HistoryAdapter(this, GetCallsInPhone(), img2);
        listView.setAdapter(historyAdapter);

        //点击联系人拨号
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HashMap<String, String> map = (HashMap<String, String>) list.get(i);
                    String str = map.get("number");
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + str));
                    try {
                        startActivity(intent);
                    }catch (SecurityException s){

                    }
                }
        });

        //页面跳转按钮
        hoitoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HistoryView.this, HistoryView.class);
                startActivity(intent);
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HistoryView.this, Contacts.class);
                startActivity(intent);
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HistoryView.this, Calling.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<Map<String, String>> GetCallsInPhone() {

        list = new ArrayList<>();
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

            Cursor cursor = this.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    null, null, null, CallLog.Calls.DATE + " desc");
//            Cursor cursor = this.getApplicationContext().getContentResolver().query(
//                    CallLog.Calls.CONTENT_URI,
//                    new String[]{CallLog.Calls.DURATION, CallLog.Calls.TYPE, CallLog.Calls.DATE,
//                            CallLog.Calls.NUMBER}, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);

            boolean hasRecord = cursor.moveToFirst();
           // int count = 0;
            Random random = new Random();
            while (hasRecord) {
                Map<String, String> map = new HashMap<>();

                int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));

                //获得通话时长
                long duration = cursor.getLong(cursor
                        .getColumnIndex(CallLog.Calls.DURATION));
                map.put("time", "时长: " + duration + "s");
                //获取通话联系人图片
                for (int i = 0; i < img2.length; i++) {
                    int idImg = random.nextInt(img2.length);
                map.put("img", idImg + "");
                }
                //获取通话联系人姓名
                String name = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.CACHED_NAME));
                map.put("name", name);
                //获取通话联系人号码
                String strPhone = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                map.put("number", strPhone);

                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd hh:mm:ss");
               Date d = new Date(Long.parseLong(cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.DATE))));
                String date = dateFormat.format(d);
                map.put("dress",date);
                //获取通话信息
                String callname = "";
                switch (type) {
                    case CallLog.Calls.INCOMING_TYPE:
                        callname ="type : 呼入";
                        break;
                    case CallLog.Calls.OUTGOING_TYPE:
                        callname = "type : 呼出";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        callname = "type : 未接";
                        break;
                    default:
                        break;
                }
                map.put("callname", callname);
               // count++;
                list.add(map);
                hasRecord = cursor.moveToNext();
            }
        }
        return list;
    }
}
