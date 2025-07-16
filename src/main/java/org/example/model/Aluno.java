package org.example.model;

public class Aluno {
    private int id;
    private String nome;
    private String email;

    public Aluno(int id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;

    }

    public Aluno() {

    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
}