package com.oaso.pro.dictador;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private int contador = 5;
    private int tiempo = 1;

    private TextToSpeech tts;

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

        tts = new TextToSpeech(this,this);

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

    @Override
    public void onInit(int status) {

        if(status == TextToSpeech.SUCCESS){

            int result = tts.setLanguage(new Locale("spa"));

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                //Log.e("TTS", "This language is not supported");
                Toast.makeText(getApplicationContext(), "This language is not supported", Toast.LENGTH_SHORT).show();
            }else{
                start.setEnabled(true);
                //speakOut();

            }
        }else{
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    protected void onDestroy() {

        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private void onClick(){

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void speakOut() {
        String text[] = {"Oso", "perro", "gato", "raton"};
        String saludos = "Hola";
        tts.setPitch(1.0f);
        //tts.speak(saludos,TextToSpeech.QUEUE_FLUSH,null);
        for (int i=0; i < text.length; i++){
            if(i==0){
                tts.speak(text[i],TextToSpeech.QUEUE_FLUSH,null);
            }else{
                tts.speak(text[i],TextToSpeech.QUEUE_ADD,null);
            }
            tts.playSilentUtterance(tiempo*1000,TextToSpeech.QUEUE_ADD,null);
        }

    }














}
