package com.oaso.pro.dictador;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class StartActivity extends AppCompatActivity {

    Cursor c = null;
    DataBaseManager manager;

    String text[] = new String[240];
    int i = 0;

    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        siguiente = findViewById(R.id.btnIniciar);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sincronizar inicio = new Sincronizar();
                inicio.execute();
            }
        });
    }

    private class Sincronizar extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {

            manager = new DataBaseManager(StartActivity.this);
            try {
                manager.createDataBase();
            } catch (IOException ioe) {
                throw new Error("Unable to create database");
            }

            try {
                manager.openDataBase();
            } catch (SQLException sqle) {
                throw sqle;
            }
            c = manager.query("Dictado", null, null, null, null, null, null);
            if (c.moveToFirst() == true) {
                do {
                    text[i] = c.getString(1);
                    i++;
                } while (c.moveToNext());
            }
            manager.close();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(StartActivity.this,MainActivity.class);
            intent.putExtra("DICTADO",text);
            startActivity(intent);
        }
    }

}
