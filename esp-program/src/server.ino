#include <ESP8266WiFi.h>          //https://github.com/esp8266/Arduino
#include <Arduino.h>

//needed for library
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <WiFiManager.h>         //https://github.com/tzapu/WiFiManager

ESP8266WebServer server(80);
const  int led = 13;
const int buttonPin = 2;
int state = LOW;

WiFiManager wifiManager;

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

  server.on("/", handleRoot);

  server.begin();
  Serial.println("HTTP server started");
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
  digitalWrite(led, 1);
  server.send(200, "text/plain", "hello from esp8266!");
  digitalWrite(led, 0);
}



void loop(void){
  server.handleClient();

}
