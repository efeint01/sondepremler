package com.son.deprem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class DepremFragmentAdapter extends FragmentStateAdapter {

    ArrayList<DepremItem> arrayList;

    public DepremFragmentAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<DepremItem> arrayList) {
        super(fragmentActivity);
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new DepremFragment();
        Bundle args = new Bundle();
        DepremItem depremItem = arrayList.get(position);
        args.putParcelable("data",depremItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
