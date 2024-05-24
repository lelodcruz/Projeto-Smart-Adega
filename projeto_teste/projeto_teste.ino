#include <ESP8266WiFi.h>
#include <OneWire.h>
#include <DallasTemperature.h>
#include <ESP8266HTTPClient.h>
#define ONE_WIRE_BUS 2

OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);

const char* SSID = "WifiCruz2.4g"; // SSID casa: WifiCruz2.4g WifiCruz5g// SSID facu: IFSP-HTO 
const char* PASS = "corrente";      //PASS casa: corrente     // PASS facu: ifsphto123
const char* server="192.168.0.18";

int rele = 13;
int ligar = 1;
float temp_de_servico = 0;
float tolerancia = 0;

void setup() {
Serial.begin(9600);
  WiFi.mode(WIFI_AP_STA);
  WiFi.begin(SSID,PASS);
/* while (WiFi.status() != WL_CONNECTED) 
      {
        delay(300); // padrao eh 300
        Serial.print(".");  
      } */ 

    Serial.print("Endereço IP obtido: ");
    Serial.println(WiFi.localIP()); 
    Serial.println("Dallas Temperature IC Control Library Demo");
    sensors.begin();
    pinMode(rele,OUTPUT); 
    digitalWrite (rele, HIGH);
}

void loop() {

  
  float valorSensor = sensors.getTempCByIndex(0);
     
    WiFiClient client;
    if (!client.connect(server,80)) {
      Serial.println("Erro - Não foi possível conectar ao servidor!");
    }
    else {
      
       String url = "/fornecedor.php?";
                

Serial.print("Requisitando URL: ");
Serial.println(url);
                          
     client.println(String("GET ") + url + " HTTP/1.1\r\n" + 
                    "Host: " + server + "\r\n" + 
                    "Connection: close\r\n\r\n"); 

      unsigned long timeout = millis();
      while (client.available() == 0) {
        if(millis() - timeout > 5000) {
          Serial.println(">>> Client Timeout !");
          client.stop();
          return;
       }
      } 
      
        while (client.available()){
        String line,line1,line2,line3;
        line = client.readStringUntil('\r');
        line1 = line.substring(line.indexOf(":") + 2, line.indexOf("}") - 234);
        line2 = line.substring(line.indexOf(":") + 117, line.indexOf("}") - 115);
        line3 = line.substring(line.indexOf(":") + 231, line.indexOf("}") - 1);
        Serial.println(line1);
        Serial.println(line2);
        Serial.println(line3);
        ligar = (line1.toInt());
        temp_de_servico = (line2.toFloat());
        tolerancia = (line3.toFloat());
        client.flush(); 

        
} 
  
      
      if(ligar==0){
        WiFiClient client2;
    if (!client2.connect(server,80)) {
      Serial.println("Erro - Não foi possível conectar ao servidor 2!");
    } else {
      IPAddress ip = WiFi.localIP();
      String enderecoIP = String(ip[0]) + "." + 
                          String(ip[1]) + "." +
                          String(ip[2]) + "." +
                          String(ip[3]);
      String valorSensorS = String(valorSensor);
      String postData = "enderecoIP=";
      postData += enderecoIP;
      postData += "&valorsensor=";
      postData += valorSensorS;
      client2.println("POST /coletor.php HTTP/1.1");
      client2.print("Host: ");
      client2.println(server);
      client2.println("Cache-Control: no-cache");
      client2.println("Content-Type: application/x-www-form-urlencoded");
      client2.print("Content-Length: ");
      client2.println(postData.length());
      client2.println();
      client2.println(postData);
} 
    }
    delay(1000);
  
  Serial.print("Requesting temperatures...");
  sensors.requestTemperatures();
  Serial.println("DONE");
  Serial.print("Temperature for the device 1 (index 0) is: ");
  Serial.println(sensors.getTempCByIndex(0));  
  delay(10000); // teste

        Serial.println(ligar);
        Serial.println(temp_de_servico);
        Serial.println(tolerancia);
 
  if (ligar == 0) {
  
  if (sensors.getTempCByIndex(0) <= (temp_de_servico - tolerancia)) { 
    digitalWrite (rele, HIGH);
    
  }
  if (sensors.getTempCByIndex(0) >= (temp_de_servico + tolerancia) && ligar == 0) { 
    digitalWrite (rele, LOW);
  }
  } else if (ligar == 1) {digitalWrite (rele, HIGH);}
    }
}
