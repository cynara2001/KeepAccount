package com.example.keepaccount.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keepaccount.R;
import com.example.keepaccount.database.AccountBean;

import java.util.Calendar;
import java.util.List;

public class AccountAdapter extends BaseAdapter {
    Context context;//上下文拿进来
    List<AccountBean> mDatas;
    LayoutInflater inflater;
    int year, month, day;

    public AccountAdapter(Context context, List<AccountBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //声明一下ViewHolder
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expend_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AccountBean bean = mDatas.get(position);
        holder.typeIv.setImageResource(bean.getImageId());
        holder.typeTv.setText(bean.getTypename());
        holder.remarkTv.setText(bean.getRemark());
        holder.moneyTv.setText("￥ "+bean.getMoney());
        if (bean.getYear()==year&&bean.getMonth()==month&&bean.getDay()==day) {
            holder.timeTv.setText("   今天");
        }else {
            holder.timeTv.setText(bean.getTime());
        }
        return convertView;
    }

    //用ViewHolder将每一个item都传进来
    class ViewHolder {

        ImageView typeIv;
        TextView typeTv, remarkTv, timeTv, moneyTv;

        public ViewHolder(View view) {
            typeIv = view.findViewById(R.id.item_account_iv);
            typeTv = view.findViewById(R.id.item_account_tv_title);
            timeTv = view.findViewById(R.id.item_account_tv_time);
            remarkTv = view.findViewById(R.id.item_account_tv_remarks);
            moneyTv = view.findViewById(R.id.item_account_tv_sum);
        }
    }
}
