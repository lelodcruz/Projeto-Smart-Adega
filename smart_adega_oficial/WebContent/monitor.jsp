<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<head>
<title>Monitor</title>
<meta charset="utf-8" />

<link rel="stylesheet" href="css/main.css">

<script type="text/javascript">
	function Paratipos() {
		location.href = "gerenciadortipos.jsp"
	}
</script>

<script type="text/javascript">
	function Parabebidas() { 
		location.href = "gerenciadorbebidas.jsp"
	}
</script>

<script type="text/javascript">
	function seleciona() {
		var select = document.getElementById("selecaotipomonitor");
		var value = select.options[select.selectedIndex].value;
		$idtipo = value;
		var id = select.options[select.selectedIndex].id;
		$tipo = id;
		var id2 = select.options[2].value;
		$naadega = id2;	
		//alert($tipo);
		document.getElementById("select").value = $tipo;
		document.getElementById("id3form").value = $idtipo;
		document.getElementById("naadega").value = $naadega;
		document.getElementById("checkbox2").submit();
		

	}
</script>


<script type="text/javascript">
	function numero1($tservico) {
		document.getElementById("numero1").value = $tservico;
		//alert($tservico);
	}
</script>

<script type="text/javascript">
	function numero2($ttolerancia) {
		document.getElementById("numero2").value = $ttolerancia;
		//alert($ttolerancia);
	}
</script>

<script type="text/javascript">
	function numero($tservico,$ttolerancia) {
		//alert($tservico);
		//alert($ttolerancia);
	}
</script>

<script type="text/javascript">
	function atualizar() {

		window.location.reload();
		
	}
</script>

</head>

<body onload="setInterval('atualizar()', 10000)">
	<div>
		<div class="titulo">SMART ADEGA</div>
		<div id="subtitulomonitor">[MONITOR]</div>
	</div>

	<div class="tipoletra">ADEGA</div>

	<div id="tiposmonitor">TIPO NA ADEGA:</div>

	<div>
		<select id="selecaotipomonitor" onChange="seleciona()">
			<option>${nometipo1}</option>
			<c:forEach var="tipo" items="${tipo}">
				<c:if test="${tipo.tipo != nometipo1}">
					<option id="${tipo.tipo}" value="${tipo.id}">${tipo.tipo}</option>
					<option style="display: none" value="${tipo.naadega}">
				</c:if>
			</c:forEach>
		</select>

	</div>



	<form id="checkbox2"
		action="http://localhost:8080/smart_adega_oficial/Controlador"
		method="post" style="display: none">
		<input type="hidden" name="tabela" value="3"> <input
			type="hidden" name="tipocrud" value="31"> <input
			type="hidden" name="testeid4" id="id3form" value=""> <input
			type="hidden" name="tipo" id="select" value=""> <input
			type="hidden" name="naadega" id="naadega" value="">
		<button type="submit"></button>
	</form>

	<div>
		<form action="http://localhost:8080/smart_adega_oficial/Controlador"
			method="post">
			<button id="botaoligaradega">LIGAR ADEGA</button>
			<input type="hidden" name="tabela" value="3"> <input
				type="hidden" name="tipocrud" value="34"> <input
				type="hidden" id="ligado" name="ligada" value="1">
		</form>

		<form action="http://localhost:8080/smart_adega_oficial/Controlador"
			method="post">
			<button id="botaodesligaradega">DESLIGAR ADEGA</button>
			<input type="hidden" name="tabela" value="3"> <input
				type="hidden" name="tipocrud" value="35"> <input
				type="hidden" id="ligado" name="ligada" value="0">
		</form>
	</div>


	<div class="number">
		<form action="http://localhost:8080/smart_adega_oficial/Controlador"
			method="post" id="numeros">
			<input type="hidden" name="tabela" value="3"> <input
				type="hidden" name="tipocrud" value="33"> <input
				type="hidden" name="testeid6" value="${idtipo}"> Temp. de
			serviço: <input class="numberbotao1" onclick="numero1(${tservico})"
				type="number" id="numero1" name="numero1" value="${tservico}"
				step="0.01" /> <br /> Tolerância: <input class="numberbotao2"
				onclick="numero2(${ttolerancia})" type="number" id="numero2"
				name="numero2" value="${ttolerancia}" step="0.01" min="2.00" /> <br />
			<button style="display: none"
				onclick="numero(${tservico},${ttolerancia})">SALVAR</button>
		</form>
	</div>

	<div id="temperaturaatual">TEMPERATURA ATUAL:</div>

	<div>
		<span id="selo2"> ${tatual}°C </span>
	</div>

	<div id="tabelamonitor">
		<table class="tabela1" border="1">
			<thead>
				<tr align="center">
					<th>Quantidade</th>
					<th>Marca</th>
					<th>Origem</th>
					<th>Nome</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bebida" items="${bebida1}">
					<tr align="center">
						<td style="display: none"><c:out value="${bebida.id2}" /></td>
						<td style="display: none"><c:out value="${bebida.id_fk}" /></td>
						<td><c:out value="${bebida.quantidade}" /></td>
						<td><c:out value="${bebida.marca}" /></td>
						<td><c:out value="${bebida.origem}" /></td>
						<td><c:out value="${bebida.nome}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	



	<div id="grafico">
		<canvas class="line-chart"></canvas>
		<script src="vendor/chartjs/Chart.min.js"></script>
		<script>
		    var dados = ${listtemp};
		    var maximo = ${maximo};
		    var minimo = ${minimo};
		    var rotulo = ${label1};
			var ctx = document.getElementsByClassName("line-chart");
			var chartGraph = new Chart(ctx, {
				type : 'line', 
				data : {
					labels : rotulo,
					datasets : [
							{
								label : "TEMPERATURA",
								
								data : dados,
								
								borderWidth : 3,
								borderColor : 'rgba(77,166,253,0.85)',
								backgroundColor : 'transparent',
							},
							{
								label : "MÍNIMO",
								data: minimo,
								borderWidth : 3,
								borderColor : 'green',
								backgroundColor : 'transparent',
							},
							{
								label : "MÁXIMO",
								data : maximo,
								borderWidth : 3,
								borderColor : 'red',
								backgroundColor : 'transparent',
							},

					]
				},
				options : {
					//animation: false,
					title : {
						display : true,
						fontSize : 20,
						text : "HISTÓRICO DA TEMPERATURA",
						fontFamily : "titulo",
					},
					legend : {
						display : true,
					},
					scales : {
						xAxes : [ {
							ticks : {
								fontSize : 15,
							},
						} ],
						yAxes : [ {
							ticks : {
								fontSize : 15,
								beginAtZero: true
							},
						} ]
					}
				} 
			}); 
		</script>
	</div>

	<div class="ligardesligar">
		<form action="http://localhost:8080/smart_adega_oficial/Controlador"
			method="post">
			<button id="ligar1" type="submit">CADASTRAR BEBIDAS</button>
			<input type="hidden" name="tabela" value="2"> <input
				type="hidden" name="tipocrud" value="21"> <input
				type="hidden" name="tipo" value="${nometipo}"> <input
				type="hidden" name="testeid4" value="${idtipo}">
		</form>



		<form action="http://localhost:8080/smart_adega_oficial/Controlador"
			method="post">
			<button type="submit" id="desligar1">CADASTRAR TIPOS</button>
			<input type="hidden" name="tabela" value="1"> <input
				type="hidden" name="tipocrud" value="11">
		</form>
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