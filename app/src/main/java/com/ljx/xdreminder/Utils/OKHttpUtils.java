package com.ljx.xdreminder.Utils;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OKHttpUtils {
    private static final String TAG = "OKHttpUtils";
    public static void GetSimpleMessages(String id,String password,String url,Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                                .add("id",id)
                                .add("password",password)
                                .build();

        Request request = new Request.Builder().url("http://10.0.2.2:5000"+url).post(body).build();
        client.newCall(request).enqueue(callback);
    }

    public static void GetCardBill(String start_date,String end_date,String id,String password,Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                                .add("start_date",start_date)
                                .add("end_date",end_date)
                                .add("id",id)
                                .add("password",password)
                                .build();
        Request request = new Request.Builder().url("http://10.0.2.2:5000/api/card_bill").post(body).build();
        client.newCall(request).enqueue(callback);
    }
}
