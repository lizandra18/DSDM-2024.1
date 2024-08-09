package com.example.ap1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ap1.models.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<Tarefa> tarefas;

    public TarefaAdapter(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarefa, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        Tarefa tarefa = tarefas.get(position);
        holder.tituloTextView.setText(tarefa.titulo);
        holder.descricaoTextView.setText(tarefa.descricao);
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    static class TarefaViewHolder extends RecyclerView.ViewHolder {
        TextView tituloTextView;
        TextView descricaoTextView;

        TarefaViewHolder(View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.textViewTitulo);
            descricaoTextView = itemView.findViewById(R.id.textViewDescricao);
        }
    }
}
