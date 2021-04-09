package com.example.keepaccount.frag_keep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.keepaccount.R;
import com.example.keepaccount.database.DBManager;
import com.example.keepaccount.database.TypeBean;

import java.util.List;

public class OutcomeFragment extends BaseRecordFragment {

    //重写
    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        //获取数据库当中的数据源
        List<TypeBean> outlist = DBManager.getTypeList(0);
        typeList.addAll(outlist);
        adapter.notifyDataSetChanged();

        typeTv.setText("其他");
        typeIv.setImageResource(R.mipmap.other1);
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(0);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
