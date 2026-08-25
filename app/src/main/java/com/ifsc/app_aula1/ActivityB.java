package com.ifsc.app_aula1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityB extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Button b_voltar = findViewById(R.id.voltar);
        b_voltar.setOnClickListener(voltar ->{
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } );
    }
}
