package com.example.administrator.callphone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/23.
 */
public class ContactAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<Map<String, String>> list;
    private  int[] address;

    ContactAdapter(Context context, ArrayList<Map<String, String>> list, int[] address){
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        this.address = address;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HashMap<String, String> map = (HashMap<String, String>) this.list.get(position);
        MyTag myTag;
        Random random = new Random();
        int idimg = 0;
        for (int i = 0; i < this.address.length; i++){
             idimg = random.nextInt(this.address.length);
        }

        if (convertView == null){

            myTag = new MyTag();

            convertView = layoutInflater.inflate(R.layout.contcat_itme, null);
            myTag.imageView = (ImageView) convertView.findViewById(R.id.imag);
            myTag.nameText = (TextView) convertView.findViewById(R.id.name);
            myTag.numberText = (TextView) convertView.findViewById(R.id.number);

            convertView.setTag(myTag);
        }else {
            myTag = (MyTag) convertView.getTag();
        }
        myTag.imageView.setImageResource(Integer.parseInt(String.valueOf(this.address[idimg])));
        myTag.nameText.setText(map.get("name"));
        myTag.numberText.setText(map.get("number"));

        return convertView;
    }
}
class MyTag{
        ImageView imageView;
    TextView nameText, numberText;

}
