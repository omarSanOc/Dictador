package com.oaso.pro.dictador;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseManager extends SQLiteOpenHelper{

    String routeStore;
    SQLiteDatabase bd;



    public DataBaseManager(Context context) {
        super(context, "palabras.db3", null, 1);
        routeStore = context.getFilesDir().getParentFile().getPath() + "palabras.db3";
    }

    public void openDataBase(Context context){
        try{

          bd = SQLiteDatabase.openDatabase(routeStore,null, SQLiteDatabase.OPEN_READONLY);

        }catch (SQLException e){

            copyDataBase(context);
            bd = SQLiteDatabase.openDatabase(routeStore,null, SQLiteDatabase.OPEN_READONLY);

        }
    }

    private void copyDataBase(Context context){

        try{

            InputStream datosEntrada = context.getAssets().open("palabras.db3");
            OutputStream datosSalida = new FileOutputStream(routeStore);

            byte[] bufferBD = new byte[1024];
            int longitud;

            while((longitud=datosEntrada.read(bufferBD))>0){
                datosSalida.flush();
                datosSalida.close();
                datosEntrada.close();
            }

        }catch (Exception e){

        }
    }

    public String datosPalabra(int id){

        String palabra;
        Cursor cursor;

        cursor = bd.rawQuery("SELECT * FROM Datos palabras WHERE id=" + id, null);
        cursor.moveToFirst();
        palabra = cursor.getString(1);
        cursor.close();
        return palabra;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
