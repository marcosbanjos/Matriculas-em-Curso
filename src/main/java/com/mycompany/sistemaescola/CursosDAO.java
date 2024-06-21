package com.mycompany.sistemaescola;

   
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;

public class CursosDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<CursoDTO> lista = new ArrayList<>();

    public void cadastrarCurso(CursoDTO objCursoDTO) {
        String sql = "insert into Curso (nome_curso, carga_horaria) values (?,?)";

        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objCursoDTO.getNome());
            pstm.setInt(2, objCursoDTO.getCarga_horaria());
          
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ArrayList<CursoDTO> ListarCursos() {
        
        String sql = "select * from curso"; //Acrescentar esta linha
        
        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while (rs.next()) {
                CursoDTO objCursoDTO = new CursoDTO();
                objCursoDTO.setId(rs.getInt("id"));
                objCursoDTO.setNome(rs.getString("nome_curso"));
                objCursoDTO.setCarga_horaria(rs.getInt("carga_horaria"));
                
                
                lista.add(objCursoDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
    
    public void excluirCurso(CursoDTO objCursoDTO) {
        String sql = "delete from curso where id = ?";

        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objCursoDTO.getId());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
 
