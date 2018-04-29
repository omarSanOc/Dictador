package com.oaso.pro.dictador;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class ResultActivity extends AppCompatActivity{

    RecyclerView listaPalabras;
    RecyclerView.LayoutManager layoutManager;

    private Button finish;
    String resultado[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultado = getIntent().getExtras().getStringArray("RESULTADO");

        listaPalabras = findViewById(R.id.listaPalabras);
        layoutManager = new LinearLayoutManager(this);
        listaPalabras.setLayoutManager(layoutManager);
        listaPalabras.setAdapter(new Adapter(this, resultado));

        finish = findViewById(R.id.btnFinish);
        finish.setTypeface(FontManager.getTypeface(ResultActivity.this, FontManager.SOLID));
        onClick();

    }

    private void onClick(){
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
