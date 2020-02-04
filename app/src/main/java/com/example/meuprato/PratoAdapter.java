package com.example.meuprato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PratoAdapter extends ArrayAdapter<Prato> {

    private ArrayList<Prato> pratos;

    public PratoAdapter(Context context, ArrayList<Prato> pratos) {
        super(context, 0, pratos);
        this.pratos = pratos;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Prato prato = pratos.get(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_prato, null);

        TextView TVNome = (TextView) convertView.findViewById(R.id.TVNome);
        TextView TVPrecocusto = (TextView) convertView.findViewById(R.id.TVPrecoCusto);
        TextView TVIngrediente = (TextView) convertView.findViewById(R.id.TVIngrediente);
        TextView TVPrecoprod = (TextView) convertView.findViewById(R.id.TVPrecoProd);


        TVNome.setText(prato.getNome().toString());
        TVIngrediente.setText(prato.getIngredientes().toString());
        TVPrecoprod.setText(String.valueOf(prato.getPrecoprod()));
        TVPrecocusto.setText(String.valueOf(prato.getPrecocusto()));
        return convertView;


    }
}
