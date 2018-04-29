package com.oaso.pro.dictador;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private int contadorPalabras = 5;
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

    String dictado[];
    String resultado[];

    boolean isDone = false;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            dictado = getIntent().getExtras().getStringArray("DICTADO");

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

        palabras.setText(String.format("No. de palabras: %d", contadorPalabras));
        tiempoPalabra.setText(String.format("Palabras por segundo: %d", tiempo));

        onClick();
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
                if(!tts.isSpeaking() && isDone){
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("RESULTADO",resultado);
                    intent.putExtra("PALABRAS",contadorPalabras);
                    startActivity(intent);
                }
            }
        });

        plus1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (contadorPalabras < 30)
                {
                    contadorPalabras += 5;
                    palabras.setText(String.format("No. de palabras: %d", contadorPalabras));
                }
            }
        });

        min1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (contadorPalabras > 5)
                {
                    contadorPalabras -= 5;
                    palabras.setText(String.format("No. de palabras: %d", contadorPalabras));
                }
            }
        });

        plus2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if(tiempo < 8){
                    tiempo++;
                    tiempoPalabra.setText(String.format("Palabras por segundo: %d", tiempo));
                }
            }
        });

        min2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                if (tiempo > 1){
                    tiempo--;
                    tiempoPalabra.setText(String.format("Palabras por segundo: %d", tiempo));
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void speakOut(){
        Random random = new Random();
        tts.setPitch(1.0f);
        int number, i;
        resultado = new String[contadorPalabras];
        for (i=0; i < contadorPalabras; i++){
           if(i==0){
                number = random.nextInt(239);
                tts.speak(dictado[number],TextToSpeech.QUEUE_FLUSH,null);
                resultado[i] = dictado[number];
            }else{
                number = random.nextInt(239);
                tts.speak(dictado[number],TextToSpeech.QUEUE_ADD,null);
                resultado[i] = dictado[number];
            }
            tts.playSilentUtterance(tiempo*1000,TextToSpeech.QUEUE_ADD,null);
        }
        isDone = true;
    }


    @Override
    public void onInit(int status) {

        if(status == TextToSpeech.SUCCESS){

            int result = tts.setLanguage(new Locale("spa"));

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(getApplicationContext(), "This language is not supported", Toast.LENGTH_SHORT).show();
            }else{
                start.setEnabled(true);
            }
        }else{
            Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    protected void onDestroy(){

        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
