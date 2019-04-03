package com.ljx.xdreminder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class settingsFragment extends Fragment {
    TextView usr_account;
    TextView usr_cardpassword;
    TextView usr_netpassword;
    TextView usr_email;
    Button modify;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usr_account = getActivity().findViewById(R.id.usr_account);
        usr_cardpassword = getActivity().findViewById(R.id.usr_cardpassword);
        usr_netpassword = getActivity().findViewById(R.id.usr_netpassword);
        usr_email = getActivity().findViewById(R.id.usr_email);
        modify = getActivity().findViewById(R.id.usr_modify);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usr", Context.MODE_PRIVATE);
        usr_account.setText("学号:"+sharedPreferences.getString("account", null));
        usr_cardpassword.setText("一卡通密码:"+sharedPreferences.getString("cardpassword",null));
        usr_netpassword.setText("校园网密码:"+sharedPreferences.getString("netpassword",null));
        usr_email.setText("邮箱:"+sharedPreferences.getString("email",null));

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.message,null);
                final EditText accountText = view1.findViewById(R.id.account);
                final EditText cardpasswordText = view1.findViewById(R.id.cardpassword);
                final EditText netpasswordText = view1.findViewById(R.id.netpassword);
                final EditText emailText = view1.findViewById(R.id.email);

                dialog.setTitle("通知");
                dialog.setView(view1);
                dialog.setMessage("您的信息将会得到保护.");
                dialog.setPositiveButton("注册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String account = accountText.getText().toString();
                        String cardpassword = cardpasswordText.getText().toString();
                        String netpassword = netpasswordText.getText().toString();
                        String email = emailText.getText().toString();

                        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("usr", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences1.edit();

                        editor.putString("account",account);
                        editor.putString("cardpassword",cardpassword);
                        editor.putString("netpassword",netpassword);
                        editor.putString("email",email);
                        editor.commit();

                        Toast.makeText(getContext(),"重启APP后生效",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
            }
        });
    }
}
