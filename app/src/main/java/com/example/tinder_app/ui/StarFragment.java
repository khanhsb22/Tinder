package com.example.tinder_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tinder_app.databinding.FragmentCategoryBinding;
import com.example.tinder_app.databinding.FragmentStarBinding;

public class StarFragment extends Fragment {
    private View v;
    private FragmentStarBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStarBinding.inflate(inflater, container, false);
        v = binding.getRoot();
        return v;
    }
}
