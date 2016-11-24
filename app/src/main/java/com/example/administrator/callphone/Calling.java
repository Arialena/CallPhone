package com.example.administrator.callphone;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/22.
 */
public class Calling extends AppCompatActivity {
    private Button hoitoryButton, contactButton, callButton;
    private TextView numbertextView;
    private Button button1, button2, button3, button4, button5, button6,
                    button7, button8, button9, buttonxin, buttonjin, button0;
    private Button callingButton, backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callview_iate);

         ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, 124);

        hoitoryButton = (Button) findViewById(R.id.history);
        contactButton = (Button) findViewById(R.id.contact);
        callButton = (Button) findViewById(R.id.call);

        numbertextView = (TextView) findViewById(R.id.numbertextView);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonxin = (Button) findViewById(R.id.xin);
        buttonjin = (Button) findViewById(R.id.jin);
        button0 = (Button) findViewById(R.id.button0);

        callingButton = (Button) findViewById(R.id.calling);
        backButton = (Button) findViewById(R.id.back);

        //页面跳转按钮
        hoitoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Calling.this, HistoryView.class);
                startActivity(intent);
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Calling.this, Contacts.class);
                startActivity(intent);
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //显示框按监听
        numbertextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //  从1---->9，，*，#的按钮监听
        numbertextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String number = numbertextView.getText().toString();
               if (number == null){
                   numbertextView.setText("1");
               }else {
                   numbertextView.setText(number + "1");
               }
           }
       });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("2");
                }else {
                    numbertextView.setText(number + "2");
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("3");
                }else {
                    numbertextView.setText(number + "3");
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("4");
                }else {
                    numbertextView.setText(number + "4");
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("5");
                }else {
                    numbertextView.setText(number + "5");
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("6");
                }else {
                    numbertextView.setText(number + "6");
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("7");
                }else {
                    numbertextView.setText(number + "7");
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("8");
                }else {
                    numbertextView.setText(number + "8");
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("9");
                }else {
                    numbertextView.setText(number + "9");
                }
            }
        });

        buttonxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("*");
                }else {
                    numbertextView.setText(number + "*");
                }
            }
        });

        buttonjin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){
                    numbertextView.setText("#");
                }else {
                    numbertextView.setText(number + "#");
                }
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null) {
                    numbertextView.setText("0");
                } else {
                    numbertextView.setText(number + "0");
                }
            }
        });

        //拨打， 退格按妞
        callingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();

                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = numbertextView.getText().toString();
                if (number == null){

                }else {
                    numbertextView.setText(number.substring(0, number.length() - 1));
                }
            }
        });
    }


}
