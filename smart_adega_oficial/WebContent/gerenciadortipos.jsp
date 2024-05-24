<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html>

<head>
<title>Gerenciador de Tipos</title>
<meta charset="utf-8" />

<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="vendor/jQuery/jquery-3.4.1.min.js"></script>

<script type="text/javascript">
	function alertar($tipoid,$naadega2) {
		if ($naadega2 == 1) {alert("Você não pode excluir um tipo que está na adega ligada, para isso, retire esse tipo no monitor!");}
		else {
		var retorno = confirm("Se você excluir um tipo de bebidas, todas as bebidas desse tipo serão excluídas! Deseja continuar?");
		if (retorno == true){
			$lixo = $tipoid;
			$naadega2 = $naadega2;
			document.getElementById("naadega2").value = $naadega2;
			document.getElementById("lixo").value = $lixo;
			document.getElementById("alertar").submit();
		} else {return false;}
	  }
	}		
</script>


<script type="text/javascript">
	function cancelarcadastro() {
		document.getElementById("teste").style.display = 'none';
		
	}
</script>

<script type="text/javascript">
	function cancelaredit() {
		document.getElementById("teste2").style.display = 'none';
	}
</script>

<script type="text/javascript">
	function Paramonitor() {
		location.href = "monitor.jsp"
	}
</script>

<script type="text/javascript">
	function Parabebidas() {
		document.getElementById("checkbox3").submit();
		//location.href = "gerenciadorbebidas.jsp"
	}
</script>

<script type="text/javascript">
	function mostrar() {
		//document.getElementById("teste2").style.display = 'none';
		document.getElementById("teste").style.display = 'block';

	}
</script>

<script type="text/javascript">
	function mostrar2($id,$tipo,$temperatura,$tolerancia,$reserva,$idade) {
		document.getElementById("teste").style.display = 'none';
		document.getElementById("teste2").style.display = 'block';
		$id = $id;
		$tipo = $tipo;
		$temperatura = $temperatura;
		$tolerancia = $tolerancia;
		$reserva = $reserva;
		$idade = $idade;
		document.getElementById("idform").value = $id;
		document.getElementById("tipoform").value = $tipo;
		document.getElementById("tempform").value = $temperatura;
		document.getElementById("toleraform").value = $tolerancia;
		document.getElementById("reservaform").value = $reserva;
		document.getElementById("idadeform").value = $idade;
	}
</script>

</head>

<body>

	<div>
		<div class="titulo">SMART ADEGA</div>
		<div id="subtitulogerenciador">[GERENCIADOR]</div>
	</div>

	<div class="tipoletra">BEBIDAS</div>

	<div class="radio">
		<input class="radiobotao" type="radio" name="opcao" checked />
		GERENCIAR BEBIDAS <br /> <input class="radiobotao" type="radio"
			name="opcao" Onclick="Parabebidas()" /> BEBIDAS NA ADEGA <br />
	</div>

	<div id="tipos">TIPOS CADASTRADOS:</div>


	<div id="tabelatipos">
		<table class="tabela3" border="1">
			<thead>
				<tr align="center">
					<th>Tipo</th>
					<th>Temp. serviço</th>
					<th>Tolerância</th>
					<th>Reserva</th>
					<th>Idade</th>
					<th colspan="3">Opção</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="tipo" items="${tipo}">
					<tr align="center">
						<td style="display: none"><c:out value="${tipo.id}" /></td>
						<td style="display: none"><c:out value="${tipo.naadega}" /></td>
						<td><c:out value="${tipo.tipo}" /></td>
						<td><c:out value="${tipo.temp_de_servico}" /></td>
						<td><c:out value="${tipo.tolerancia}" /></td>
						<td><c:out value="${tipo.reserva}" /></td>
						<td><c:out value="${tipo.idade}" /></td>



						<td style="border-right: none">
							<form
								action="http://localhost:8080/smart_adega_oficial/Controlador"
								method="post">
								<input type="hidden" name="tabela" value="2"> <input
									type="hidden" name="tipocrud" value="26"> <input
									type="hidden" name="testeid6" id="lupa" value="${tipo.id}">
								<button type="submit" class="botoestabela">
									<img id="imagemicone3" src="imagens/lupa2.png">
								</button>
							</form>
						</td>

						<td class="bordas">
							<button
								onclick="mostrar2(${tipo.id},'${tipo.tipo}',${tipo.temp_de_servico},${tipo.tolerancia},${tipo.reserva},${tipo.idade})"
								class="botoestabela">
								<img id="imagemicone1" src="imagens/editar3.png">
							</button>


							<div id="formulariotipo2">

								<form id="teste2"
									action="http://localhost:8080/smart_adega_oficial/Controlador"
									method="post" style="display: none">
									<input type="hidden" name="tabela" value="1"> <input
										type="hidden" name="tipocrud" value="14"> <input
										type="hidden" id="idform" name="testeid2" value="">
									<div>
										Tipo:<input type="text" id="tipoform" name="tipo" value=""
											required> Temp. de Serviço:<input type="number"
											id="tempform" name="temp_de_servico" value="" step="0.01"
											required> Tolerância:<input type="number"
											id="toleraform" name="tolerancia" value="" step="0.01"
											min="2" required> Reserva:<input type="number"
											id="reservaform" name="reserva" value="" required>
										Idade:<input type="number" id="idadeform" name="idade"
											value="" required>

									</div>
									<button type="button" class="cancelar" onclick="cancelaredit()">Cancelar</button>
									<button class="botaocadastrar4">SALVAR</button>
								</form>
							</div>
						</td>


						<td style="border-left: none">
							<form
								action="http://localhost:8080/smart_adega_oficial/Controlador"
								method="post" id="alertar">
								<input type="hidden" name="tabela" value="1"> <input
									type="hidden" name="tipocrud" value="13"> <input
									type="hidden" name="testeid" id="lixo" value="${tipo.id}">
									<input
									type="hidden" name="naadega2" id="naadega2" value="${tipo.naadega}">
								<button type="button" class="botoestabela"
									onclick="alertar(${tipo.id},${tipo.naadega})">
									<img id="imagemicone2" src="imagens/lixo2.png">
								</button>
							</form>

						</td>

					</tr>



				</c:forEach>

			</tbody>


		</table>
	</div>




	<div>


		<button class="botaocadastrar3" onclick="mostrar()">CADASTRAR</button>

	</div>

	<div id="formulariotipo">
		<form id="teste" style="display: none"
			action="http://localhost:8080/smart_adega_oficial/Controlador"
			method="post">
			<input type="hidden" name="tabela" value="1"> <input
				type="hidden" name="tipocrud" value="12"> Tipo:<input
				type="text" name="tipo" value="" required> Temp. de Serviço:<input
				type="number" name="temp_de_servico" value="" step="0.01" required>
			Tolerância:<input type="number" name="tolerancia" value=""
				step="0.01" min="2" required> Reserva:<input type="number"
				name="reserva" value="" required> Idade:<input type="number"
				name="idade" value="" required>
			<button type="button" class="cancelar" onclick="cancelarcadastro()">Cancelar</button>

			<button class="botaocadastrar4">SALVAR</button>
		</form>
	</div>

	<form id="checkbox3"
		action="http://localhost:8080/smart_adega_oficial/Controlador"
		method="post" style="display: none">
		<input type="hidden" name="tabela" value="2"> <input
			type="hidden" name="tipocrud" value="21"> <input
			type="hidden" name="tipo" value="${nometipo}"> <input
			type="hidden" name="testeid4" value="${idtipo}">


		<button type="submit" class="botoestabela"></button>
	</form>


	<div>
		<form action="http://localhost:8080/smart_adega_oficial/Controlador"
			method="post">
			<input type="hidden" name="tabela" value="3"> <input
				type="hidden" name="tipocrud" value="32">
			<button class="botaovoltar4" Onclick="Paramonitor()">MONITOR</button>
		</form>
	</div>



	<div>
		<a href="http://www.wine.com.br" id="link" target="_blank">
			wine.com.br </a>
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