package com.ljx.xdreminder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.gson.Gson;
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
        /*初始化控件*/
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

        /*载入spinner*/
        String[] Items = getResources().getStringArray(R.array.ways);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,Items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);

        /*校园网流量提醒门槛*/
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = getActivity().findViewById(id);
                final String level = radioButton.getText().toString();
                remindJson.setNetworkLimit(Integer.parseInt(level.substring(0,level.length()-1)));
            }
        });

        /*设置各类提醒方法*/
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                remindJson.setWay1(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                remindJson.setWay2(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                remindJson.setWay3(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                remindJson.setWay4(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*各功能开关*/
                if (netlogin.isChecked()) remindJson.setCampusNetworkLogin(true); else remindJson.setCampusNetworkLogin(false);
                if (netbalance.isChecked()) remindJson.setCampusNetworkBalance(true); else remindJson.setCampusNetworkBalance(false);
                if (card.isChecked()) remindJson.setCardBalance(true); else remindJson.setCardBalance(false);
                if (book.isChecked()) remindJson.setBook(true); else remindJson.setBook(false);



                boolean f = true;

                /*设置一卡通余额门槛*/
                String str = cardlimit.getText().toString();
                if (str.isEmpty()) {
                    if (remindJson.isCardBalance()) {
                        f = false;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "余额门槛不能为空", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    try {
                        int c = Integer.parseInt(str);
                        if (c<0 || c>50) {
                            f = false;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(),"余额门槛不符合规范",Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else remindJson.setCardLimit(c);
                    } catch (NumberFormatException e) {
                        f = false;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"输入不符合规范",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                if (f) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usr", Context.MODE_PRIVATE);
                    String account = sharedPreferences.getString("account",null);
                    String cardpassword = sharedPreferences.getString("cardpassword",null);
                    String netaccount = sharedPreferences.getString("netaccount",null);
                    String netpassword = sharedPreferences.getString("netpassword",null);
                    String email = sharedPreferences.getString("email",null);
                    remindJson.setAccount(account);
                    remindJson.setCardpassword(cardpassword);
                    remindJson.setNetaccount(netaccount);
                    remindJson.setNetpassword(netpassword);
                    remindJson.setEmail(email);

                    String jsonObject = new Gson().toJson(remindJson);
                    System.out.println(jsonObject);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("通知");
                    dialog.setMessage("成功设置提醒任务");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();
                }

            }
        });
    }

}
