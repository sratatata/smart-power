window.onload = function() {
	// TODO:: Do your initialization job

	// add eventListener for tizenhwkey
	document.addEventListener('tizenhwkey', function(e) {
		if (e.keyName === "back") {
			try {
				tizen.application.getCurrentApplication().exit();
			} catch (ignore) {
			}
		}
	});

	var a = new Button("Zasilacz", "01111", "10000");

	var remote = new Remote([ 
	                         new Button("Choinka", "01111", "00010") ,
	                         new Button("Zasilacz", "01111", "10000"), 
	                         new Button("Lampka", "11010", "00100"),
	                         new Button("Farelka", "11010", "00010"), 
	                         new Button("Drukarka", "11010", "10000") 
	                          ]);

	remote.render($("#buttons"));

};