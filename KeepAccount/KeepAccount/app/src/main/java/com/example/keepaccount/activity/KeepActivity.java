package com.example.keepaccount.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.keepaccount.R;
import com.example.keepaccount.adapter.KeepPaperAdapter;
import com.example.keepaccount.frag_keep.IncomeFragment;
import com.example.keepaccount.frag_keep.BaseRecordFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class KeepActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep);
        //1.查找控件
        tabLayout = findViewById(R.id.keep_tabs);
        viewPager = findViewById(R.id.keep_vp);
        //2.设置ViewPager加载页面
        initPager();
    }

    private void initPager() {
        //初始化ViewPager页面的集合
        List<Fragment> fragmentList = new ArrayList<>();
        //创建收入和支出页面，放置在Fragment中
        BaseRecordFragment outFrag = new BaseRecordFragment();//支出
        IncomeFragment inFrag = new IncomeFragment();//收入
        fragmentList.add(outFrag);
        fragmentList.add(inFrag);
        //创建适配器
        KeepPaperAdapter paperAdapter = new KeepPaperAdapter(getSupportFragmentManager(),fragmentList);
        //设置适配器
        viewPager.setAdapter(paperAdapter);
        //将TabLayout和ViewPaper进行关联
        tabLayout.setupWithViewPager(viewPager);
    }
}