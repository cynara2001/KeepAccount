package com.example.keepaccount.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.keepaccount.R;

public class BudgetDialog extends Dialog implements View.OnClickListener {

    EditText moneyEt;
    Button ensureBtn, cancelBtn;
    public interface onEnsureListener {
        public void onEnsure(float money);
    }

    onEnsureListener onEnsureListener;

    public void setOnEnsureListener(BudgetDialog.onEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public BudgetDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.budge_dialog);
        moneyEt = findViewById(R.id.dialog_budget_et);
        ensureBtn = findViewById(R.id.dialog_budget_btn_confirm);
        cancelBtn = findViewById(R.id.dialog_budget_btn_cancel);
        ensureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_budget_btn_cancel:
                cancel();
                break;
            case R.id.dialog_budget_btn_confirm:
                //获取输入数据数值
                String data = moneyEt.getText().toString();
                if (TextUtils.isEmpty(data)) {
                    Toast.makeText(getContext(), "输入数据不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                float money = Float.parseFloat(data);
                if (money <= 0) {
                    Toast.makeText(getContext(), "预算金额必须大于0", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (onEnsureListener!=null) {
                    onEnsureListener.onEnsure(money);
                }
                cancel();
                break;
        }
    }
}
