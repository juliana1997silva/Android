package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosTeste();
    }

    private static void criaAlunosTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Juliana Jesus", "11999996666", "juliana@gmail.com"));
        dao.salva(new Aluno("Antonio Santos", "11999998888", "antonio@gmail.com"));

    }
}
