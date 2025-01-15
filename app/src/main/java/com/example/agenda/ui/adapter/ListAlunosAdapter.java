package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size(); // quantidade de item que tem na lista
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position); // retornar o item que esta na posição recebida
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId(); // retona o ID do item
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Aluno aluno = alunos.get(position);
        vinculaInfo(viewCriada, aluno);
        return viewCriada; //retorna a view personalizada que criamos
    }

    private static void vinculaInfo(View viewCriada, Aluno aluno) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        nome.setText(aluno.getNomeAluno());
        telefone.setText(aluno.getTelefoneAluno());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent, false);
    }

    public void atualiza(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged(); // notifica o dataset que teve mudancas na lista
    }


    public void remove(Aluno alunoEncontrado) {
        alunos.remove(alunoEncontrado);
        notifyDataSetChanged();
    }
}
