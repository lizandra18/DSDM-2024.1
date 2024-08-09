package com.example.ap1.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ap1.models.Tarefa;

import java.util.List;

@Dao
public interface TarefaDao {
    @Query("SELECT * FROM tarefa")
    List<Tarefa> getAll();

    @Query("SELECT * FROM tarefa WHERE uid IN (:userIds)")
    List<Tarefa>loadAllByIds(int[] userIds);

    @Query("SELECT * FROM tarefa WHERE titulo LIKE :titulo AND " +
            "descricao LIKE :descricao LIMIT 1")
    Tarefa findByName(String titulo, String descricao);

    @Insert
    void insertAll(Tarefa... tarefas);

    @Query("SELECT * FROM tarefa")
    List<Tarefa> getAllTarefas();

}
