
package com.mycompany.sistemaescola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;

public class MatriculaDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<MatriculaDTO> lista = new ArrayList<>();

    public void cadastrarMatricula(MatriculaDTO objMatriculaDTO) {
        String sql = "insert into matricula (aluno_id, curso_id) values (?,?)";

        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objMatriculaDTO.getAluno_id());
            pstm.setInt(2, objMatriculaDTO.getCurso_id());
          
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ArrayList<MatriculaDTO> ListarMatricula() {
        
        String sql = "select aluno.nome, curso.nome_curso, matricula.id from matricula, aluno, curso where matricula.aluno_id = aluno.id AND matricula.curso_id = curso.id ";
        
        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while (rs.next()) {
                MatriculaDTO objMatriculaDTO = new MatriculaDTO();
                objMatriculaDTO.setId(rs.getInt("id"));
                objMatriculaDTO.setNomeAluno(rs.getString("nome"));
                objMatriculaDTO.setNomeCurso(rs.getString("nome_curso"));
                              
                lista.add(objMatriculaDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
    
    public void excluirMatricula(MatriculaDTO objMatriculaDTO) {
        String sql = "delete from matricula where id = ?";

        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objMatriculaDTO.getId());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public ResultSet carregarCBXAluno() {
        conn = Conexao.getConexao();
        String sql = "select * from aluno order by nome;";
        try {
            pstm = conn.prepareStatement(sql);
            return pstm.executeQuery();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return null;
        }
    }
    
    public ResultSet carregarCBXCurso() {
        conn = Conexao.getConexao();
        String sql = "select * from curso order by nome_curso;";
        try {
            pstm = conn.prepareStatement(sql);
            return pstm.executeQuery();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return null;
        }
    }
}
