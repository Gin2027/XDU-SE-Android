package com.ljx.xdreminder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.ljx.xdreminder.json.RemindJson;
import com.suke.widget.SwitchButton;

public class remindFragment extends Fragment {
    private SwitchButton netlogin;
    private SwitchButton netbalance;
    private SwitchButton card;
    private SwitchButton book;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private RadioGroup radioGroup;
    private EditText cardlimit;
    private Button put;
    private RemindJson remindJson;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remind,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        remindJson = new RemindJson();
        netlogin = getActivity().findViewById(R.id.netlogin);
        netbalance = getActivity().findViewById(R.id.netbalance);
        card = getActivity().findViewById(R.id.card);
        book = getActivity().findViewById(R.id.book);
        radioGroup = getActivity().findViewById(R.id.chooseLevel);
        spinner1 = getActivity().findViewById(R.id.spinner1);
        spinner2 = getActivity().findViewById(R.id.spinner2);
        spinner3 = getActivity().findViewById(R.id.spinner3);
        spinner4 = getActivity().findViewById(R.id.spinner4);
        cardlimit = getActivity().findViewById(R.id.cardlimit);
        put = getActivity().findViewById(R.id.put);

        String[] Items = getResources().getStringArray(R.array.ways);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,Items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);

        netlogin.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) remindJson.setCampusNetworkLogin(true);
            }
        });

        netbalance.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) remindJson.setCampusNetworkBalance(true);
            }
        });

        card.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) remindJson.setCardBalance(true);
            }
        });

        book.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) remindJson.setBook(true);
            }
        });

        int c = Integer.parseInt(cardlimit.getText().toString());
        remindJson.setCardLimit(c);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = getActivity().findViewById(id);
                final String level = radioButton.getText().toString();
                level.substring(0,level.length()-1);
                remindJson.setNetworkLimit(Integer.parseInt(level));
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



}
