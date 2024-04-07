/**
 * Klasa ShowTimersFragment to fragment odpowiedzialny za wyświetlanie timerów.
 */
package com.example.wielowatkosc_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowTimersFragment extends Fragment {
    public ShowTimersFragment() {
        super(R.layout.fragment_show_timers);
    }

    private SharedViewModel sharedViewModel;
    private TextView timerTextView;
    private TextView countDownTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        timerTextView = view.findViewById(R.id.timerTextView);
        countDownTextView = view.findViewById(R.id.countDownTextView);

        sharedViewModel.getTimerValue().observe(getViewLifecycleOwner(), interval -> {
            timerTextView.setText("Interwał: " + interval);
        });
        sharedViewModel.getCountDownValue().observe(getViewLifecycleOwner(), value -> {
            countDownTextView.setText("Odliczanie: " + value);
        });
    }
}