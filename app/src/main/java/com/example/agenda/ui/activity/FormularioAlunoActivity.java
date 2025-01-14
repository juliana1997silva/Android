package com.example.agenda.ui.activity;

import android.os.Bundle;
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

    public static final String TITULO_APPBAR = "Novo Aluno";
    private static EditText nome;
    private static EditText telefone;
    private static EditText email;
    AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);

        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = criarAluno();
                salvar(aluno);
            }
        });
    }

    private void inicializacaoDosCampos() {
        //valores dos dados do formulario
        nome = findViewById(R.id.activity_formulario_aluno_nome);
        telefone = findViewById(R.id.activity_formulario_aluno_telefone);
        email = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvar(Aluno aluno) {
        //salvar o aluno e finaliza a activity
        dao.salva(aluno);
        finish();
    }

    @NonNull
    private static Aluno criarAluno() {
        //pega as informação que o usuario digitou
        String nomeAluno =  nome.getText().toString();
        String telefoneAluno =  telefone.getText().toString();
        String emailAluno =  email.getText().toString();

        //criar um aluno novo
        Aluno alunoCriado = new Aluno(nomeAluno,telefoneAluno,emailAluno);
        return alunoCriado;
    }
}