package com.example.keepaccount.frag_keep;

import android.annotation.SuppressLint;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keepaccount.R;
import com.example.keepaccount.database.AccountBean;
import com.example.keepaccount.database.DBManager;
import com.example.keepaccount.database.TypeBean;
import com.example.keepaccount.utils.KeyBoardUtils;
import com.example.keepaccount.utils.RemarkDialog;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public abstract class BaseRecordFragment extends Fragment implements View.OnClickListener {

    KeyboardView keyboardView;
    //键盘引起EditText、图片等的变化
    EditText moneyEt;
    ImageView typeIv;
    TextView typeTv, remarkTv, timeTv;
    GridView typeGv;
    List<TypeBean> typeList;
    List<TypeBean> outList;
    TypeBaseAdapter adapter;
    AccountBean accountBean;    //将需要插入到记账本当中的数据保存成对象的形式

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建这个对象
        accountBean = new AccountBean();
        accountBean.setTypename("其他");//默认
        accountBean.setImageId(R.mipmap.other1);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expend, container, false);

        initView(view);//用一个方法来初始化View

        setInitTime();//初始化时间

        loadDataToGV();//给GridView填充数据的方法

        setGVListener();//设置GridView每一项的点击事件
        return view;
    }

    //获取当前时间，显示在timeTv上
    private void setInitTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String time = sdf.format(date);
        timeTv.setText(time);
        accountBean.setTime(time);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        accountBean.setYear(year);
        accountBean.setMonth(month);
        accountBean.setDay(day);

    }


    //设置GridView每一项的点击事件
    private void setGVListener() {
        typeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.selectPos = position;
                adapter.notifyDataSetChanged();//提示绘制发生变化了
                TypeBean typeBean = typeList.get(position);
                String typename = typeBean.getTypename();
                typeTv.setText(typename);

                accountBean.setTypename(typename);

                int imageId = typeBean.getImageID();
                typeIv.setImageResource(imageId);

                accountBean.setImageId(imageId);
            }
        });
    }

    //给GridView填充数据的方法
    public void loadDataToGV() {
        typeList = new ArrayList<>();
        adapter = new TypeBaseAdapter(getContext(), typeList);
        typeGv.setAdapter(adapter);

    }

    private void initView(View view) {
        keyboardView = view.findViewById(R.id.frag_keep_keyboard);
        moneyEt = view.findViewById(R.id.frag_keep_et_money);
        typeIv = view.findViewById(R.id.frag_keep_iv);
        typeGv = view.findViewById(R.id.frag_keep_gv);
        typeTv = view.findViewById(R.id.frag_keep_tv_type);
        remarkTv = view.findViewById(R.id.frag_keep_tv_remark);
        timeTv = view.findViewById(R.id.frag_keep_tv_time);
        remarkTv.setOnClickListener(this);
        //让软键盘显示出来
        KeyBoardUtils boardUtils = new KeyBoardUtils(keyboardView, moneyEt);
        boardUtils.showKeyboard();
        //设置接口，监听确定按钮按钮被点击了
        boardUtils.setOnEnsureListener(new KeyBoardUtils.OnEnsureListener() {
            @Override
            public void onEnsure() {
                //获取输入钱数
                String moneyStr = moneyEt.getText().toString();
                if (TextUtils.isEmpty(moneyStr)||moneyStr.equals("0")) {
                    getActivity().finish();
                    return;
                }
                float money = Float.parseFloat(moneyStr);
                accountBean.setMoney(money);
                //获取记录的信息，保存在数据库当中
                saveAccountToDB();
                // 返回上一级页面
                getActivity().finish();
            }
        });
    }

    //让子类一定要重写这个方法
    public abstract void saveAccountToDB();

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.frag_keep_tv_remark){
            showRemarkDialog();
        }
    }

    //弹出备注对话框
   public void showRemarkDialog() {
       RemarkDialog dialog = new RemarkDialog(getContext());
       dialog.show();

       dialog.setOnEnsureListener(new RemarkDialog.OnEnsureListener() {
           @Override
           public void onEnsure() {
               String msg = dialog.getEditText();
               if (!TextUtils.isEmpty(msg)) {
                   remarkTv.setText(msg);
                   accountBean.setRemark(msg);
               }
               dialog.cancel();
           }
       });
   }
}
