package org.example.dao;

import org.example.model.Aluno;
import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void adicionar(String nome, String email) {

        String sql = "INSERT INTO aluno (nome,email) VALUES (?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement st = conn.prepareStatement(sql)) {


            st.setString(1, nome);
            st.setString(2, email);
            st.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Aluno> listarTodos() {
        List<Aluno> list = new ArrayList<>();

        String sql = "SELECT id, nome, email FROM aluno ";

        try (Connection conn = Conexao.conectar();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet resultS = st.executeQuery()) {

            while (resultS.next()) {
                Aluno alu = new Aluno(
                        resultS.getInt("id"),
                        resultS.getString("nome"),
                        resultS.getString("email")
                );
                list.add(alu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Aluno> listarNaoMatriculados()  {
        List<Aluno> list = new ArrayList<>();
        String sql = "SELECT id, nome, email FROM aluno a  LEFT JOIN matricula m ON a.id = m.aluno_id  WHERE m.curso_id IS NULL ";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);

             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));
                list.add(a);
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return list;
    }


    public void removerPorID(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement st = conn.prepareStatement(sql)) {


            st.setInt(1, id);
            int alunoRemovido = st.executeUpdate();

            if (alunoRemovido > 0) {
                System.out.println("Aluno removido com sucesso!");
            } else {
                System.out.println("Aluno não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
