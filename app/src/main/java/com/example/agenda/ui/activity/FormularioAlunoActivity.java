package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_INSERT = "Novo Aluno";
    private static final String TITULO_APPBAR_EDIT = "Editar Aluno";
    private static EditText nome;
    private static EditText telefone;
    private static EditText email;
    AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_aluno);

        inicializacaoDosCampos();
        carregaAluno();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizaCadastro();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)){
            setTitle(TITULO_APPBAR_EDIT);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        }else {
            setTitle(TITULO_APPBAR_INSERT);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        nome.setText(aluno.getNomeAluno());
        telefone.setText(aluno.getTelefoneAluno());
        email.setText(aluno.getEmailAluno());
    }

    private void finalizaCadastro() {
        preencheAluno();
        if(aluno.temIdValido()){
            dao.edita(aluno);
        }else {
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        //valores dos dados do formulario
        nome = findViewById(R.id.activity_formulario_aluno_nome);
        telefone = findViewById(R.id.activity_formulario_aluno_telefone);
        email = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void  preencheAluno() {
        //pega as informação que o usuario digitou
        String alunoNome =  nome.getText().toString();
        String alunoTelefone =  telefone.getText().toString();
        String alunoEmail =  email.getText().toString();

       aluno.setEmailAluno(alunoEmail);
       aluno.setNomeAluno(alunoNome);
       aluno.setTelefoneAluno(alunoTelefone);
    }
}