function save(key, remote) {
	var key = key;
	var data = remote.getData();

	// localStorage setItem
	if ("localStorage" in window) {
		localStorage.setItem(key, JSON.stringify(data));
		
	} else {
		alert("no localStorage in window");
	}
}

function load(key) {
	var key = key;
	// localStorage setItem
	if ("localStorage" in window) {
		var r = JSON.parse(localStorage.getItem(key));
		var remote = new Remote([]);
		if (r) {
			$.each(r, function(index, value) {
				remote.addButton(new Button(value.ip, value.label,
						value.family, value.device));

			});
		}
		return remote;
		// location.reload();
	} else {
		alert("no localStorage in window");
	}
}