<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html>

	<head>
		<title>Login</title>
		<meta charset="utf-8" />
		<link rel="stylesheet" href="css/main.css">
		
	<script type="text/javascript">
	function alertar() {
		//alert("Usuário não autenticado!");
	}
</script>

	<body>
	<script>
	window.onload = alertar;
	</script>
		<div class="titulo">
			SMART ADEGA
		</div>	
		<div class="corpo">
			<div id="imagemlogin">
				<img id="imagem" src="imagens/imagemlogin.jpg">
			</div>
			<div id="formulariologin">
				<form action="http://localhost:8080/smart_adega_oficial/Autenticador" method="post">
					<div id="account">
							ACCOUNT LOGIN
					</div>
					<table>
						<tr>
							<td><input id="entradaemail" name="email" type="email" placeholder="Email"/><td/>
							<td><input id="entradasenha" name="senha" type="password" placeholder="Password" required/></td>
						</tr>
						<tr>
							<td id="botao"><input id="signin" type="submit" value="SIGN IN"/></td>
						</tr>
					</table>
					<div id="forgot">
							<a id="botaoesqueci" href="#"> Forgot password </a>
					</div>
					<div id="signup">
							<a href="#" id="botaoentrar"> SIGN UP </a>
					</div>				
				</form>
			</div>
		</div>	
		<div class="rodape">
			<div class="letreirorodape">
			<strong>A PRODUCT OF DREAMSFACTORY® COMPANY</strong>
			</div>
			<div class="creditos">
			<strong>Produced by Aurélio Domingues Cruz</strong>
			</div>		
		</div>

	</body>


</html>