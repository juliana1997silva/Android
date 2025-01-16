package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListAlunosAdapter;

public class ListAlunosView {
    private final AlunoDAO dao;
    private final ListAlunosAdapter adapter;
    private final Context context;

    public ListAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListAlunosAdapter(this.context);
        this.dao = new AlunoDAO();
    }

    public void removeAluno(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        assert menuInfo != null;
        Aluno aluno = adapter.getItem(menuInfo.position);
        remove(aluno);
    }

    private void remove(Aluno alunoEncontrado) {
        dao.remove(alunoEncontrado);
        adapter.remove(alunoEncontrado);
    }
    public void confirmarRemoverAluno(@NonNull MenuItem item) {
        //cria um alerta (popup)
        new AlertDialog.Builder(this.context)
                .setTitle("Remover Aluno")
                .setMessage("Tem certeza que deseja remover o aluno ?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeAluno(item);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    public void configuraAdapter(ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }
}
