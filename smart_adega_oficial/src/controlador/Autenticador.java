package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import VOs.Tipos;
//import VOs.Bebidas;

/**
 * Servlet implementation class Autenticador
 */
@WebServlet("/Autenticador")
public class Autenticador extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();

		int id = 0;
		String tipo = null;
		float temp_de_servico = 0;
		float tolerancia = 0;
		int reserva = 0;
		int idade = 0;
		
		int naadega = 0;
		//int id_fk_adega = 0;
		//int notnull = 0;
		
		String email;
		String senha;

		email = request.getParameter("email");
		senha = request.getParameter("senha");
		System.out.println(senha);

		
		Connection con = null;

		try {
			con = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String sql = "SELECT * FROM login WHERE email = ? AND senha = ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
			
				String sql2 = "SELECT * FROM propriedades";
				PreparedStatement pstm2 = con.prepareStatement(sql2);
				ResultSet rs2 = pstm2.executeQuery();
				ArrayList<Tipos> list2 = new ArrayList<Tipos>();
				while (rs2.next()) {

					Tipos tipos = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);
					tipos.setId(rs2.getInt(1));

					tipos.setTipo(rs2.getString(2));

					tipos.setTemp_de_servico(rs2.getFloat(3));

					tipos.setTolerancia(rs2.getFloat(4));

					tipos.setReserva(rs2.getInt(5));

					tipos.setIdade(rs2.getInt(6));

					tipos.setNaadega(rs2.getInt(7));

					// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

					list2.add(tipos);

					// String notnull = rs2.getString(2);

					// String nometipo1 = rs2.getString(2);

					session.setAttribute("tipo", list2);

					// session.setAttribute("nometipo", rs2.getString(2));

					// session.setAttribute("idtipo", id_fk);

				}

				// session.setAttribute("mensagem", "Usuário Autenticado!");
				String sql3 = "SELECT * FROM propriedades";
				PreparedStatement pstm3 = con.prepareStatement(sql3);
				ResultSet rs3 = pstm3.executeQuery();
				if (!rs3.isBeforeFirst()) {
					session.setAttribute("nometipo1", null);
				} 
				session.setAttribute("login", "true");
				response.sendRedirect("monitor.jsp");

			} else {
				session.setAttribute("mensagem", "Usuário Não Autenticado!");
				session.setAttribute("login", "false");
				response.sendRedirect("index.jsp");
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} 
}
