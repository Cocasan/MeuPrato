package com.example.meuprato;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class Prato {

    private int codigo;
    private int quantidade;
    private double precofinal;
    private String nome;
    private String ingredientes;
    private double precocusto;
    private double precoprod;
    private boolean excluir;
    private Context context;

    public Prato(Context context) {
        this.context = context;
        setCodigo(-1);
    }

    public Prato() {

    }

    public boolean isExcluir() {
        return excluir;
    }

    @Override
    public String toString() {
        return this.getNome();
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecofinal() {
        return (precocusto * 0.70)+ precocusto;
    }

    public void setPrecofinal(double precofinal) {
        this.precofinal = precofinal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public double getPrecocusto() {
        return precocusto;
    }

    public void setPrecocusto(double precocusto) {
        this.precocusto = precocusto;
    }

    public double getPrecoprod() {
        return precoprod;
    }

    public void setPrecoprod(double precoprod) {
        this.precoprod = precoprod;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }


    public double cadastro(){
        precocusto = (precocusto * 0.70)+ precocusto;
                return precofinal;
    }





    public ArrayList<Prato> getPratos() {
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<Prato> pratos = new ArrayList<>();

        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("prato", null,
                    null, null, null, null, null);

            while (cursor.moveToNext()) {
                Prato prato = new Prato(context);
                prato.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                prato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                prato.setIngredientes(cursor.getString(cursor.getColumnIndex("ingredientes")));
                prato.setPrecocusto(cursor.getDouble(cursor.getColumnIndex("precocusto")));
                prato.setPrecoprod(cursor.getDouble(cursor.getColumnIndex("precoprod")));
                pratos.add(prato);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }

        return pratos;

    }

    public ArrayList<Prato> getPratosSpinner() {
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<Prato> pratos = new ArrayList<>();

        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();


            cursor = sqLiteDatabase.query("prato", null,
                    null, null, null, null, null);


            while (cursor.moveToNext()) {
                Prato prato = new Prato(context);

                prato.setNome(cursor.getString(cursor.getColumnIndex("nome")));

                pratos.add(prato);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }

        return pratos;

    }


    public boolean Salvar() {
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;

        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            String sql = "";


            if (getCodigo() == -1) {

                sql = "INSERT INTO prato (nome,ingredientes,precocusto,precofinal, precoprod) VALUES (?,?,?,?,?)";

            } else {
                sql = "UPDATE prato set nome = ?, ingredientes = ?, precocusto = ?,precoprod =? WHERE codigo = ?";
            }

            sqLiteDatabase.beginTransaction();
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, getNome());
            sqLiteStatement.bindString(2, getIngredientes());
            sqLiteStatement.bindString(3, String.valueOf(getPrecocusto()));
            sqLiteStatement.bindString(4, String.valueOf(getPrecofinal()));
            sqLiteStatement.bindString(5, String.valueOf(111111));


            if (getCodigo() != -1)
                sqLiteStatement.bindString(4, String.valueOf(getCodigo()));
            sqLiteStatement.executeInsert();
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();


            return true;

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;

        } finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }

    }


    public boolean Excluir() {
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;

        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.delete("prato", "codigo = ?", new String[]{String.valueOf(getCodigo())});
            setExcluir(true);
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;

        } finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }

    }

    public void pesquisaPrato(int codigo) {
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;

        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();

            cursor = sqLiteDatabase.query("prato", null,
                    "codigo = ?", new String[]{String.valueOf(codigo)},
                    null, null, null);

            setExcluir(true);

            while (cursor.moveToNext()) {

                this.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                setNome(cursor.getString(cursor.getColumnIndex("nome")));
                setIngredientes(cursor.getString(cursor.getColumnIndex("ingredientes")));
                setPrecocusto(cursor.getInt(cursor.getColumnIndex("precocusto")));
                setPrecoprod(cursor.getInt(cursor.getColumnIndex("precoprod")));
                setExcluir(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }


    }

}



