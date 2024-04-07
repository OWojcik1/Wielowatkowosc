/**
 * Klasa SharedViewModel to ViewModel wykorzystywana do przechowywania i udostępniania danych pomiędzy różnymi fragmentami.
 */
package com.example.wielowatkosc_10;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    // Deklaracje LiveData dla różnych wartości timera
    // oraz metod do ustawiania i pobierania tych wartości

    private final MutableLiveData<Integer> timerValue = new MutableLiveData<>();
    private final MutableLiveData<Long> timerInterval = new MutableLiveData<>();
    private final MutableLiveData<Long> countDownValue = new MutableLiveData<>();
    private final MutableLiveData<String> timerUnit = new MutableLiveData<>();
    private final MutableLiveData<String> countDownUnit = new MutableLiveData<>();


    /**
     * Metoda setTimerValue() ustawia wartość timera.
     * @param value Wartość timera do ustawienia.
     */
    public void setTimerValue(int value) {
        timerValue.postValue(value);
    }

    /**
     * Metoda setTimerInterval() ustawia interwał czasowy timera.
     * @param interval Interwał czasowy timera do ustawienia.
     */
    public void setTimerInterval(long interval) {
        timerInterval.postValue(interval);
    }

    /**
     * Metoda setCountDownValue() ustawia wartość początkową odliczania.
     * @param startValue Wartość początkowa odliczania do ustawienia.
     */
    public void setCountDownValue(long startValue) {
        countDownValue.postValue(startValue);
    }


    /**
     * Metoda setTimerUnit() ustawia jednostkę czasu timera.
     * @param unit Jednostka czasu timera do ustawienia.
     */
    public void setTimerUnit(String unit) {
        timerUnit.setValue(unit);
    }

    /**
     * Metoda setCountDownUnit() ustawia jednostkę czasu odliczania.
     * @param unit Jednostka czasu odliczania do ustawienia.
     */
    public void setCountDownUnit(String unit) {
        countDownUnit.postValue(unit);
    }

    /**
     * Metoda getTimerUnit() zwraca obiekt LiveData zawierający jednostkę czasu timera.
     * @return Obiekt LiveData z jednostką czasu timera.
     */
    public LiveData<String> getTimerUnit() {
        return timerUnit;
    }

    /**
     * Metoda getCountDownUnit() zwraca obiekt LiveData zawierający jednostkę czasu odliczania.
     * @return Obiekt LiveData z jednostką czasu odliczania.
     */
    public LiveData<String> getCountDownUnit() {
        return countDownUnit;
    }

    /**
     * Metoda getTimerInterval() zwraca obiekt LiveData zawierający interwał czasowy timera.
     * @return Obiekt LiveData z interwałem czasowym timera.
     */
    public LiveData<Long> getTimerInterval() {
        return timerInterval;
    }

    /**
     * Metoda getCountDownValue() zwraca obiekt LiveData zawierający wartość odliczania.
     * @return Obiekt LiveData z wartością odliczania.
     */
    public LiveData<Long> getCountDownValue() {
        return countDownValue;
    }

    /**
     * Metoda getTimerValue() zwraca obiekt LiveData zawierający aktualną wartość timera.
     * @return Obiekt LiveData z aktualną wartością timera.
     */
    public LiveData<Integer> getTimerValue() {
        return timerValue;
    }

    /**
     * Metoda convertTimerInterval() konwertuje interwał czasowy timera na milisekundy zgodnie z podaną jednostką.
     * @param unit Jednostka czasu do konwersji.
     * @return Interwał czasowy timera w milisekundach.
     */
    public long convertTimerInterval(String unit) {
        long interval = timerInterval.getValue() != null ? timerInterval.getValue() : 0;
        switch (unit) {
            case "ms":
                return interval;
            case "s":
                return interval * 1000;
            case "min":
                return interval * 60000;
            case "h":
                return interval * 3600000;
            default:
                return interval;
        }
    }
}
