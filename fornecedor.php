<html>
<head>
<title>Try Session JSON</title>
</head>
<body>
<?php

echo "Hello World";

	$dbusername = "root";  
    $dbpassword = ""; 
    $server = "localhost"; 

$dbconnect = mysqli_connect($server, $dbusername, $dbpassword);
    $dbselect = mysqli_select_db($dbconnect, "smart_adega_database");

	//$sql="SELECT onoff FROM ligadesliga";

	$sql="SELECT ligadesliga.onoff, lpad((select cast(propriedades.temp_de_servico as decimal(10, 2))
from propriedades where naadega=1),5,'0') , lpad((select cast(propriedades.tolerancia as decimal(10, 2))
from propriedades where naadega=1),5,'0') FROM ligadesliga, propriedades where naadega = 1";

	$records=mysqli_query($dbconnect,$sql);
	$json_array=array();
	
	while($row=mysqli_fetch_assoc($records))
	{
		$json_array[]=$row;
		
	}
		/*echo '<pre>';
		print_r($json_array);
		echo '</pre>';*/
		
		$input = "{\"sensor\":\"gps\",\"time\":1351824120,\"data\":[48.756080,2.302038]}";
	
		
	echo json_encode($json_array);
	//echo json_encode($input);
	
	
?>
</body>
</html>