package br.ufc.quixada.usoroomdatabase.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pessoa")
public class Pessoa {
    // : Id, Nome, Curso e Idade
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "nome")
    public String nome;
    @ColumnInfo(name = "curso")
    public String curso;
    @ColumnInfo(name = "idade")
    public int idade;


    public Pessoa(String nome, String curso, int idade){
        this.nome = nome;
        this.curso = curso;
        this.idade = idade;
    }

    @NonNull
    @Override
    public String toString() {
        String nomeRetorno = this.uid + " | " + this.nome + " | " + this.curso + " | " + this.idade;
        return nomeRetorno ;
    }
}
