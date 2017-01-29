function Button(ip, label, family, device) {
	this.ip = ip;
	this.label = label;
	this.family = family;
	this.device = device;

	this.on = function() {

		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET", "http://" + this.ip + "/rc/on?family="
				+ this.family + "&device=" + this.device);
		xmlHttp.send();
	};

	this.off = function() {
		var xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET", "http://" + this.ip + "/rc/off?family="
				+ this.family + "&device=" + this.device);
		xmlHttp.send();
	};

	this.class_on = function() {
		return this.label.toLowerCase() + "_on";
	}

	this.class_off = function() {
		return this.label.toLowerCase() + "_off";
	}
}

function Remote(buttons) {
	this.buttons = buttons;

	this.render = function(element) {
		var that = this;
		$.each(this.buttons,
				function(index, value) {
					element.append("<li id='b_" + index + "'>" + value.label
							+ '</li>');

					var b = document.getElementById('b_' + index);
					b.addEventListener("swipelist.left", function(evt) {
						value.off();
					});

					b.addEventListener("swipelist.right", function(evt) {
						value.on();
					});
				});
	};

	this.addButton = function(button) {
		this.buttons.push(button);
	}

	this.getData = function() {
		return this.buttons;
	}
}