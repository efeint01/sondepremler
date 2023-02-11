package com.son.deprem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.son.deprem.databinding.FragmentDepremBinding;


public class DepremFragment extends Fragment {
    FragmentDepremBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDepremBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args == null) return;
        DepremItem depremItem = args.getParcelable("data");
        binding.magTw.setText(String.format("%s", depremItem.getMag()));
        binding.locationTw.setText(depremItem.getLokasyon());
        binding.dateTw.setReferenceTime(depremItem.getTimestamp()*1000);
        binding.progressIndicator.setProgress((int) depremItem.getMag(),true);
    }
}