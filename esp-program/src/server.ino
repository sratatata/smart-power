#include <ESP8266WiFi.h>          //https://github.com/esp8266/Arduino
#include <Arduino.h>

//needed for library
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <WiFiManager.h>         //https://github.com/tzapu/WiFiManager

#include <RCSwitch.h>

#include <ESP8266mDNS.h>

ESP8266WebServer server(80);
const  int led = 13;
const int buttonPin = 2;
int state = LOW;

const String hostname = "smartpowerap";

WiFiManager wifiManager;
RCSwitch mySwitch = RCSwitch();

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);


  pinMode(buttonPin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(buttonPin),reset, FALLING);

  wifiManager.autoConnect("SmartPowerAP");

  //if you get here you have connected to the WiFi
  Serial.println("connected...yeey :)");

  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  WiFi.hostname(hostname);
  MDNSConnect();
  Serial.printf("My host name is: %s\n", hostname.c_str());

  server.on("/", handleRoot);
  server.on("/rc/on", handleRcOn);
  server.on("/rc/off", handleRcOff);

  server.begin();
  Serial.println("HTTP server started");

  mySwitch.setPulseLength(161); // Przepisujemy wartość sczytaną z serial monitora
  mySwitch.enableTransmit(0); // Przepisujemy wartość sczytaną z serial monitora

}

void reset() {
  Serial.println("I'm resetting my settings!");
  delay(1000); //debounce
  wifiManager.resetSettings();
  Serial.println("I'm going to reset");
  ESP.reset();
  Serial.println("End of interruption!");
}

void handleRoot() {
  server.send(200, "text/plain", "hello from esp8266!");
}

void handleRcOn() {
  String family = server.arg("family");
  String device = server.arg("device");
  Serial.printf("Family: %s, Device: %s\n", family.c_str(), device.c_str() );

  if(family != "" || device !=""){
    mySwitch.switchOn(family.c_str(), device.c_str());
    server.send(200, "text/plain", "OK");
  }else{
    server.send(404, "text/plain", "Resource not found");
  }
}

void handleRcOff() {
  String family = server.arg("family");
  String device = server.arg("device");
  Serial.printf("Family: %s, Device: %s\n", family.c_str(), device.c_str() );

  if(family != "" || device !=""){
    mySwitch.switchOff(family.c_str(), device.c_str());
    server.send(200, "text/plain", "OK");
  }else{
    server.send(404, "text/plain", "Resource not found");
  }

}

void MDNSConnect() {
  if (!MDNS.begin(hostname.c_str())) {
    while (1) {
      delay(1000);
    }
  }
  MDNS.addService("http", "tcp", 80);
}



void loop(void){
  server.handleClient();
}
