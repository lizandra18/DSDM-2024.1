package com.example.ap1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.ap1.dao.TarefaDao;
import com.example.ap1.database.AppDatabase;
import com.example.ap1.models.Tarefa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private TarefaDao tarefaDao;
    private List<Tarefa> tarefasDoBd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase appDatabase = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "db_tarefas")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        tarefaDao = appDatabase.tarefaDao();

        tarefasDoBd = tarefaDao.getAllTarefas();

        tarefaAdapter = new TarefaAdapter(tarefasDoBd);
        recyclerView.setAdapter(tarefaAdapter);

        findViewById(R.id.bt_novoItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NovaTarefa.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tarefasDoBd.clear();
        tarefasDoBd.addAll(tarefaDao.getAllTarefas());
        tarefaAdapter.notifyDataSetChanged();
    }
}
