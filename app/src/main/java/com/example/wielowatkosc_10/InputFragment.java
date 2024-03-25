package com.example.wielowatkosc_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InputFragment extends Fragment {
    public InputFragment() {
        super(R.layout.fragment_input);
    }

    private SharedViewModel sharedViewModel;
    private EditText timerIntervalInput;
    private EditText countDownStartInput;
    private Button applyButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        timerIntervalInput = view.findViewById(R.id.timerEditText);
        countDownStartInput = view.findViewById(R.id.countDownEditText);
        applyButton = view.findViewById(R.id.applyButton);

        applyButton.setOnClickListener(v -> applyInputs());
    }

    private void applyInputs(){
        try{
            sharedViewModel.setTimerInterval(Long.parseLong(timerIntervalInput.getText().toString()));
            sharedViewModel.setCountDownValue(Long.parseLong(countDownStartInput.getText().toString()));
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
    }
}