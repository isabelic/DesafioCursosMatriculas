package org.example.model;

public class Curso {
    private int id;
    private String nome;
    private String descricao;


    public Curso(int id, String nome, String descricao){
        this.id = id;
        this.nome =nome;
        this.descricao = descricao;
    }
    public Curso(){}

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Descrição: " + descricao;
    }
}