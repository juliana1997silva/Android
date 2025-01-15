package com.example.agenda.dao;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorIds = 1;
    public void salva(Aluno aluno) {
        aluno.setId(contadorIds);
        alunos.add(aluno);
        atualizaIds();
    }

    private static void atualizaIds() {
        contadorIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = null;
        alunoEncontrado = buscaAluno(aluno);

        if(alunoEncontrado != null){
            int i = alunos.indexOf(alunoEncontrado);
            alunos.set(i, aluno);

        }
    }

    private static Aluno buscaAluno(Aluno aluno) {
        for (Aluno a: alunos
             ) {
            if(a.getId() == aluno.getId()){
                return  a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno alunoEncontrado) {
        Aluno alunoDevolvido = buscaAluno(alunoEncontrado);
       if(alunoDevolvido != null ) {
           alunos.remove(alunoDevolvido);
       };
    }
}
