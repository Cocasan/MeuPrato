package com.example.meuprato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button Cadastrar;
    private Button Visualizar;
    private Button Produzir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cadastrar = (Button) findViewById(R.id.bTCadastro);
        Visualizar = (Button) findViewById(R.id.btnVisualiza);
        Produzir = (Button) findViewById(R.id.btnCadastro);

        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityCadastro.class);
                startActivity(intent);

            }
        });

        Visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityVisualizar.class);
                startActivity(intent);
            }
        });

        Produzir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityProduzir.class);
                startActivity(intent);
            }
        });
    }
}
