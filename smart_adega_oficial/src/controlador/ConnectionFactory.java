package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import javax.swing.JOptionPane;

public class ConnectionFactory {

	public Connection getConnection() throws ClassNotFoundException {

		try {

			String serverName = "localhost"; // caminho do servidor do BD
			String mydatabase = "smart_adega_database"; // nome do seu banco de dados
			int porta = 3306;
			String url = "jdbc:mysql://" + serverName + ":" +porta + "/" + mydatabase + "?useTimezone=true&serverTimezone=UTC";
			String user = "root"; // nome de um usuario de seu BD
			String password = ""; // sua senha de acesso
			String driver = "com.mysql.cj.jdbc.Driver";
			Connection conexao;
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		} catch (SQLException e) {
			System.out.print("Conexao com o BD falhou!!!");
			throw new RuntimeException(e);
		}
	}
}	
