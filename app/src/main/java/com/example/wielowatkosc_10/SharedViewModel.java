package com.example.wielowatkosc_10;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {


    private final MutableLiveData<Integer> timerValue = new MutableLiveData<>();
    private final MutableLiveData<Long> timerInterval = new MutableLiveData<>();
    private final MutableLiveData<Long> countDownValue = new MutableLiveData<>();
    private final MutableLiveData<String> timerUnit = new MutableLiveData<>();
    private final MutableLiveData<String> countDownUnit = new MutableLiveData<>();


    public void setTimerValue(int value) {timerValue.setValue(value);}
    public void setTimerInterval(long interval) {timerInterval.setValue(interval);}
    public void setCountDownValue(long startValue) {countDownValue.setValue(startValue);}

    public void setTimerUnit(String unit){
        timerUnit.setValue(unit);
    }
    public void setCountDownUnit(String unit){
        countDownUnit.setValue(unit);
    }

    public LiveData<String> getTimerUnit(){
        return timerUnit;
    }
    public LiveData<String> getCountDownUnit(){return countDownUnit;}
    public LiveData<Long> getTimerInterval(){return timerInterval;}
    public LiveData<Long> getCountDownValue(){return countDownValue;}

    public LiveData<Integer> getTimerValue(){ return timerValue;}
}
