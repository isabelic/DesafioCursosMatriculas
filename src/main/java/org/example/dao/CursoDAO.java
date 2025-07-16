package org.example.dao;

import java.sql.Connection;
import org.example.util.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public List<String> alunosPorCurso() throws SQLException {
        List<String> listarHistorico = new ArrayList<>();
        String sql = "SELECT c.nome, COUNT(m.aluno_id) AS total_alunos FROM curso c  LEFT JOIN matricula m ON c.id = m.curso_id  GROUP BY c.id, c.nome  ";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listarHistorico.add(rs.getString("nome") + " - " + rs.getInt("total_alunos") + " alunos");
            }
        }
        return listarHistorico;
    }

}
