package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListAlunosActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de Alunos";

    private final AlunoDAO dao = new AlunoDAO();
    private ListAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_alunos);
        setTitle(TITLE_APPBAR);

        configuraFabNovoAluno();
        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    //criar menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int idItem = item.getItemId(); // pega o id do item
        if(idItem == R.id.activity_list_alunos_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno aluno = adapter.getItem(menuInfo.position);
            remove(aluno);
        }
        return super.onContextItemSelected(item);
    }

    private void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton novoAluno = findViewById(R.id.activity_list_alunos_fab_novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAluno();
            }
        });
    }

    private void insertAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }
    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_list_alunos_listview);
        configuraAdapter(listaAlunos);
        configuraListenerCliqueItem(listaAlunos);
        //configuraListenerItemClickLong(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void remove(Aluno alunoEncontrado) {
        dao.remove(alunoEncontrado);
        adapter.remove(alunoEncontrado);
    }

    private void configuraListenerCliqueItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno edit = (Aluno) parent.getItemAtPosition(position); // pega o objeto da lista que foi enviada anteriormente para o adapter
                editarAluno(edit);
            }
            }
        );
    }

    private void editarAluno(Aluno edit) {
        Intent dados = new Intent(ListAlunosActivity.this, FormularioAlunoActivity.class);
        dados.putExtra(CHAVE_ALUNO, edit);
        startActivity(dados);
    }

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ListAlunosAdapter(this);
        listaAlunos.setAdapter(adapter);
    }
}
