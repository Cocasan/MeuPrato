package com.example.meuprato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityVisualizar extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listViewPratos;
    private Button btnVoltar;
    private PratoAdapter pratoAdapter;
    private ArrayList<Prato> pratos;
    private Prato pratoEdicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        setTitle("Visualizar Pratos");

        listViewPratos = (ListView) findViewById(R.id.listViewPratos);
       btnVoltar = (Button) findViewById(R.id.btnVoltar);

       btnVoltar.setOnClickListener(this);
        listViewPratos.setOnItemClickListener(this);

        pratos = new Prato(this).getPratos();
        pratoAdapter = new PratoAdapter(this,pratos);
        listViewPratos.setAdapter(pratoAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Prato prato = pratos.get(position);
        Intent intent = new Intent(this,ActivityCadastro.class);
        intent.putExtra("consulta",prato.getCodigo());
        pratoEdicao = prato;
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pratoEdicao != null){
            pratoEdicao.pesquisaPrato(pratoEdicao.getCodigo());
            if(pratoEdicao.isExcluir()){
                pratos.remove(pratoEdicao);
            }
        }
    }
}
