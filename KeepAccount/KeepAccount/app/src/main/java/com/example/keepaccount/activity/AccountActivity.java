package com.example.keepaccount.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.keepaccount.R;

public class AccountActivity extends AppCompatActivity{

    private Button mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAdd = (Button) findViewById(R.id.btn_add);

        mAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this,KeepActivity.class);
                startActivity(intent);

            }
        });
    }

}












