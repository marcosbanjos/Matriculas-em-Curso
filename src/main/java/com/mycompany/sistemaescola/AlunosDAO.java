
package com.mycompany.sistemaescola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;

public class AlunosDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<AlunosDTO> lista = new ArrayList<>();

    public void cadastrarAluno(AlunosDTO objAlunoDTO) {
        String sql = "insert into aluno (nome, idade, cidade_id) values (?,?,?)";

        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objAlunoDTO.getNome());
            pstm.setInt(2, objAlunoDTO.getIdade());
            pstm.setInt(3, objAlunoDTO.getCod_Cidade());
          
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ArrayList<AlunosDTO> ListarAlunos() {
        
        String sql = "select * from aluno, cidade where aluno.cidade_id = cidade.id_cidade"; //Acrescentar esta linha
        
        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while (rs.next()) {
                AlunosDTO objAlunoDTO = new AlunosDTO();
                objAlunoDTO.setId(rs.getInt("id"));
                objAlunoDTO.setNome(rs.getString("nome"));
                objAlunoDTO.setIdade(rs.getInt("idade"));
                objAlunoDTO.setCod_Cidade(rs.getInt("id_cidade"));
                objAlunoDTO.setCidade(rs.getString("nome_cidade"));
                
                lista.add(objAlunoDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
    
    public void excluirAluno(AlunosDTO objAlunoDTO) {
        String sql = "delete from aluno where id = ?";

        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objAlunoDTO.getId());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ResultSet carregarCBX() {
        conn = Conexao.getConexao();
        String sql = "select * from cidade order by nome_cidade;";
        try {
            pstm = conn.prepareStatement(sql);
            return pstm.executeQuery();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return null;
        }
    }
}
