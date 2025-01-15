package com.example.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private int id = 0;
    private  String nomeAluno;
    private  String telefoneAluno;

    public Aluno() {

    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public void setTelefoneAluno(String telefoneAluno) {
        this.telefoneAluno = telefoneAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    private  String emailAluno;

    public Aluno(String nomeAluno, String telefoneAluno, String emailAluno) {
        this.nomeAluno = nomeAluno;
        this.telefoneAluno = telefoneAluno;
        this.emailAluno = emailAluno;
    }

    @NonNull
    @Override
    public String toString() {
        return nomeAluno;
    }

    public String getTelefoneAluno() {
        return telefoneAluno;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setId(int contadorIds) {
        this.id = contadorIds;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
