package br.ufc.quixada.usoroomdatabase.dao;

import android.app.Person;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.ufc.quixada.usoroomdatabase.models.Pessoa;

@Dao
public interface PessoaDao {
    @Query("SELECT * FROM pessoa")
    List<Pessoa>getAll();

    @Query("SELECT * FROM pessoa WHERE uid IN (:userIds)")
    List<Pessoa>loadAllByIds(int[] userIds);

    @Query("SELECT * FROM pessoa WHERE nome LIKE :nome AND " +
        "curso LIKE :curso LIMIT 1")
    Pessoa findByName(String nome, String curso);

    @Insert
    void insertAll(Pessoa... pessoas);

    @Delete
    void delete(Pessoa pessoa);

    @Query("SELECT * FROM pessoa")
    List<Pessoa> getAllPessoas();

}
