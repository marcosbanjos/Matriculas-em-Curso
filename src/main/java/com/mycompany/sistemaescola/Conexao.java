package com.mycompany.sistemaescola;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
//Método de Conexão//

    public static String status = "Não conectou...";

    public static java.sql.Connection getConexao() {
        Connection conn = null; //atributo do tipo Connection
        try {
// Carregando o JDBC Driver padrão
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
// Configurando a nossa conexão com um banco de dados//
            String url = "jdbc:mysql://localhost:3306/escola";
            String username = "root"; //nome de um usuário de seu BD
            String password = ""; //sua senha de acesso
            conn = DriverManager.getConnection(url, username, password);
//Testa sua conexão//
            if (conn != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            return conn;
        } catch (ClassNotFoundException e) {
//Driver não encontrado
            JOptionPane.showMessageDialog(null, "O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
//Não conseguindo se conectar ao banco
            JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }
//Método que retorna o status da sua conexão//

    public static String statusConection() {
        return status;
    }
//Método que fecha sua conexão//

    public static boolean FecharConexao() {
        try {
            Conexao.getConexao().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
//Método que reinicia sua conexão//

    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return Conexao.getConexao();
    }
}