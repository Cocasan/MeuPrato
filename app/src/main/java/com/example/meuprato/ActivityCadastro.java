package com.example.meuprato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityCadastro extends AppCompatActivity implements View.OnClickListener{

    private EditText eTNome;
    private EditText eTIngredientes;
    private EditText eTPreco;
    private Button btnCadastro;
    private Button btnCancelar;
    private Button btnExcluir;

    private final Prato prato = new Prato(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        eTNome = (EditText) findViewById(R.id.eTNome);
        eTIngredientes = (EditText) findViewById(R.id.eTIngredientes);
        eTPreco = (EditText) findViewById(R.id.eTPreco);
        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        btnCadastro.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        btnExcluir.setOnClickListener(this);
        if(getIntent().getExtras() != null){
            setTitle(getString(R.string.titulo_editando));
            int codigo = getIntent().getExtras().getInt("consulta");
            prato.pesquisaPrato(codigo);

            eTNome.setText(prato.getNome());
            eTIngredientes.setText(prato.getIngredientes());
            eTPreco.setText(String.valueOf(prato.getPrecocusto()));


        }else {
            setTitle(getString(R.string.titulo_incluindo));
        }

        btnExcluir.setEnabled(true);
        if (prato.getCodigo() == -1)
            btnExcluir.setEnabled(false);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnExcluir: {
                prato.Excluir();
                finish();
                break;
            }
            case R.id.btnCadastro: {
                boolean valido = true;
                prato.setNome(eTNome.getText().toString());
                prato.setIngredientes(eTIngredientes.getText().toString());
                prato.setPrecocusto(Integer.parseInt(eTPreco.getText().toString()));

                if(prato.getNome().equals("")){
                    eTNome.setError(getString(R.string.obrigatorio));
                    valido = false;
                }
                if(prato.getIngredientes().equals("")){
                    eTIngredientes.setError(getString(R.string.obrigatorio));
                    valido = false;
                }
                if(prato.getPrecocusto() == 0){
                    eTPreco.setError(getString(R.string.obrigatorio));
                    valido = false;
                }

                if(valido){
                    prato.Salvar();
                    finish();
                }
                break;
            }
            case R.id.btnCancelar: {
                finish();
                break;
            }
        }

    }

}

