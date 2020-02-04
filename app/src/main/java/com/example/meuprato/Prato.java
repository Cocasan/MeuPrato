package com.example.meuprato;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class Prato {

    private int codigo;
    private int precofinal;
    private String nome;
    private String ingredientes;
    private int precocusto;
    private int precoprod;
    private boolean excluir;
    private Context context;

    public Prato(Context context) {
        this.context = context;
        codigo = -1;
    }



    public int getCodigo() {return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }


    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }


    public String getIngredientes() { return ingredientes;}

    public void setIngredientes(String ingredientes) {this.ingredientes=ingredientes;}


    public int getPrecocusto() {return precocusto;}

    public void setPrecocusto(int precocusto) {this.precocusto=precocusto;}


    public int getPrecoprod() {return precoprod;}

    public void setPrecoprod(int precoprod) {this.precoprod=precoprod;}


    public boolean isExcluir() { return excluir; }

    public void setExcluir(boolean excluir) { this.excluir = excluir; }

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
                prato.codigo = cursor.getInt(cursor.getColumnIndex("codigo"));
                prato.nome = cursor.getString(cursor.getColumnIndex("nome"));
                prato.ingredientes = cursor.getString(cursor.getColumnIndex("ingredientes"));
                prato.precocusto = cursor.getInt(cursor.getColumnIndex("precocusto"));
                prato.precoprod = cursor.getInt(cursor.getColumnIndex("precoprod"));
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


            if (codigo == -1) {

                sql = "INSERT INTO prato (nome,ingredientes,precocusto,precoprod) VALUES (?,?,?,?)";

            } else {
                sql = "UPDATE prato set nome = ?, ingredientes = ?, precocusto = ?,precoprod =? WHERE codigo = ?";
            }

        sqLiteDatabase.beginTransaction();
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, nome);
        sqLiteStatement.bindString(2, ingredientes);
        sqLiteStatement.bindString (3, String.valueOf(precocusto));
        sqLiteStatement.bindString (4, String.valueOf(23));


        if (codigo != -1)
            sqLiteStatement.bindString(4, String.valueOf(codigo));
        sqLiteStatement.executeInsert();
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();


        return true;

    }  catch (Exception e) {
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
            sqLiteDatabase.delete("prato", "codigo = ?", new String[]{String.valueOf(codigo)});
            excluir = true;
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

                 excluir = true;

                 while (cursor.moveToNext()) {

                     this.codigo = cursor.getInt(cursor.getColumnIndex("codigo"));
                     nome = cursor.getString(cursor.getColumnIndex("nome"));
                     ingredientes = cursor.getString(cursor.getColumnIndex("ingredientes"));
                     precocusto = cursor.getInt(cursor.getColumnIndex("precocusto"));
                     precoprod = cursor.getInt(cursor.getColumnIndex("precoprod"));
                     excluir = false;
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

