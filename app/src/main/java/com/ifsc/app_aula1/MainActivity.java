package com.ifsc.app_aula1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    //int contador;
    int numeroAleatorio;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //-- cria as variaveis do tipo função.
        Button button = findViewById(R.id.button);

        //-- cria as variaveis do tipo textView.
        TextView textView = findViewById(R.id.textView);
        TextView edMin = findViewById(R.id.edMin);
        TextView edMax = findViewById(R.id.edMax);

        button.setOnClickListener( view -> {
            //textView.setText("olaaaa");
            //contador++;

            String smin = edMin.getText().toString();
            String smax = edMax.getText().toString();

            if(smin.isBlank()){
                edMin.setError("Informe um inteiro");
                return;
            }
            if(smax.isBlank()){
                edMax.setError("Informe um inteiro");
                return;
            }

            //-- passa o valor do campo para as variaveis.
            int numeroMinumo = Integer.parseInt(smin);
            int numeroMaximo = Integer.parseInt(smax);

            if(numeroMinumo > numeroMaximo){
                Toast.makeText(this, "Informe o maximo maior que o minimo", Toast.LENGTH_SHORT).show();
                return;
            }

            //-- sorteia o número e passa para a variavel.
            numeroAleatorio = random.nextInt(numeroMinumo,numeroMaximo);
            textView.setText(Integer.toString(numeroAleatorio));

        });
    }
}