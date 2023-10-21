package com.example.listview_3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String[] foodName;
    private int[] foodPrice;
    private TypedArray foodpic;
    private List<Map<String, Object>> listData;
    private ListView listViewData;
    private SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodName = getResources().getStringArray(R.array.food_name);
        foodPrice = getResources().getIntArray(R.array.food_price);
        foodpic = getResources().obtainTypedArray(R.array.food_picture);
        listData = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < foodName.length;i++){
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("name",foodName[i]);
            data.put("price",foodPrice[i]);
            data.put("picture",foodpic.getResourceId(i,0));
            listData.add(data);
        }

        listViewData = findViewById(R.id.listView_data);
        adapter = new SimpleAdapter(MainActivity.this
                ,listData
                , R.layout.item_layout
                ,new String[]{"name","price","picture"}
                ,new int[]{R.id.itemtxt_name,R.id.itemtxt_price,R.id.item_image});
        listViewData.setAdapter(adapter);

        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
                String name =item.get("name").toString();
                int price = (int) item.get("price");
                int picNum = (int) item.get("picture");
                Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("piice",price);
                intent.putExtra("picture",picNum);
                startActivity(intent);

            }
        });
    }
}