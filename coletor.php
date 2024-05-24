<?php

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "smart_adega_database";

	// Cria conexão
	$linkDB = mysqli_connect($servername, $username, $password,$dbname);

	// Estabelece conexão UTF-8
	//mysqli_query("SET NAMES utf8");
	//mysqli_query("SET CHARACTER SET utf8");

	//Pega horário e data atual
	date_default_timezone_set('America/Fortaleza'); //America/Fortaleza  America/Sao_Paulo
	$dataHora = date('Y-m-d H:i:s');

	//Pega distância e endereço IP do Nó Sensor
	$enderecoIP = $_POST["enderecoIP"];
	$valorSensor = $_POST["valorsensor"];

	//Cria SQL para inserir no Banco de Dados
//	$sql1 = "DELETE FROM tabela";	// teste1
//	$sql2 = "ALTER TABLE tabela AUTO_INCREMENT = 1"; // teste2
	$sql = "INSERT INTO tabela(dataHora,enderecoIP,valorSensor) VALUES('$dataHora','$enderecoIP','$valorSensor')";

	//Executa a query no banco de dados
	$resultado = mysqli_query($linkDB,/*$sql1,$sql2,*/$sql); 

	//Libera os recursos utilizados
	if ($resultado) {
		//Encerra a conexão com o banco de dados
		mysqli_free_result($resultado);
		mysqli_close();
		
	}
	
?>
