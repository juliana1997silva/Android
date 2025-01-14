package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAlunosActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_alunos);
        setTitle(TITLE_APPBAR);

        configuraFabNovoAluno();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }
    private void configuraFabNovoAluno() {
        FloatingActionButton novoAluno = findViewById(R.id.activity_list_alunos_fab_novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFormulario();
            }
        });
    }

    private void abrirFormulario() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }
    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_list_alunos_listview);
        listaAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()));
    }
}
