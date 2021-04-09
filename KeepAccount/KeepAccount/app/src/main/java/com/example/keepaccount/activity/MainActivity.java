package com.example.keepaccount.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;

import com.example.keepaccount.R;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDetail;//先声明控件
    private Button mBtnAccount;
    private Button mBtnMine;
    private Button mBtnBalance;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//在活动中加载了activity_main布局
        mBtnDetail = (Button) findViewById(R.id.button_1);
        mBtnBalance = (Button) findViewById(R.id.button_2);
        mBtnAccount = (Button) findViewById(R.id.button_3);
        mBtnMine = (Button) findViewById(R.id.button_4);
        mBtnDetail.setText(getSpan(R.drawable.detail," "));
        mBtnBalance.setText(getSpan(R.drawable.balance," "));
        mBtnAccount.setText(getSpan(R.drawable.account," "));
        mBtnMine.setText(getSpan(R.drawable.mine," "));
        setListeners();
    }

    private void setListeners() {
        //在这里设置它的监听事件
        OnClick onClick = new OnClick();
        mBtnDetail.setOnClickListener(onClick);
        mBtnAccount.setOnClickListener(onClick);
        mBtnBalance.setOnClickListener(onClick);
        mBtnMine.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.button_1:
                    //跳转到Detail（账目明细）演示界面
                    intent = new Intent(MainActivity.this, DetailActivity.class);
                    break;
                case R.id.button_2:
                    //跳转到Balance（余额）演示界面
                    intent = new Intent(MainActivity.this, BalanceActivity.class);
                    break;
                case R.id.button_3:
                    //跳转到Account（记账页）演示界面
                    intent = new Intent(MainActivity.this, AccountActivity.class);
                    break;
                case R.id.button_4:
                    //跳转到Mine（我的）演示界面
                    intent = new Intent(MainActivity.this, MineActivity.class);
                    break;
            }
            startActivity(intent);
        }

    }

    public Spanned getSpan(int id, String s) {
        Html.ImageGetter imgGetter = new Html.ImageGetter() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public Drawable getDrawable(String source) {
                // TODO Auto-generated method stub
                Drawable drawable = null;
                drawable = MainActivity.this.getResources().getDrawable(
                        Integer.parseInt(source));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
                return drawable;
            }
        };
        StringBuffer sb = new StringBuffer();
        sb.append("<img src=\"").append(id).append("\"/>")
                .append("              ").append("<font>" + s + "</font>");
        ;
        Spanned span = Html.fromHtml(sb.toString(), imgGetter, null);
        return span;
    }
}
