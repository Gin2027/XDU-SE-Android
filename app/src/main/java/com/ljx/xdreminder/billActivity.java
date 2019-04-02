package com.ljx.xdreminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ljx.xdreminder.Entity.bill;

import java.util.ArrayList;

public class billActivity extends AppCompatActivity {
    private TextView time;
    private TextView place;
    private TextView type;
    private TextView amount;
    private ListView billList;
    private BaseAdapter adapter;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        result = this.getIntent().getStringExtra("result");
        billList = findViewById(R.id.BillList);

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(result).getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<bill> bills = new ArrayList<>();
        for (JsonElement element: jsonArray){
            bill b = gson.fromJson(element,bill.class);
            bills.add(b);
        }

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return bills.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                LayoutInflater inflater = billActivity.this.getLayoutInflater();
                View view1;
                if (view == null) {
                    view1 = inflater.inflate(R.layout.bill_item,null);
                } else {
                    view1 = view;
                }
                time = view1.findViewById(R.id.bill_time);
                place = view1.findViewById(R.id.bill_place);
                type = view1.findViewById(R.id.bill_type);
                amount = view1.findViewById(R.id.bill_amount);

                time.setText("消费时间:"+bills.get(i).getTime());
                place.setText("消费地点:"+bills.get(i).getPlace());
                type.setText("消费类型:"+bills.get(i).getType());
                amount.setText("消费金额:"+bills.get(i).getAmount());
                return view1;
            }
        };

        billList.setAdapter(adapter);
    }
}
