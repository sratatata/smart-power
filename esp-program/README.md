# esp-program

It's job is to gather parameters from url and turn on/off rc switches.

Simplifications:

* Parametrized by query parameters
* Works in local network
* Wifi credentials hardcoded into rom

# Usage

Use your esp ip adress or mdns name if available:

Turn on device with switches set to 01111 and 10000.

```
http://smartpowerap.local/rc/on?family=01111&device=10000
```
Turn off device with switches set to 01111 and 10000.

```
http://smartpowerap.local/rc/off?family=01111&device=10000
```

# Development

For it's development I've used:

* [platform.io](http://platform.io/)
* [rc-switch library](https://github.com/ninjablocks/arduino/tree/master/RCSwitch)

Interesting articles:

* [ESP8266 Quick Start guide](http://rancidbacon.com/files/kiwicon8/ESP8266_WiFi_Module_Quick_Start_Guide_v_1.0.4.pdf)
* [ESP8266-webserver getting query parameters](https://techtutorialsx.wordpress.com/2016/10/22/esp8266-webserver-getting-query-parameters/)
