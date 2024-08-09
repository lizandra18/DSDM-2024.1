package com.example.ap1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ap1.dao.TarefaDao;
import com.example.ap1.database.AppDatabase;
import com.example.ap1.models.Tarefa;

public class NovaTarefa extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextDescricao;
    private TarefaDao tarefaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_tarefa);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextDescricao = findViewById(R.id.editTextDescricao);

        AppDatabase appDatabase = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "db_tarefas")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        tarefaDao = appDatabase.tarefaDao();

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = editTextTitulo.getText().toString();
                String descricao = editTextDescricao.getText().toString();

                if (!titulo.isEmpty() && !descricao.isEmpty()) {
                    Tarefa novaTarefa = new Tarefa(titulo, descricao);
                    tarefaDao.insertAll(novaTarefa);
                    finish();
                }
            }
        });
    }
}
