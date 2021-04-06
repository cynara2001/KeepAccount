package com.example.keepaccount.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.keepaccount.R;
import com.example.keepaccount.adapter.AccountListAdapter;
import com.example.keepaccount.database.dao.AccountDao;
import com.example.keepaccount.database.domain.Account;

import java.util.List;

public class AccountActivity extends AppCompatActivity {

    private Button mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mAdd = (Button) findViewById(R.id.btn_add);
        OnClick onClick = new OnClick();
        mAdd.setOnClickListener(onClick);

    }

    private class OnClick implements View.OnClickListener {
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.btn_add:
                    intent = new Intent(AccountActivity.this, KeepActivity.class);//跳转界面的操作
                    break;
            }
            startActivity(intent);
        }
    }
}













