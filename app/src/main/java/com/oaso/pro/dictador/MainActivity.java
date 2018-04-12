package com.oaso.pro.dictador;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int contador = 5;
    private int tiempo = 1;

    private Button plus1;
    private Button plus2;
    private Button min1;
    private Button min2;
    private Button start;
    private Button result;

    private TextView palabras;
    private TextView tiempoPalabra;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        palabras = findViewById(R.id.palabras);
        tiempoPalabra = findViewById(R.id.tiempo);

        plus1 = findViewById(R.id.btnPlus1);
        plus2 = findViewById(R.id.btnPlus2);
        min1 = findViewById(R.id.btnMin1);
        min2 = findViewById(R.id.btnMin2);
        start = findViewById(R.id.btnStart);
        result = findViewById(R.id.btnResult);

        plus1.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));
        plus2.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));
        min1.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));
        min2.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));
        start.setTypeface(FontManager.getTypeface(MainActivity.this, FontManager.SOLID));

        palabras.setText(String.format("No. de palabras: %d", contador));
        tiempoPalabra.setText(String.format("Intervalo de tiempo por palabra: %d", tiempo));

        onClick();
    }

    private void onClick(){
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResulActivity.class);
                startActivity(intent);
            }
        });

        plus1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (contador < 50)
                {
                    contador += 5;
                    palabras.setText(String.format("No. de palabras: %d", contador));
                }
            }
        });

        min1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (contador > 5)
                {
                    contador -= 5;
                    palabras.setText(String.format("No. de palabras: %d", contador));
                }
            }
        });

        plus2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if(tiempo < 5){
                    tiempo++;
                    tiempoPalabra.setText(String.format("Intervalo de tiempo por palabra: %d", tiempo));
                }
            }
        });

        min2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (tiempo > 1){
                    tiempo--;
                    tiempoPalabra.setText(String.format("Intervalo de tiempo por palabra: %d", tiempo));
                }
            }
        });
    }
}
