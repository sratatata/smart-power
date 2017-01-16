function Button(label, family, device) {
	this.label = label;
	this.family = family;
	this.device = device;

	this.on = function() {

		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET", "http://192.168.1.15/rc/on?family=" + this.family
				+ "&device=" + this.device);
		xmlHttp.send();
	};

	this.off = function() {
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET", "http://192.168.1.15/rc/off?family="
				+ this.family + "&device=" + this.device);
		xmlHttp.send();
	};
	
	this.class_on = function(){
		return this.label.toLowerCase() + "_on";
	}
	
	this.class_off = function(){
		return this.label.toLowerCase() + "_off";
	}
}

function Remote(buttons) {
	this.buttons = buttons;

	this.render = function(element) {
		$.each(buttons, function(index , value) {
			element.append("<tr><td>"+value.label+'</td><td><button class="'+value.class_on()+'">On</button></td><td><button class="'+value.class_off()+'">Off</button></td></tr>');
			$("button."+value.class_on()).click(function(){
				value.on();
			});
			$("button."+value.class_off()).click(function(){
				value.off();
			});
		});
	};
}