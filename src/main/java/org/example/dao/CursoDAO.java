package org.example.dao;

import java.sql.Connection;

import org.example.model.Curso;
import org.example.util.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {



    public void adicionarCurso (String nome, String descricao){
        String sql = "INSERT INTO curso (nome, descricao) VALUES (?,?)";

        try (Connection conn = Conexao.conectar();
        PreparedStatement st = conn.prepareStatement(sql)){

            st.setString(1,nome);
            st.setString(2,descricao);

            st.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public List<String> listarCursosComAlunos() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT c.nome AS curso_nome, a.nome AS aluno_nome " +
                "FROM curso c " +
                "JOIN matricula m ON c.id = m.curso_id " +
                "JOIN aluno a ON m.aluno_id = a.id " +
                "ORDER BY c.nome, a.nome";

        try (Connection conn = Conexao.conectar();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet resultT = st.executeQuery()) {

            String estadoCurso = null;
            List<String> alunos = new ArrayList<>();

            while (resultT.next()) {

                String nomeC = resultT.getString("curso_nome");
                String nomeA = resultT.getString("aluno_nome");


                if (estadoCurso == null) {
                    estadoCurso = nomeC;

                }

                if (!nomeC.equals(estadoCurso)) {


                    lista.add("Curso: " + estadoCurso);
                    // lista com nome dos cursos

                    lista.addAll(alunos);
                    // lista de alunos associados aos cursos

                    lista.add("---------------");
                    alunos.clear();

                    estadoCurso = nomeC;

                }

                alunos.add(" - " + nomeA);
            }


            if (estadoCurso != null) {

                lista.add("Curso: " + estadoCurso);
                // último curso que apareceu

                lista.addAll(alunos);
                // alunos relacionados aos cursos da lista
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
// retorna a lista geral

    }



        public List<String> listarCursosSemAlunos() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT c.* FROM curso c LEFT JOIN matricula m ON c.id = m.curso_id WHERE m.aluno_id IS NULL ";

        try (Connection conn = Conexao.conectar();
             PreparedStatement st = conn.prepareStatement(sql);

             ResultSet resulT = st.executeQuery()) {

            while (resulT.next()) {

                Curso c = new Curso();

                c.setId(resulT.getInt("id"));
                c.setNome(resulT.getString("nome"));
                c.setDescricao(resulT.getString("descricao"));
                lista.add(String.valueOf(c));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }


    public void removerCursoID(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            int ctt = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




