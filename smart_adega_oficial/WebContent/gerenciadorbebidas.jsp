<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<head>
<title>Gerenciador de Bebidas</title>
<meta charset="utf-8" />

<link rel="stylesheet" href="css/main.css">

<script type="text/javascript">
	function cancelaredit() {
		document.getElementById("teste2").style.display = 'none';
	}
</script>

<script type="text/javascript">
	function cancelarcadastro() {
		document.getElementById("teste1").style.display = 'none';
	}
</script>

<script type="text/javascript">
	function Paramonitor() {
		location.href = "monitor.jsp"
	}
</script>

<script type="text/javascript">
	function Paratipos() {
		document.getElementById("checkbox").submit();
		//location.href = "gerenciadortipos.jsp"
	}
</script>

<script type="text/javascript">
	function mostrar1($id_fkteste) {
		//document.getElementById("teste2").style.display = 'none';
		$id_fkteste = $id_fkteste;
		
		if ($id_fkteste != null){
		document.getElementById("teste1").style.display = 'block';
		document.getElementById("id_fkteste").value = $id_fkteste; 
		}
		if ($id_fkteste == null){
			alert("Primeiro cadastre o tipo da sua bebida!");
		}
	}
</script>

<script type="text/javascript">
	function mostrar2($id2,$id_fk,$quantidade,$marca,$origem,$nome) {
		document.getElementById("teste1").style.display = 'none';
		document.getElementById("teste2").style.display = 'block';
		
		
		
		$id2 = $id2;
		$id_fk = $id_fk;
		$quantidade = $quantidade;
		$marca = $marca;
		$origem = $origem;
		$nome = $nome;
		document.getElementById("id2form").value = $id2;
		document.getElementById("id_fkform").value = $id_fk;
		document.getElementById("qtdeform").value = $quantidade;
		document.getElementById("marcaform").value = $marca;
		document.getElementById("origemform").value = $origem;
		document.getElementById("nomeform").value = $nome;
		
	}
</script>

<script type="text/javascript">
function seleciona(){
	var select = document.getElementById("selecaotipo");
	var value = select.options[select.selectedIndex].value;
	$idtipo = value;
	var id = select.options[select.selectedIndex].id;
	$tipo = id;
	var id2 = select.options[2].value;
	$naadega = id2;
	//alert($notnull);
	document.getElementById("select").value = $tipo;
	document.getElementById("id3form").value = $idtipo;
	document.getElementById("naadega").value = $naadega;
	document.getElementById("checkbox2").submit();
	
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
		<input class="radiobotao" type="radio" name="opcao"
			Onclick="Paratipos()" /> GERENCIAR BEBIDAS <br /> <input
			class="radiobotao" type="radio" name="opcao" checked /> BEBIDAS NA
		ADEGA <br />
	</div>

	<div id="tipos">TIPO:</div>

	<div>
		<select id="selecaotipo" onChange="seleciona()">
			<option>${nometipo}</option>
			<c:forEach var="tipo" items="${tipo}">
				<c:if test="${tipo.tipo != nometipo}">
					<option id="${tipo.tipo}" value="${tipo.id}">${tipo.tipo}</option>
					<option style="display: none" value="${tipo.naadega}">
				</c:if>
			</c:forEach>
		</select>
	</div>




	<c:set var="tiponaadega" scope="session" value="${naadega}" />
	<c:if test="${tiponaadega == 1 && nometipo != null}">
		<div id="selo">NA ADEGA</div>
	</c:if>



	<div id="tabelabebidas">
		<table class="tabela2" border="1">
			<thead>
				<tr align="center">
					<th>Quantidade</th>
					<th>Marca</th>
					<th>Origem</th>
					<th>Nome</th>
					<th colspan="2">Opção</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bebida" items="${bebida}">
					<tr align="center">
						<td style="display: none"><c:out value="${bebida.id2}" /></td>
						<td style="display: none"><c:out value="${bebida.id_fk}" /></td>
						<td><c:out value="${bebida.quantidade}" /></td>
						<td><c:out value="${bebida.marca}" /></td>
						<td><c:out value="${bebida.origem}" /></td>
						<td><c:out value="${bebida.nome}" /></td>
						<td class="bordas">
							<button
								onclick="mostrar2(${bebida.id2},${bebida.id_fk},${bebida.quantidade},'${bebida.marca}','${bebida.origem}','${bebida.nome}')"
								class="botoestabela">
								<img id="imagemicone1" src="imagens/editar3.png">
							</button>



							<div id="formulariotipo2">

								<form id="teste2"
									action="http://localhost:8080/smart_adega_oficial/Controlador"
									method="post" style="display: none">
									<input type="hidden" name="tabela" value="2"> <input
										type="hidden" name="tipocrud" value="23"> <input
										type="hidden" id="id2form" name="testeid" value="">
									<div>
										<input type="hidden" id="id_fkform" name="id_fk" value="">
										Quantidade:<input type="number" id="qtdeform"
											name="quantidade" value="" required> Marca:<input
											type="text" id="marcaform" name="marca" value="" required>
										Origem:<input type="text" id="origemform" name="origem"
											value="" required> Nome:<input type="text"
											id="nomeform" name="nome" value="" required>


									</div>
									<button type="button" class="cancelar" onclick="cancelaredit()">Cancelar</button>
									<button class="botaocadastrar4">SALVAR</button>
								</form>
							</div>
						</td>

						<td style="border-left: none">
							<form
								action="http://localhost:8080/smart_adega_oficial/Controlador"
								method="post">
								<input type="hidden" name="tabela" value="2"> <input
									type="hidden" name="tipocrud" value="24"> <input
									type="hidden" name="testeid3" value="${bebida.id2}"> <input
									type="hidden" name="id_fk" value="${bebida.id_fk}">
								<button type="submit" class="botoestabela">
									<img id="imagemicone2" src="imagens/lixo2.png">
								</button>
							</form>

						</td>

					</tr>

				</c:forEach>

			</tbody>
		</table>
	</div>

	<form id="checkbox"
		action="http://localhost:8080/smart_adega_oficial/Controlador"
		method="post" style="display: none">
		<input type="hidden" name="tabela" value="1"> <input
			type="hidden" name="tipocrud" value="11">
		<button type="submit" class="botoestabela"></button>
	</form>

	<div>

		<button class="botaocadastrar2" onclick="mostrar1(${idtipo})">CADASTRAR</button>
	</div>

	<div id="formulariotipo1">
		<form id="teste1" style="display: none"
			action="http://localhost:8080/smart_adega_oficial/Controlador"
			method="post">
			<input type="hidden" name="tabela" value="2"> <input
				type="hidden" name="tipocrud" value="22"> <input
				type="hidden" name="id_fk" id="id_fkteste" value="">
			Quantidade:<input type="number" name="quantidade" value="" required>
			Marca:<input type="text" name="marca" value="" required>
			Origem:<input type="text" name="origem" value="" required>
			Nome:<input type="text" name="nome" value="" required>
			<button type="button" class="cancelar" onclick="cancelarcadastro()">Cancelar</button>
			<button class="botaocadastrar4">SALVAR</button>
		</form>
	</div>

	<form id="checkbox2"
		action="http://localhost:8080/smart_adega_oficial/Controlador"
		method="post" style="display: none">
		<input type="hidden" name="tabela" value="2"> <input
			type="hidden" name="tipocrud" value="25"> <input
			type="hidden" name="testeid4" id="id3form" value=""> <input
			type="hidden" name="tipo" id="select" value=""> <input
			type="hidden" name="naadega" id="naadega" value="">

		<button type="submit"></button>
	</form>


	<div>
	<form 
		action="http://localhost:8080/smart_adega_oficial/Controlador"
		method="post" >
		<input type="hidden" name="tabela" value="3"> <input
			type="hidden" name="tipocrud" value="32">
		<button id="botaovoltar10" Onclick="Paramonitor()">MONITOR</button>
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