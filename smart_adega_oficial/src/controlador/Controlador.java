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
import VOs.Bebidas;

//import VOs.Informacoes;

//import VOs.Informacoes;
//import VOs.Tipos;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")

public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		int tabela;
		int tipocrud;
		tabela = Integer.parseInt(request.getParameter("tabela"));
		tipocrud = Integer.parseInt(request.getParameter("tipocrud"));

		HttpSession session = request.getSession();

		int id = 0;
		String tipo = null;
		float temp_de_servico = 0;
		float tolerancia = 0;
		int reserva = 0;
		int idade = 0;
		int acesso = 0;

		int naadega = 0;
		int id_fk_adega = 0;
		int notnull = 0;
		int novoid = 0;
		float tatual = 0;
		

		int id2 = 0;
		int id_fk = 0;
		int quantidade = 0;
		String marca = null;
		String origem = null;
		String nome = null;

		// System.out.println("TESTE MONITOR 3");
		try {
			Connection con = new ConnectionFactory().getConnection();

			if (tabela == 1) { // tipo
				if (acesso != 0) {
					tipocrud = 11;
				}

				switch (tipocrud) {
				case 11: // listar
				{

					String sql = "SELECT * FROM propriedades";
					PreparedStatement pstm = con.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					ArrayList<Tipos> list = new ArrayList<Tipos>();

					while (rs.next()) {

						Tipos tipos = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);

						tipos.setId(rs.getInt(1));

						tipos.setTipo(rs.getString(2));

						tipos.setTemp_de_servico(rs.getFloat(3));

						tipos.setTolerancia(rs.getFloat(4));

						tipos.setReserva(rs.getInt(5));

						tipos.setIdade(rs.getInt(6));

						tipos.setNaadega(rs.getInt(7));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						list.add(tipos);

						session.setAttribute("tipo", list);

						session.setAttribute("notnull", notnull);

						// System.out.println("TESTE");
					}
					response.sendRedirect("gerenciadortipos.jsp");
					con.close();
					break;
				}

				case 12: {

					String sql11 = "select auto_increment from information_schema.tables where table_name = 'propriedades' and table_schema = 'smart_adega_database'";
					PreparedStatement pstm11 = con.prepareStatement(sql11);
					ResultSet rs11 = pstm11.executeQuery();
					// ArrayList<Tipos> list11 = new ArrayList<Tipos>();

					while (rs11.next()) {
						novoid = rs11.getInt(1);
					}

					tipo = request.getParameter("tipo");
					temp_de_servico = Float.parseFloat(request.getParameter("temp_de_servico"));
					tolerancia = Float.parseFloat(request.getParameter("tolerancia"));
					reserva = Integer.parseInt(request.getParameter("reserva"));
					idade = Integer.parseInt(request.getParameter("idade"));

					String sql = "INSERT INTO propriedades (id,tipo,temp_de_servico,tolerancia,reserva,idade) VALUES (default,?,?,?,?,?)";
					Tipos tipos = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);
					try {

						PreparedStatement pstm = con.prepareStatement(sql);
						// pstm.setInt(1, tipos.getId());
						pstm.setString(1, tipos.getTipo());
						pstm.setFloat(2, tipos.getTemp_de_servico());
						pstm.setFloat(3, tipos.getTolerancia());
						pstm.setInt(4, tipos.getReserva());
						pstm.setInt(5, tipos.getIdade());

						try {
							pstm.execute();

						} catch (SQLException e) {
							System.out.println(e);
						}

						pstm.close();
					} catch (Exception e) {

					}

					String sql9 = "SELECT * FROM propriedades";
					PreparedStatement pstm9 = con.prepareStatement(sql9);
					ResultSet rs9 = pstm9.executeQuery();
					if (!rs9.isBeforeFirst()) {
						System.out.println("TESTE NULL 01");
						System.out.println(novoid);
					} else {
						System.out.println("TESTE NULL 02");
						System.out.println(novoid);
						String sql10 = "UPDATE propriedades SET naadega = ? WHERE id = ?";
						PreparedStatement pstm10 = con.prepareStatement(sql10);
						pstm10.setInt(1, 0);
						pstm10.setInt(2, novoid);
						pstm10.execute();
					}

					String sql2 = "SELECT * FROM propriedades";
					PreparedStatement pstm = con.prepareStatement(sql2);
					ResultSet rs = pstm.executeQuery();
					ArrayList<Tipos> list = new ArrayList<Tipos>();

					while (rs.next()) {

						Tipos tipos2 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);

						tipos2.setId(rs.getInt(1));

						tipos2.setTipo(rs.getString(2));

						tipos2.setTemp_de_servico(rs.getFloat(3));

						tipos2.setTolerancia(rs.getFloat(4));

						tipos2.setReserva(rs.getInt(5));

						tipos2.setIdade(rs.getInt(6));

						tipos2.setNaadega(rs.getInt(7));

						list.add(tipos2);

						session.setAttribute("tipo", list);
					}

					response.sendRedirect("gerenciadortipos.jsp");
					con.close();
					break;
				}
				case 13: {
					int testeid = Integer.parseInt(request.getParameter("testeid"));

					String sql = "DELETE FROM propriedades WHERE id = ?";
					try {
						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setInt(1, testeid);
						pstm.execute();
						session.setAttribute("tipo", null);
					} catch (Exception e) {
						session.setAttribute("tipo", null);
					}

					String sql6 = "DELETE FROM informacoes_da_bebida WHERE id_fk = ?";
					try {
						PreparedStatement pstm = con.prepareStatement(sql6);
						pstm.setInt(1, testeid);
						pstm.execute();
						session.setAttribute("bebida", null);
					} catch (Exception e) {
						session.setAttribute("bebida", null);
					}

					String sql2 = "SELECT * FROM propriedades";
					PreparedStatement pstm = con.prepareStatement(sql2);

					ResultSet rs = pstm.executeQuery();
					ArrayList<Tipos> list = new ArrayList<Tipos>();

					if (!rs.isBeforeFirst()) {
						session.setAttribute("idtipo", null);
						session.setAttribute("nometipo1", null);
						session.setAttribute("bebida1", null);

					} else {

						while (rs.next()) {

							Tipos tipos2 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);

							tipos2.setId(rs.getInt(1));

							tipos2.setTipo(rs.getString(2));

							tipos2.setTemp_de_servico(rs.getFloat(3));

							tipos2.setTolerancia(rs.getFloat(4));

							tipos2.setReserva(rs.getInt(5));

							tipos2.setIdade(rs.getInt(6));

							tipos2.setNaadega(rs.getInt(7));

							list.add(tipos2);

							session.setAttribute("tipo", list);

						}
					}

					response.sendRedirect("gerenciadortipos.jsp");
					con.close();
					break;
				}
				case 14: {

					int testeid2 = Integer.parseInt(request.getParameter("testeid2"));
					System.out.println(testeid2);
					tipo = request.getParameter("tipo");
					temp_de_servico = Float.parseFloat(request.getParameter("temp_de_servico"));
					tolerancia = Float.parseFloat(request.getParameter("tolerancia"));
					reserva = Integer.parseInt(request.getParameter("reserva"));
					idade = Integer.parseInt(request.getParameter("idade"));

					String sql = "UPDATE propriedades SET tipo = ?, temp_de_servico = ?, tolerancia = ?,reserva = ? , idade = ? WHERE id = ?";
					Tipos tipos = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);
					try {

						PreparedStatement pstm = con.prepareStatement(sql);
						// pstm.setInt(1, tipos.getId());
						pstm.setString(1, tipos.getTipo());
						pstm.setFloat(2, tipos.getTemp_de_servico());
						pstm.setFloat(3, tipos.getTolerancia());
						pstm.setInt(4, tipos.getReserva());
						pstm.setInt(5, tipos.getIdade());
						pstm.setInt(6, testeid2);

						try {
							pstm.execute();
						} catch (SQLException e) {
							System.out.println(e);
						}

						// session.setAttribute("tipo", tipos);
						pstm.close();
					} catch (Exception e) {

						// session.setAttribute("mensagem", "Bebida " + nome + " Não Cadastrada!");
						// session.setAttribute("tipo", tipos);
					}
					String sql2 = "SELECT * FROM propriedades";
					PreparedStatement pstm = con.prepareStatement(sql2);
					ResultSet rs = pstm.executeQuery();
					ArrayList<Tipos> list = new ArrayList<Tipos>();

					while (rs.next()) {

						Tipos tipos2 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);

						tipos2.setId(rs.getInt(1));

						tipos2.setTipo(rs.getString(2));

						tipos2.setTemp_de_servico(rs.getFloat(3));

						tipos2.setTolerancia(rs.getFloat(4));

						tipos2.setReserva(rs.getInt(5));

						tipos2.setIdade(rs.getInt(6));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						list.add(tipos2);

						session.setAttribute("tipo", list);

						// System.out.println("TESTE");
					}
					response.sendRedirect("gerenciadortipos.jsp");
					con.close();
					break;

				}
				default:
					break;
				}

			} else if (tabela == 2) {

				switch (tipocrud) {
				case 21: {
					String sql = "SELECT * FROM informacoes_da_bebida WHERE EXISTS (SELECT * FROM propriedades WHERE propriedades.naadega = 1 AND propriedades.id = informacoes_da_bebida.id_fk)";
					PreparedStatement pstm = con.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					ArrayList<Bebidas> list = new ArrayList<Bebidas>();
					if (!rs.isBeforeFirst()) {
						System.out.println("TESTE BEBIDAS VAZIO");
						session.setAttribute("bebida", null);

					} else {
						System.out.println("TESTE BEBIDAS CHEIO");
					}

					while (rs.next()) {

						Bebidas bebidas = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);

						bebidas.setId2(rs.getInt(1));

						bebidas.setId_fk(rs.getInt(2));

						bebidas.setQuantidade(rs.getInt(3));

						bebidas.setMarca(rs.getString(4));

						bebidas.setOrigem(rs.getString(5));

						bebidas.setNome(rs.getString(6));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						// System.out.println("TESTE");

						list.add(bebidas);

						session.setAttribute("bebida", list);

						// id_fk_adega = 117;

						// session.setAttribute("idtipo", idtipo);
					}
					String sql2 = "SELECT * FROM propriedades";
					PreparedStatement pstm2 = con.prepareStatement(sql2);
					ResultSet rs2 = pstm2.executeQuery();
					ArrayList<Tipos> list2 = new ArrayList<Tipos>();
					if (!rs2.isBeforeFirst()) {
						session.setAttribute("nometipo", null);
					} else {
						// System.out.println("TESTE NÃO NULL");
					}

					while (rs2.next()) {

						Tipos tipos2 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);
						tipos2.setId(rs2.getInt(1));

						tipos2.setTipo(rs2.getString(2));

						tipos2.setTemp_de_servico(rs2.getFloat(3));

						tipos2.setTolerancia(rs2.getFloat(4));

						tipos2.setReserva(rs2.getInt(5));

						tipos2.setIdade(rs2.getInt(6));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						list2.add(tipos2);

						session.setAttribute("tipo", list2);
					}
					String sql4 = "SELECT * from propriedades WHERE naadega = 1";
					PreparedStatement pstm4 = con.prepareStatement(sql4);
					ResultSet rs4 = pstm4.executeQuery();
					// ArrayList<Tipos> list4 = new ArrayList<Tipos>();
					while (rs4.next()) {

						Tipos tipos4 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);
						tipos4.setId(rs4.getInt(1));

						tipos4.setTipo(rs4.getString(2));

						tipos4.setTemp_de_servico(rs4.getFloat(3));

						tipos4.setTolerancia(rs4.getFloat(4));

						tipos4.setReserva(rs4.getInt(5));

						tipos4.setIdade(rs4.getInt(6));

						tipos4.setNaadega(rs4.getInt(7));

						session.setAttribute("nometipo", null);

						session.setAttribute("nometipo", rs4.getString(2));

						// nometipo = rs4.getString(2);

						session.setAttribute("idtipo", rs4.getInt(1));

						session.setAttribute("naadega", 1);

						// naadega = 1;

					}

				}
					response.sendRedirect("gerenciadorbebidas.jsp");
					con.close();
					break;

				case 22: {
					System.out.println("TESTE 22");

					id_fk = Integer.parseInt(request.getParameter("id_fk"));

					System.out.println(id_fk);
					quantidade = Integer.parseInt(request.getParameter("quantidade"));
					marca = request.getParameter("marca");
					origem = request.getParameter("origem");
					nome = request.getParameter("nome");

					String sql = "INSERT INTO informacoes_da_bebida (id2,id_fk,quantidade,marca,origem,nome) VALUES (default,?,?,?,?,?)";
					Bebidas bebidas = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);
					try {

						PreparedStatement pstm = con.prepareStatement(sql);
						// pstm.setInt(1, bebidas.getId2());
						System.out.println("TESTE");
						pstm.setInt(1, bebidas.getId_fk());
						pstm.setInt(2, bebidas.getQuantidade());
						pstm.setString(3, bebidas.getMarca());
						pstm.setString(4, bebidas.getOrigem());
						pstm.setString(5, bebidas.getNome());

						try {
							pstm.execute();
						} catch (SQLException e) {
							System.out.println(e);
						}

						// session.setAttribute("tipo", tipos);
						pstm.close();
					} catch (Exception e) {

						// session.setAttribute("mensagem", "Bebida " + nome + " Não Cadastrada!");
						// session.setAttribute("tipo", tipos);
					}
					// String nometipo = request.getParameter("tipo");
					// id_fk = Integer.parseInt(request.getParameter("testeid4"));
					String sql2 = "SELECT * FROM informacoes_da_bebida WHERE id_fk = ?";
					PreparedStatement pstm = con.prepareStatement(sql2);
					pstm.setInt(1, id_fk);
					ResultSet rs = pstm.executeQuery();
					ArrayList<Bebidas> list = new ArrayList<Bebidas>();

					while (rs.next()) {

						Bebidas bebidas2 = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);

						bebidas2.setId2(rs.getInt(1));

						bebidas2.setId_fk(rs.getInt(2));

						bebidas2.setQuantidade(rs.getInt(3));

						bebidas2.setMarca(rs.getString(4));

						bebidas2.setOrigem(rs.getString(5));

						bebidas2.setNome(rs.getString(6));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						list.add(bebidas2);

						session.setAttribute("bebida", list);

					}
					response.sendRedirect("gerenciadorbebidas.jsp");
					con.close();
					break;
				}

				case 23: {
					int testeid = Integer.parseInt(request.getParameter("testeid"));
					System.out.println(testeid);
					id_fk = Integer.parseInt(request.getParameter("id_fk"));
					quantidade = Integer.parseInt(request.getParameter("quantidade"));
					marca = request.getParameter("marca");
					origem = request.getParameter("origem");
					nome = request.getParameter("nome");

					String sql = "UPDATE informacoes_da_bebida SET id_fk = ?,quantidade = ?, marca = ?, origem = ?,nome = ? WHERE id2 = ?";
					Bebidas bebidas2 = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);
					try {

						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setInt(1, bebidas2.getId_fk());
						pstm.setInt(2, bebidas2.getQuantidade());
						pstm.setString(3, bebidas2.getMarca());
						pstm.setString(4, bebidas2.getOrigem());
						pstm.setString(5, bebidas2.getNome());
						pstm.setInt(6, testeid);

						try {
							pstm.execute();
						} catch (SQLException e) {
							System.out.println(e);
						}

						// session.setAttribute("tipo", tipos);
						pstm.close();
					} catch (Exception e) {

						// session.setAttribute("mensagem", "Bebida " + nome + " Não Cadastrada!");
						// session.setAttribute("tipo", tipos);
					}
					String sql2 = "SELECT * FROM informacoes_da_bebida WHERE id_fk = ?";
					PreparedStatement pstm = con.prepareStatement(sql2);
					pstm.setInt(1, id_fk);
					ResultSet rs = pstm.executeQuery();
					ArrayList<Bebidas> list = new ArrayList<Bebidas>();

					while (rs.next()) {

						Bebidas bebidas = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);

						bebidas.setId2(rs.getInt(1));

						bebidas.setId_fk(rs.getInt(2));

						bebidas.setQuantidade(rs.getInt(3));

						bebidas.setMarca(rs.getString(4));

						bebidas.setOrigem(rs.getString(5));

						bebidas.setNome(rs.getString(6));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						list.add(bebidas);

						session.setAttribute("bebida", list);

						// System.out.println("TESTE");
					}
					response.sendRedirect("gerenciadorbebidas.jsp");
					con.close();
					break;

				}
				case 24: {

					int testeid3 = Integer.parseInt(request.getParameter("testeid3"));
					String sql = "DELETE FROM informacoes_da_bebida WHERE id2 = ?";
					try {
						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setInt(1, testeid3);
						pstm.execute();
						session.setAttribute("bebida", null);
					} catch (Exception e) {
						session.setAttribute("bebida", null);
					}
					id_fk = Integer.parseInt(request.getParameter("id_fk"));
					System.out.println("TESTE DELETE");
					String sql2 = "SELECT * FROM informacoes_da_bebida WHERE id_fk = ?";
					PreparedStatement pstm = con.prepareStatement(sql2);
					pstm.setInt(1, id_fk);
					ResultSet rs = pstm.executeQuery();
					ArrayList<Bebidas> list = new ArrayList<Bebidas>();

					while (rs.next()) {

						Bebidas bebidas = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);

						bebidas.setId2(rs.getInt(1));

						bebidas.setId_fk(rs.getInt(2));

						bebidas.setQuantidade(rs.getInt(3));

						bebidas.setMarca(rs.getString(4));

						bebidas.setOrigem(rs.getString(5));

						bebidas.setNome(rs.getString(6));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						list.add(bebidas);

						session.setAttribute("bebida", list);

						// System.out.println("TESTE");
					}
					response.sendRedirect("gerenciadorbebidas.jsp");
					con.close();
					break;
				}
				case 25: {
					// int naadega1 = Integer.parseInt(request.getParameter("naadega"));
					// System.out.println("TESTE MONITOR");
					id_fk = Integer.parseInt(request.getParameter("testeid4"));
					String nometipo1 = (request.getParameter("tipo"));

					String sql = "SELECT * FROM propriedades";
					PreparedStatement pstm2 = con.prepareStatement(sql);
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

					String sql2 = "SELECT * FROM informacoes_da_bebida WHERE id_fk = ?";
					PreparedStatement pstm = con.prepareStatement(sql2);
					pstm.setInt(1, id_fk);
					ResultSet rs = pstm.executeQuery();

					ArrayList<Bebidas> list = new ArrayList<Bebidas>();

					if (!rs.isBeforeFirst()) {
						Bebidas bebidas2 = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);
						bebidas2.setId2(null);

						bebidas2.setId_fk(null);

						bebidas2.setQuantidade(null);

						bebidas2.setMarca(null);

						bebidas2.setOrigem(null);

						bebidas2.setNome(null);

						list.add(bebidas2);

						session.setAttribute("bebida", null);

					} else {

						while (rs.next()) {

							Bebidas bebidas = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);

							bebidas.setId2(rs.getInt(1));

							bebidas.setId_fk(rs.getInt(2));

							bebidas.setQuantidade(rs.getInt(3));

							bebidas.setMarca(rs.getString(4));

							bebidas.setOrigem(rs.getString(5));

							bebidas.setNome(rs.getString(6));

							// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

							list.add(bebidas);

							session.setAttribute("bebida", list);

						}
					}
					String sql4 = "SELECT * from propriedades WHERE naadega = 1";
					PreparedStatement pstm4 = con.prepareStatement(sql4);
					ResultSet rs4 = pstm4.executeQuery();
					// ArrayList<Tipos> list4 = new ArrayList<Tipos>();
					while (rs4.next()) {

						Tipos tipos4 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);
						tipos4.setId(rs4.getInt(1));

						tipos4.setTipo(rs4.getString(2));

						tipos4.setTemp_de_servico(rs4.getFloat(3));

						tipos4.setTolerancia(rs4.getFloat(4));

						tipos4.setReserva(rs4.getInt(5));

						tipos4.setIdade(rs4.getInt(6));

						tipos4.setNaadega(rs4.getInt(7));

						id_fk_adega = rs4.getInt(1);

					}

					if (id_fk == id_fk_adega) {

						session.setAttribute("naadega", 1);
						session.setAttribute("nometipo", nometipo1);
						session.setAttribute("idtipo", id_fk);
					} else {

						session.setAttribute("naadega", 0);
						session.setAttribute("nometipo", nometipo1);
						session.setAttribute("idtipo", id_fk);
					}

					response.sendRedirect("gerenciadorbebidas.jsp");
					con.close();

					break;
				}

				case 26: {
					int testeid6 = Integer.parseInt(request.getParameter("testeid6"));
					String sql2 = "SELECT * FROM informacoes_da_bebida WHERE id_fk = ?";
					PreparedStatement pstm = con.prepareStatement(sql2);
					pstm.setInt(1, testeid6);
					ResultSet rs = pstm.executeQuery();
					ArrayList<Bebidas> list = new ArrayList<Bebidas>();

					while (rs.next()) {

						Bebidas bebidas = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);

						bebidas.setId2(rs.getInt(1));

						bebidas.setId_fk(rs.getInt(2));

						bebidas.setQuantidade(rs.getInt(3));

						bebidas.setMarca(rs.getString(4));

						bebidas.setOrigem(rs.getString(5));

						bebidas.setNome(rs.getString(6));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						list.add(bebidas);

						session.setAttribute("bebida", list);

						// System.out.println("TESTE");
					}
					String sql6 = "SELECT * FROM propriedades WHERE id = ?";
					PreparedStatement pstm6 = con.prepareStatement(sql6);
					pstm6.setInt(1, testeid6);
					ResultSet rs6 = pstm6.executeQuery();

					while (rs6.next()) {
						Tipos tipos4 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);

						tipos4.setId(rs6.getInt(1));

						tipos4.setTipo(rs6.getString(2));

						tipos4.setTemp_de_servico(rs6.getFloat(3));

						tipos4.setTolerancia(rs6.getFloat(4));

						tipos4.setReserva(rs6.getInt(5));

						tipos4.setIdade(rs6.getInt(6));

						tipos4.setNaadega(rs6.getInt(7));
						System.out.println("TESTE 26");
						session.setAttribute("nometipo", rs6.getString(2));
						session.setAttribute("naadega", rs6.getInt(7));
					}

					response.sendRedirect("gerenciadorbebidas.jsp");
					con.close();

					break;
				}
				default:
					break;
				}

			}
			if (tabela == 3) {
				switch (tipocrud) {
				case 31: {

					id_fk = Integer.parseInt(request.getParameter("testeid4"));
					String nometipo1 = (request.getParameter("tipo"));

					String sql = "SELECT * FROM propriedades";
					PreparedStatement pstm2 = con.prepareStatement(sql);
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

					}

					String sql2 = "SELECT * FROM informacoes_da_bebida WHERE id_fk = ?";
					PreparedStatement pstm = con.prepareStatement(sql2);
					pstm.setInt(1, id_fk);
					ResultSet rs = pstm.executeQuery();

					ArrayList<Bebidas> list = new ArrayList<Bebidas>();

					if (!rs.isBeforeFirst()) {
						Bebidas bebidas2 = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);
						bebidas2.setId2(null);

						bebidas2.setId_fk(null);

						bebidas2.setQuantidade(null);

						bebidas2.setMarca(null);

						bebidas2.setOrigem(null);

						bebidas2.setNome(null);

						list.add(bebidas2);

						session.setAttribute("bebida1", null);

					} else {

						while (rs.next()) {

							Bebidas bebidas = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);

							bebidas.setId2(rs.getInt(1));

							bebidas.setId_fk(rs.getInt(2));

							bebidas.setQuantidade(rs.getInt(3));

							bebidas.setMarca(rs.getString(4));

							bebidas.setOrigem(rs.getString(5));

							bebidas.setNome(rs.getString(6));

							// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

							list.add(bebidas);

							session.setAttribute("bebida1", list);

						}
					}

					int testeid2 = Integer.parseInt(request.getParameter("testeid4"));
					System.out.println(testeid2);
					String sql8 = "UPDATE propriedades SET naadega = 0";
					PreparedStatement pstm8 = con.prepareStatement(sql8);
					pstm8.execute();
					String sql7 = "UPDATE propriedades SET naadega = ? WHERE id = ?";
					try {
						PreparedStatement pstm7 = con.prepareStatement(sql7);
						pstm7.setInt(1, 1);
						pstm7.setInt(2, testeid2);
						try {
							pstm7.execute();
						} catch (SQLException e) {
							System.out.println(e);
						}
						pstm7.close();
					} catch (Exception e) {
					}

					String sql4 = "SELECT * from propriedades WHERE naadega = 1";
					PreparedStatement pstm4 = con.prepareStatement(sql4);
					ResultSet rs4 = pstm4.executeQuery();
					// ArrayList<Tipos> list4 = new ArrayList<Tipos>();

					while (rs4.next()) {

						Tipos tipos4 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);
						tipos4.setId(rs4.getInt(1));

						tipos4.setTipo(rs4.getString(2));

						tipos4.setTemp_de_servico(rs4.getFloat(3));

						tipos4.setTolerancia(rs4.getFloat(4));

						tipos4.setReserva(rs4.getInt(5));

						tipos4.setIdade(rs4.getInt(6));

						tipos4.setNaadega(rs4.getInt(7));

						id_fk_adega = rs4.getInt(1);

						if (id_fk == id_fk_adega) {

							// session.setAttribute("notnull", notnull);
							session.setAttribute("naadega", 1);
							session.setAttribute("nometipo1", nometipo1);
							session.setAttribute("idtipo", id_fk);
							session.setAttribute("tservico", rs4.getFloat(3));
							session.setAttribute("ttolerancia", rs4.getFloat(4));
						} else {

							// session.setAttribute("notnull", notnull);
							session.setAttribute("naadega", 0);
							session.setAttribute("nometipo1", nometipo1);
							session.setAttribute("idtipo", id_fk);
							session.setAttribute("tservico", rs4.getFloat(3));
							session.setAttribute("ttolerancia", rs4.getFloat(4));
							// System.out.println("TESTE MONITOR 2");

						}
					}
					response.sendRedirect("monitor.jsp");
					con.close();
					break;
				}
				case 32: {
					System.out.println("TESTE MONITOR 2");
					String sql = "SELECT * FROM informacoes_da_bebida WHERE EXISTS (SELECT * FROM propriedades WHERE propriedades.naadega = 1 AND propriedades.id = informacoes_da_bebida.id_fk)";
					PreparedStatement pstm = con.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					ArrayList<Bebidas> list = new ArrayList<Bebidas>();

					while (rs.next()) {

						Bebidas bebidas = new Bebidas(id2, id_fk, quantidade, marca, origem, nome);

						bebidas.setId2(rs.getInt(1));

						bebidas.setId_fk(rs.getInt(2));

						bebidas.setQuantidade(rs.getInt(3));

						bebidas.setMarca(rs.getString(4));

						bebidas.setOrigem(rs.getString(5));

						bebidas.setNome(rs.getString(6));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						// System.out.println("TESTE");

						list.add(bebidas);

						session.setAttribute("bebida1", list);

						// id_fk_adega = 117;

						// session.setAttribute("idtipo", idtipo);
					}
					String sql2 = "SELECT * FROM propriedades";
					PreparedStatement pstm2 = con.prepareStatement(sql2);
					ResultSet rs2 = pstm2.executeQuery();
					ArrayList<Tipos> list2 = new ArrayList<Tipos>();
					if (!rs2.isBeforeFirst()) {
						session.setAttribute("nometipo1", null);
					} else {
						System.out.println("TESTE NÃO NULL");
					}

					while (rs2.next()) {

						Tipos tipos2 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);
						tipos2.setId(rs2.getInt(1));

						tipos2.setTipo(rs2.getString(2));

						tipos2.setTemp_de_servico(rs2.getFloat(3));

						tipos2.setTolerancia(rs2.getFloat(4));

						tipos2.setReserva(rs2.getInt(5));

						tipos2.setIdade(rs2.getInt(6));

						// session.setAttribute("mensagem", "Bebida " + " Encontrada!");

						list2.add(tipos2);

						session.setAttribute("tipo", list2);
					}
					String sql4 = "SELECT * from propriedades WHERE naadega = 1";
					PreparedStatement pstm4 = con.prepareStatement(sql4);
					ResultSet rs4 = pstm4.executeQuery();
					// ArrayList<Tipos> list4 = new ArrayList<Tipos>();
					while (rs4.next()) {

						Tipos tipos4 = new Tipos(id, tipo, temp_de_servico, tolerancia, reserva, idade, naadega);
						tipos4.setId(rs4.getInt(1));

						tipos4.setTipo(rs4.getString(2));

						tipos4.setTemp_de_servico(rs4.getFloat(3));

						tipos4.setTolerancia(rs4.getFloat(4));

						tipos4.setReserva(rs4.getInt(5));

						tipos4.setIdade(rs4.getInt(6));

						tipos4.setNaadega(rs4.getInt(7));

						session.setAttribute("nometipo1", null);

						session.setAttribute("nometipo1", rs4.getString(2));

						// nometipo = rs4.getString(2);

						session.setAttribute("idtipo", rs4.getInt(1));

						session.setAttribute("naadega", 1);

						// naadega = 1;

						session.setAttribute("tservico", rs4.getFloat(3));
						session.setAttribute("ttolerancia", rs4.getFloat(4));

					}

				}
					response.sendRedirect("monitor.jsp");
					con.close();
					break;

				case 33: {
					int testeid5 = Integer.parseInt(request.getParameter("testeid6"));
					String sql11 = "select temp_de_servico, tolerancia from propriedades WHERE id = ?";
					PreparedStatement pstm11 = con.prepareStatement(sql11);
					pstm11.setInt(1, testeid5);
					ResultSet rs11 = pstm11.executeQuery();

					while (rs11.next()) {
						float tservico = rs11.getFloat(1);
						float ttolerancia = rs11.getFloat(2);
						session.setAttribute("tservico", tservico);
						session.setAttribute("ttolerancia", ttolerancia);
						System.out.println(tservico);
						System.out.println(ttolerancia);
					}

					int testeid6 = Integer.parseInt(request.getParameter("testeid6"));
					float numero1 = Float.parseFloat(request.getParameter("numero1"));
					float numero2 = Float.parseFloat(request.getParameter("numero2"));

					String sql7 = "UPDATE propriedades SET temp_de_servico = ?, tolerancia = ? where id = ?";
					try {
						PreparedStatement pstm7 = con.prepareStatement(sql7);
						pstm7.setFloat(1, numero1);
						pstm7.setFloat(2, numero2);
						pstm7.setFloat(3, testeid6);
						try {
							pstm7.execute();
						} catch (SQLException e) {
							System.out.println(e);
						}
						pstm7.close();
					} catch (Exception e) {
					}

					session.setAttribute("tservico", numero1);
					session.setAttribute("ttolerancia", numero2);

					response.sendRedirect("monitor.jsp");
					con.close();

					break;
				}

				case 34: { // ligar adega
					String sql31 = "UPDATE ligadesliga SET onoff = 0";
					PreparedStatement pstm31 = con.prepareStatement(sql31);
					pstm31.execute();
					int ligada = Integer.parseInt(request.getParameter("ligada"));
					while (ligada == 1) {
						String sql20 = "select valorSensor from tabela where id = (SELECT MAX(ID) FROM tabela)";
						PreparedStatement pstm20 = con.prepareStatement(sql20);
						ResultSet rs20 = pstm20.executeQuery();
						while (rs20.next()) {
							tatual = rs20.getFloat(1);
						}
						session.setAttribute("tatual", tatual);

						String sql30 = "select temp_de_servico, tolerancia from propriedades WHERE naadega = 1";
						PreparedStatement pstm30 = con.prepareStatement(sql30);
						ResultSet rs30 = pstm30.executeQuery();
						while (rs30.next()) {
							float tservico2 = rs30.getFloat(1);
							float ttolerancia2 = rs30.getFloat(2);
						
						String sql21 = "select valorSensor from tabela";
						PreparedStatement pstm21 = con.prepareStatement(sql21);
						ResultSet rs21 = pstm21.executeQuery();
						ArrayList<Float> list21 = new ArrayList<Float>();
						ArrayList<Integer> list22 = new ArrayList<Integer>();
						ArrayList<Float> list23 = new ArrayList<Float>();
						ArrayList<Float> list24 = new ArrayList<Float>();
						float maximo = (tservico2 + ttolerancia2);
						float minimo = (tservico2 - ttolerancia2);
						int label1 = 0;
						while (rs21.next()) {
							list21.add(rs21.getFloat(1));
							label1 = label1 + 10;
							list22.add(label1);
							list23.add(maximo);
							list24.add(minimo);
							
						}
						session.setAttribute("listtemp", list21);
						session.setAttribute("label1", list22);
						session.setAttribute("maximo", list23);
						session.setAttribute("minimo", list24);
						}	
					}
					
					response.sendRedirect("monitor.jsp");
					con.close();
					break;
				}
				
				case 35: {
					String sql20 = "delete from tabela";
					PreparedStatement pstm20 = con.prepareStatement(sql20);
					pstm20.execute();
					String sql21 = "UPDATE ligadesliga SET onoff = 1";
					PreparedStatement pstm21 = con.prepareStatement(sql21);
					pstm21.execute();
					response.sendRedirect("monitor.jsp");
					con.close();
					break;
				}
				
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("ERRO");
			e1.printStackTrace();
		}

	}
}