package br.ufc.quixada.usoroomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufc.quixada.usoroomdatabase.models.Pessoa;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder> {

    private List<Pessoa> pessoas;

    public PessoaAdapter(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new PessoaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder holder, int position) {
        Pessoa pessoa = pessoas.get(position);
        holder.nomeTextView.setText(pessoa.nome);
        holder.cursoTextView.setText(pessoa.curso);
        holder.idadeTextView.setText(String.valueOf(pessoa.idade));
    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }

    public static class PessoaViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeTextView;
        public TextView cursoTextView;
        public TextView idadeTextView;

        public PessoaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.textViewNome);
            cursoTextView = itemView.findViewById(R.id.textViewCurso);
            idadeTextView = itemView.findViewById(R.id.textViewIdade);
        }
    }
}
