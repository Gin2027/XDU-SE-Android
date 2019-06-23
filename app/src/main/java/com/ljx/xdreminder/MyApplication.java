package com.ljx.xdreminder;

import android.app.Application;

import com.lwy.smartupdate.Config;
import com.lwy.smartupdate.UpdateManager;
import com.lwy.smartupdate.api.OkhttpManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //推荐在Application中初始化
        Config config = new Config.Builder()
                .isDebug(true)
                .httpManager(new OkhttpManager())
                .build(this);
        UpdateManager.getInstance().init(config);
    }
}