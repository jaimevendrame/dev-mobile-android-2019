package com.tads.sqliteexemplo1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try{

            //Criar banco de dados - 1
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null );

            //Criar tabela - 2
            bancoDados.execSQL(" CREATE TABLE IF NOT EXISTS pessoas ( nome VARCHAR, idade INT(2) ) ");

            //Inserir dados - 3
            bancoDados.execSQL(" INSERT INTO pessoas(nome, idade) VALUES('Jaime', 39) ");
            bancoDados.execSQL(" INSERT INTO pessoas(nome, idade) VALUES('Beatriz', 1) ");

//            //Recuperar dados - 4
//            bancoDados.rawQuery(" SELECT nome, idade FROM pessoas ", null);
//


             //Recuperar dados - 5
            Cursor cursor = bancoDados.rawQuery(" SELECT nome, idade FROM pessoas ", null);

            // 7 Indeices da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            // 8
            cursor.moveToFirst();

            // 6
            while ( cursor != null ){
                Log.i("RESULTADO - nome: ", cursor.getString(indiceNome) );
                Log.i("RESULTADO - idade: ", cursor.getString(indiceIdade) );
                //9
                cursor.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
