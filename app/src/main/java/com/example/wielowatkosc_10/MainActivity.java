/**
 * Klasa MainActivity odpowiada za główne activity aplikacji.
 */
package com.example.wielowatkosc_10;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ustawienie layoutu activity_main.xml
        setContentView(R.layout.activity_main);
    }
}