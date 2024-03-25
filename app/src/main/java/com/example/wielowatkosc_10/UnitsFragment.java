package com.example.wielowatkosc_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class UnitsFragment extends Fragment {
    public UnitsFragment() {
        super(R.layout.fragment_units);
    }
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_units, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        Spinner countDownSpinner = (Spinner) view.findViewById(R.id.countDownSpinner);
        Spinner timerSpinner = (Spinner) view.findViewById(R.id.timerSpinner);

        ArrayAdapter<CharSequence> countDownAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.countDownSpinnerItems, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> timerAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.timerSpinnerItems, android.R.layout.simple_spinner_item);


        countDownSpinner.setAdapter(countDownAdapter);
        timerSpinner.setAdapter(timerAdapter);

        timerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedUnit = (String) adapterView.getItemAtPosition(i);
                sharedViewModel.setTimerUnit(selectedUnit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        countDownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedUnit = (String) adapterView.getItemAtPosition(i);
                sharedViewModel.setCountDownUnit(selectedUnit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}