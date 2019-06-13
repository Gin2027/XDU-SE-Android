package com.ljx.xdreminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ljx.xdreminder.Entity.grades;
import com.ljx.xdreminder.Entity.orderedGrade;
import com.loopeer.cardstack.CardStackView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class gradesActivity extends AppCompatActivity implements CardStackView.ItemExpendListener {
    private String result;
    private CardStackView cardStackView;
    private List<orderedGrade> gradesList;
    Integer[] color = {
            R.color.holo_blue_bright,
            R.color.holo_orange_light,
            R.color.holo_purple,
            R.color.holo_red_light,
            R.color.holo_blue_bright,
            R.color.holo_orange_light,
            R.color.holo_purple,
            R.color.holo_red_light
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        result = this.getIntent().getStringExtra("result");
        gradesList = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(result).getAsJsonArray();
        Gson gson = new Gson();

        for (JsonElement element : jsonArray) {
            grades grades = gson.fromJson(element,grades.class);
            String s = gson.fromJson(element,grades.class).getSemester();
            byte[] bytes = s.getBytes();
            int sum = 0;
            for (int i = 0; i < bytes.length; i++) {
                sum += bytes[i];
            }
            orderedGrade orderedGrade = new orderedGrade(grades.getSemester(),grades.getLessons(),sum);
            gradesList.add(orderedGrade);
        }

        Collections.sort(gradesList);


        init();
    }

    @Override
    public void onItemExpend(boolean expend) {

    }

    void init() {
        cardStackView = findViewById(R.id.cardStackView);
        ScoresCardStackAdapter adapter = new ScoresCardStackAdapter(this);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemExpendListener(this);

        List<List<String>> lists = new LinkedList<>();
        List<Integer> colorList = new LinkedList<>();
        List<String> titleList = new LinkedList<>();
        for (int i = 0; i<gradesList.size(); i++) {
            orderedGrade g = gradesList.get(i);
            lists.add(g.getLessons());
            colorList.add(color[i]);
            titleList.add(gradesList.get(i).getSemester());
        }
        adapter.updateData(colorList,titleList,lists);
    }
}
