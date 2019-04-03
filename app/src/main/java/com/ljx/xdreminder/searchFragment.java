package com.ljx.xdreminder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ljx.xdreminder.Utils.BasisTimesUtils;
import com.ljx.xdreminder.Utils.OKHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class searchFragment extends Fragment {
    private String account;
    private String cardpassword;
    private String netpassword;
    private String email;
    private ImageView card_balace;
    private ImageView card_bill;
    private ImageView book;
    private ImageView grades;
    private ImageView net;
    private ImageView calendar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        card_balace = getActivity().findViewById(R.id.yue);
        card_bill = getActivity().findViewById(R.id.zhangdan);
        book = getActivity().findViewById(R.id.tushu);
        grades = getActivity().findViewById(R.id.chengji);
        net = getActivity().findViewById(R.id.liuliang);
        calendar = getActivity().findViewById(R.id.xiaoli);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usr", Context.MODE_PRIVATE);
        account = sharedPreferences.getString("account",null);
        cardpassword = sharedPreferences.getString("cardpassword",null);
        netpassword = sharedPreferences.getString("netpassword",null);
        email = sharedPreferences.getString("email",null);

        card_balace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OKHttpUtils.GetSimpleMessages(account, cardpassword, "/api/card_balance", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("一卡通余额");
                        dialog.setMessage(response.body().string());
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.show();
                            }
                        });
                    }
                });
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OKHttpUtils.GetSimpleMessages(account, cardpassword, "/api/book", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        List<String> books = new ArrayList<>();

                        String jsonObject = response.body().string();
                        JsonParser parser = new JsonParser();
                        JsonArray jsonArray = parser.parse(jsonObject).getAsJsonArray();
                        Gson gson = new Gson();
                        for (JsonElement book : jsonArray) {
                            books.add(gson.fromJson(book,String.class));
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                View view1 = getLayoutInflater().inflate(R.layout.book_item,null);
                                AlertDialog dialog = new AlertDialog.Builder(getContext())
                                        .setTitle("借阅书籍")
                                        .setView(view1)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        }).create();
                                ListView listView = view1.findViewById(R.id.book_name);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,books);
                                listView.setAdapter(adapter);
                                dialog.show();
                            }
                        });
                    }
                });
            }
        });

        card_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        View view1 = getLayoutInflater().inflate(R.layout.bill_time,null);
                        Button stime = view1.findViewById(R.id.stime);
                        Button ttime = view1.findViewById(R.id.ttime);
                        TextView stimetext = view1.findViewById(R.id.stime_text);
                        TextView ttimetext = view1.findViewById(R.id.ttime_text);
                        stime.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                BasisTimesUtils.showDatePickerDialog(getContext(), BasisTimesUtils.THEME_HOLO_DARK, "请选择年月日", 2019, 4, 1, new BasisTimesUtils.OnDatePickerListener() {
                                    @Override
                                    public void onConfirm(int year, int month, int dayOfMonth) {
                                        String time = year + "-" + month + "-" + dayOfMonth;
                                        stimetext.setText(time);
                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                            }
                        });
                        ttime.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                BasisTimesUtils.showDatePickerDialog(getContext(), BasisTimesUtils.THEME_HOLO_DARK, "请选择年月日", 2019, 4, 1, new BasisTimesUtils.OnDatePickerListener() {
                                    @Override
                                    public void onConfirm(int year, int month, int dayOfMonth) {
                                        String time = year + "-" + month + "-" + dayOfMonth;
                                        ttimetext.setText(time);
                                    }

                                    @Override
                                    public void onCancel() {

                                    }
                                });
                            }
                        });
                        AlertDialog dialog = new AlertDialog.Builder(getContext())
                                .setTitle("选择时间段:注意间隔不超过30天")
                                .setView(view1)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        OKHttpUtils.GetCardBill(stimetext.getText().toString(), ttimetext.getText().toString(), account, cardpassword, new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                e.printStackTrace();
                                                Toast.makeText(getContext(),"查询失败",Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                String result = response.body().string();
                                                if (result.startsWith("指定")||result.startsWith("查询时间跨度")) {
                                                    Looper.prepare();
                                                    Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
                                                    Looper.loop();
                                                } else {
                                                    Intent intent = new Intent(getActivity(), billActivity.class);
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("result", result);
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                }
                                            }
                                        });
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                    }
                });
            }
        });

        grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OKHttpUtils.GetSimpleMessages(account, cardpassword, "/api/grades", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Intent intent = new Intent(getActivity(), gradesActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("result", result);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
        });

        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OKHttpUtils.GetSimpleMessages(account, netpassword, "/api/net_balance", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                                dialog.setTitle("剩余流量");
                                dialog.setMessage(result);
                                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                dialog.show();
                            }
                        });
                    }
                });
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"功能待开发",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
