
package com.mycompany.sistemaescola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;

public class CidadesDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<CidadesDTO> lista = new ArrayList<>();

    public void cadastrarCidade(CidadesDTO objCidadesDTO) {
        String sql = "insert into cidade (nome_cidade) values (?)";

        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objCidadesDTO.getNome_cidade());
          
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ArrayList<CidadesDTO> ListarCidades() {
        
        String sql = "select * from cidade"; //Acrescentar esta linha
        
        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while (rs.next()) {
                CidadesDTO objCidadesDTO = new CidadesDTO();
                objCidadesDTO.setId_cidade(rs.getInt("id_cidade"));
                objCidadesDTO.setNome_cidade(rs.getString("nome_cidade"));
                               
                lista.add(objCidadesDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return lista;
    }
    
    public void excluirCidade(CidadesDTO objCidadesDTO) {
        String sql = "delete from cidade where id_cidade = ?";

        conn = Conexao.getConexao();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objCidadesDTO.getId_cidade());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
