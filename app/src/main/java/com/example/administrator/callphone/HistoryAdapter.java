package com.example.administrator.callphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/23.
 */
public class HistoryAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Map<String, String>> list;
    private int[] addre;

    HistoryAdapter(Context context, ArrayList<Map<String, String>> list, int[] addre){
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.addre = addre;
    }
    @Override
    public int getCount() {
        return list.size();
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
        Map map = list.get(position);
        HistoryMyTag historyMyTag;
        int idimg = 0;

        Random random = new Random();
        for (int i = 0; i < this.addre.length; i++){

            idimg = random.nextInt(this.addre.length);
        }

        if (convertView == null){
            historyMyTag = new HistoryMyTag();
            convertView = layoutInflater.inflate(R.layout.activity_main, null);
            historyMyTag.historyImg = (ImageView) convertView.findViewById(R.id.historyImg);
            historyMyTag.historyName = (TextView) convertView.findViewById(R.id.historyName);
            historyMyTag.historyNumber = (TextView) convertView.findViewById(R.id.historyNumber);
            historyMyTag.callName = (TextView) convertView.findViewById(R.id.callName);
            historyMyTag.time = (TextView) convertView.findViewById(R.id.timeView);
            convertView.setTag(historyMyTag);
        }else {
            historyMyTag = (HistoryMyTag) convertView.getTag();
        }

        historyMyTag.historyImg.setImageResource(this.addre[idimg]);
        historyMyTag.historyName.setText((String) map.get("name"));
        historyMyTag.historyNumber.setText((String) map.get("number"));
        historyMyTag.callName.setText((String) map.get("callname"));
        historyMyTag.time.setText((String) map.get("time"));

        return convertView;
    }
}

class HistoryMyTag{
    ImageView historyImg;
    TextView historyName, historyNumber,callName, time;
}
