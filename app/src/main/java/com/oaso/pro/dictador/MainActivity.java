package com.oaso.pro.dictador;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int contador = 5;
    private int tiempo = 1;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView palabras = findViewById(R.id.palabras);
        TextView tiempoPalabra = findViewById(R.id.tiempo);


        Button plus1 = findViewById(R.id.btnPlus1);
        Button plus2 = findViewById(R.id.btnPlus2);
        Button min1 = findViewById(R.id.btnMin1);
        Button min2 = findViewById(R.id.btnMin2);
        Button start = findViewById(R.id.btnStart);

        plus1.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));
        plus2.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));
        min1.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));
        min2.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));
        start.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));

        palabras.setText(String.format("No. de palabras: %d", contador));
        tiempoPalabra.setText(String.format("Intervalo de tiempo por palabra: %d", tiempo));
    }
}
