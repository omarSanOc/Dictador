package com.oaso.pro.dictador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResulActivity extends AppCompatActivity{

    private Button finish;
    String resultado[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultado = getIntent().getExtras().getStringArray("RESULTADO");

        finish = findViewById(R.id.btnFinish);
        onClick();

    }

    private void onClick(){
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(ResulActivity.this, MainActivity.class);
                //startActivity(intent);
                Toast.makeText(getApplicationContext(),resultado[2],Toast.LENGTH_SHORT).show();
            }
        });
    }

}
