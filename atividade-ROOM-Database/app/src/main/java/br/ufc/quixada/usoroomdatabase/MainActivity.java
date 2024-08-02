package br.ufc.quixada.usoroomdatabase;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import br.ufc.quixada.usoroomdatabase.dao.PessoaDao;
import br.ufc.quixada.usoroomdatabase.database.AppDatabase;
import br.ufc.quixada.usoroomdatabase.models.Pessoa;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PessoaAdapter pessoaAdapter;
    private PessoaDao pessoaDao;
    private List<Pessoa> pessoasDoBd;
    private EditText editTextNome;
    private EditText editTextCurso;
    private EditText editTextIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCurso = findViewById(R.id.editTextCurso);
        editTextIdade = findViewById(R.id.editTextIdade);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        AppDatabase appDatabase = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "db_pessoas")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        pessoaDao = appDatabase.pessoaDao();

        pessoasDoBd = pessoaDao.getAllPessoas();
        for (Pessoa p : pessoasDoBd) {
            Log.d("sid-tag", p.toString());
        }

        pessoaAdapter = new PessoaAdapter(pessoasDoBd);
        recyclerView.setAdapter(pessoaAdapter);

        swipeToDelete();

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPessoa();
            }
        });
    }

    private void swipeToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pessoa pessoa = pessoasDoBd.get(position);
                pessoaDao.delete(pessoa);
                pessoasDoBd.remove(position);
                pessoaAdapter.notifyItemRemoved(position);
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void addPessoa() {
        String nome = editTextNome.getText().toString();
        String curso = editTextCurso.getText().toString();
        int idade = Integer.parseInt(editTextIdade.getText().toString());

        Pessoa novaPessoa = new Pessoa(nome, curso, idade);
        pessoaDao.insertAll(novaPessoa);

        pessoasDoBd.add(novaPessoa);
        pessoaAdapter.notifyItemInserted(pessoasDoBd.size() - 1);

        editTextNome.setText("");
        editTextCurso.setText("");
        editTextIdade.setText("");
    }

}
