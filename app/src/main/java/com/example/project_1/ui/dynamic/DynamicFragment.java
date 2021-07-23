package com.example.project_1.ui.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_1.databinding.FragmentDynamicBinding;
import com.example.project_1.databinding.FragmentDynamicBinding;

public class DynamicFragment extends Fragment {

    private DynamicViewModel dynamicViewModel;
private FragmentDynamicBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        dynamicViewModel =
                new ViewModelProvider(this).get(DynamicViewModel.class);

    binding = FragmentDynamicBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textDynamic;
        dynamicViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}