package com.example.wielowatkosc_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class ThreadsHandlerFragment extends Fragment {
    public ThreadsHandlerFragment() {
        super(R.layout.fragment_threads_handler);
    }

    private SharedViewModel sharedViewModel;
    private Timer timer;
    private Timer countDownTimer;
    private long countDownRemaining;
    private boolean isTimerRunning = false;
    private boolean isCountDownRunning = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        Button startTimerButton = view.findViewById(R.id.startTimerButton);
        Button stopTimerButton = view.findViewById(R.id.stopTimerButton);
        Button startCountDownButton = view.findViewById(R.id.startCountdownButton);
        Button stopCountDownButton = view.findViewById(R.id.stopCountdownButton);

        startTimerButton.setOnClickListener(v -> startTimer());
        stopTimerButton.setOnClickListener(v -> stopTimer());
        startCountDownButton.setOnClickListener(v -> startCountDown());
        stopCountDownButton.setOnClickListener(v -> stopCountDown());
    }

    private void startTimer() {
        if(!isTimerRunning){
            timer = new Timer();
            isTimerRunning = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    sharedViewModel.setTimerValue(new Random().nextInt(100));
                }
            }, 0, sharedViewModel.getTimerInterval().getValue());
        }
    }
    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
            isTimerRunning = false;
        }
    }

    private void startCountDown(){
        if(!isCountDownRunning && sharedViewModel.getCountDownValue().getValue() != null){

            isCountDownRunning = true;
            countDownTimer = new Timer();

            countDownTimer.scheduleAtFixedRate(new TimerTask() {
                long remaining = sharedViewModel.getCountDownValue().getValue();

                @Override
                public void run() {
                    if(remaining > 0){
                        remaining--;
                        sharedViewModel.setCountDownValue(remaining);
                    }else{
                        stopCountDown();
                    }
                }
            }, 0, 1000);
        }
    }

    private void stopCountDown(){
        if(countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer = null;
            isCountDownRunning = false;
        }
    }
}