package br.ufc.quixada.usoroomdatabase.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.ufc.quixada.usoroomdatabase.dao.PessoaDao;
import br.ufc.quixada.usoroomdatabase.models.Pessoa;

@Database(entities = {Pessoa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PessoaDao pessoaDao();
}
