package com.example.keepaccount.frag_keep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.keepaccount.R;

public class IncomeFragment extends Fragment {
    public IncomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstaanceState){
        return inflater.inflate(R.layout.fragment_expend, container, false);
    }
}
