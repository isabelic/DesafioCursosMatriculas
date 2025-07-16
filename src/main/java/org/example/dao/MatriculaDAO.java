package org.example.dao;



import org.example.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatriculaDAO {

    public void matricular(int alunoId, int cursoId)  {
        String sql = "INSERT INTO matricula (aluno_id, curso_id) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, alunoId);
            st.setInt(2, cursoId);
            st.executeUpdate();



        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
