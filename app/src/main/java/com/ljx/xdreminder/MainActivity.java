package com.ljx.xdreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.lwy.smartupdate.UpdateManager;
import com.lwy.smartupdate.api.IUpdateCallback;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private remindFragment remind;
    private searchFragment search;
    private settingsFragment settings;
    private Fragment[] fragments;
    private int lastfragment;

    private String manifestJsonUrl = "http://api.ppoj.ac.cn:8000/app/UpdateManifest.json";
    private IUpdateCallback mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkUpdate();
        System.out.println(BuildConfig.VERSION_CODE);

        firstRun();
        initFragment();
    }

    private void checkUpdate() {
        UpdateManager.getInstance().update(this, manifestJsonUrl, null);
    }

    public void registerUpdateCallbak() {
        mCallback = new IUpdateCallback() {
            @Override
            public void noNewApp() {

            }

            @Override
            public void beforeUpdate() {

            }

            @Override
            public void onProgress(int percent, long totalLength, int patchIndex, int patchCount) {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onCancelUpdate() {

            }

            @Override
            public void onBackgroundTrigger() {

            }
        };
        UpdateManager.getInstance().register(mCallback);
    }

    @Override
    protected void onDestroy() {
        if (mCallback != null)
            UpdateManager.getInstance().unRegister(mCallback);
        super.onDestroy();

    }

    private void firstRun() {
        final SharedPreferences sharedPreferences = getSharedPreferences("FirstRun", 0);
        Boolean first_run = sharedPreferences.getBoolean("First", true);
        if (first_run) {
            sharedPreferences.edit().putBoolean("First", false).commit();

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            View view1 = LayoutInflater.from(this).inflate(R.layout.message,null);
            dialog.setView(view1);

            final EditText accountText = view1.findViewById(R.id.account);
            final EditText cardpasswordText = view1.findViewById(R.id.cardpassword);
            final EditText netaccountText = view1.findViewById(R.id.netaccount);
            final EditText netpasswordText = view1.findViewById(R.id.netpassword);
            final EditText emailText = view1.findViewById(R.id.email);

            dialog.setTitle("通知");
            dialog.setMessage("您是第一次使用本APP,开启所有功能需要注册您的学号以及一卡通密码和校园网密码,您可以现在完成设置也可以在之后自行登录信息,您的信息会的到充分保护.\n注册完成后APP会自动重启开启全部功能!");
            dialog.setPositiveButton("注册", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String account = accountText.getText().toString();
                    String cardpassword = cardpasswordText.getText().toString();
                    String netaccount = netaccountText.getText().toString();
                    String netpassword = netpasswordText.getText().toString();
                    String email = emailText.getText().toString();

                    SharedPreferences sharedPreferences1 = getSharedPreferences("usr", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();

                    editor.putString("account",account);
                    editor.putString("cardpassword",cardpassword);
                    editor.putString("netaccount",netaccount);
                    editor.putString("netpassword",netpassword);
                    editor.putString("email",email);
                    editor.commit();

                    Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    intent.putExtra("REBOOT","reboot");
                    PendingIntent restartIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
                    AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, restartIntent);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });

            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences sharedPreferences1 = getSharedPreferences("usr", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putString("account","未知");
                    editor.putString("cardpassword","NULL");
                    editor.putString("netaccount","NULL");
                    editor.putString("netpassword","NULL");
                    editor.putString("email","NULL");
                    editor.commit();
                }
            });
            dialog.show();
        } else {
        }
    }


    private void initFragment() {
        remind = new remindFragment();
        search = new searchFragment();
        settings = new settingsFragment();
        fragments = new Fragment[]{search,remind,settings};
        lastfragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,search).show(search).commit();
        bottomNavigationView = findViewById(R.id.bnv);
        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.id1:
                {
                    if(lastfragment!=0)
                    {
                        switchFragment(lastfragment,0);
                        lastfragment=0;

                    }

                    return true;
                }
                case R.id.id2:
                {
                    if(lastfragment!=1)
                    {
                        switchFragment(lastfragment,1);
                        lastfragment=1;

                    }

                    return true;
                }
                case R.id.id3:
                {
                    if(lastfragment!=2)
                    {
                        switchFragment(lastfragment,2);
                        lastfragment=2;

                    }

                    return true;
                }
            }
            return false;
        }
    };

    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.mainview,fragments[index]);


        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }
}
