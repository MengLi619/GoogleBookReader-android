package com.xiaoheifamily.googlebookreader.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoheifamily.googlebookreader.R;
import com.xiaoheifamily.googlebookreader.databinding.AccountFragmentBinding;
import com.xiaoheifamily.googlebookreader.viewmodel.AccountViewModel;

public class AccountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.account_fragment, container, false);
        AccountFragmentBinding bind = AccountFragmentBinding.bind(view);

        bind.setModel(new AccountViewModel());

        return view;
    }
}