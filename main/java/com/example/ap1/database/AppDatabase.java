package com.example.ap1.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ap1.dao.TarefaDao;
import com.example.ap1.models.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TarefaDao tarefaDao();
}
