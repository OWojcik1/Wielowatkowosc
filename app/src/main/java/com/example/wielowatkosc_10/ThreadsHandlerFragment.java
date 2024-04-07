/**
 * Klasa ThreadsHandlerFragment to fragment odpowiedzialny za obsługę wątków timerów.
 */

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
import java.util.concurrent.TimeUnit;


public class ThreadsHandlerFragment extends Fragment {
    public ThreadsHandlerFragment() {
        super(R.layout.fragment_threads_handler);
    }

    private SharedViewModel sharedViewModel;
    private Timer timer;
    private Timer countDownTimer;
    private boolean isTimerRunning = false;
    private boolean isCountDownRunning = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_threads_handler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        Button startTimerButton = view.findViewById(R.id.startTimerButton);
        Button stopTimerButton = view.findViewById(R.id.stopTimerButton);
        Button startCountDownButton = view.findViewById(R.id.startCountdownButton);
        Button stopCountDownButton = view.findViewById(R.id.stopCountdownButton);

        startTimerButton.setOnClickListener(v -> {
            try{
                startTimer();
            }catch(Exception e){
                e.printStackTrace();
            }
        });
        stopTimerButton.setOnClickListener(v -> stopTimer());
        startCountDownButton.setOnClickListener(v -> startCountDown());
        stopCountDownButton.setOnClickListener(v -> stopCountDown());
    }

    /**
     * Metoda startTimer() rozpoczyna działanie timera.
     * @throws IllegalArgumentException Jeśli interwał czasowy nie został ustawiony lub jest nieprawidłowy.
     */
    private void startTimer() throws IllegalArgumentException {
        if (sharedViewModel.getTimerInterval().getValue() == null)
            throw new IllegalArgumentException("Podano nieprawdidlowa wartosc interwalu!");

        if (!isTimerRunning) {
            timer = new Timer();
            isTimerRunning = true;
            String timerUnit = sharedViewModel.getTimerUnit().getValue();
            long interval = sharedViewModel.convertTimerInterval(timerUnit);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    sharedViewModel.setTimerValue(new Random().nextInt(100));
                }
            }, 0, interval);
        }
    }

    /**
     * Metoda stopTimer() zatrzymuje działanie timera.
     */
    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
            isTimerRunning = false;
        }
    }

    /**
     * Metoda startCountDown() rozpoczyna odliczanie.
     */
    private void startCountDown() {
        if (!isCountDownRunning && sharedViewModel.getCountDownValue().getValue() != null) {
            isCountDownRunning = true;
            countDownTimer = new Timer();

            String countDownUnit = sharedViewModel.getCountDownUnit().getValue();
            long intervalInMillis = TimeUnit.SECONDS.toMillis(1);

            switch (countDownUnit) {
                case "s":
                    intervalInMillis = TimeUnit.SECONDS.toMillis(1);
                    break;
                case "min":
                    intervalInMillis = TimeUnit.MINUTES.toMillis(1);
                    break;
                case "h":
                    intervalInMillis = TimeUnit.HOURS.toMillis(1);
                    break;
            }

            countDownTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    long remaining = sharedViewModel.getCountDownValue().getValue();
                    if (remaining > 0) {
                        remaining--;
                        sharedViewModel.setCountDownValue(remaining);
                    } else {
                        stopCountDown();
                    }
                }
            }, 0, intervalInMillis);
        }
    }

    /**
     * Metoda stopCountDown() zatrzymuje odliczanie.
     */
    private void stopCountDown(){
        if(countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer = null;
            isCountDownRunning = false;
        }
    }
}