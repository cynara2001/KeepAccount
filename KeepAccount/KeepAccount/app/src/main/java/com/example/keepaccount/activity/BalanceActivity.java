package com.example.keepaccount.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keepaccount.R;
import com.example.keepaccount.database.DBManager;
import com.example.keepaccount.utils.BudgetDialog;

public class BalanceActivity extends AppCompatActivity implements View.OnClickListener {

    TextView outTv, inTv, budgetTv;
    ImageView searchIv;
    int year, month;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        outTv = findViewById(R.id.balance_expend);
        inTv = findViewById(R.id.balance_income);
        budgetTv = findViewById(R.id.balance_budget);
        searchIv = findViewById(R.id.balance_search);

        preferences = getSharedPreferences("budget", MODE_PRIVATE);

        outTv.setOnClickListener(this);
        inTv.setOnClickListener(this);
        budgetTv.setOnClickListener(this);
    }

    //设置文本的显示
    private void setTvShow() {
        //        获取本月收入和支出总金额
        float incomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 1);
        float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
        inTv.setText("￥" + incomeOneMonth);
        outTv.setText("￥" + outcomeOneMonth);

//    设置显示运算剩余
        float bmoney = preferences.getFloat("bmoney", 0);//预算
        if (bmoney == 0) {
            budgetTv.setText("￥ 0");
        } else {
            float syMoney = bmoney - outcomeOneMonth;
            budgetTv.setText("￥" + syMoney);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.balance_budget:
                showBudgetDialog();
                break;
            case R.id.balance_search:


                break;
        }
    }

    private void showBudgetDialog() {
        BudgetDialog dialog = new BudgetDialog(this);
        dialog.show();

        dialog.setOnEnsureListener(new BudgetDialog.onEnsureListener() {
            @Override
            public void onEnsure(float money) {
                //将预算金额写入共享参数中，进行储存
                SharedPreferences.Editor editor = preferences.edit();
                editor.putFloat("bmoney", money);
                editor.commit();

                //计算剩余金额
                float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);

                float syMoney = money - outcomeOneMonth;//预算剩余 = 预算-支出
                budgetTv.setText("￥" + syMoney);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTvShow();
    }


}