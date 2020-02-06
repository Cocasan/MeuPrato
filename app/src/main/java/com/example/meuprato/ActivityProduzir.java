package com.example.meuprato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityProduzir extends AppCompatActivity {

    //Spinner spnPratos;
    List<Prato> listaPrato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produzir);
        Prato pratos = new Prato();

        Spinner spinner=findViewById(R.id.spnPratos);
        ArrayList<Prato> list= pratos.getPratosSpinner();
        ArrayAdapter<Prato> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);

    }

}
