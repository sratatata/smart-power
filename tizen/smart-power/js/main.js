(function() {
	var page = document.getElementById("pageSwipeList"), listElement = page
			.getElementsByClassName("ui-swipelist-list")[0], swipeList;

	var remote = load("r1");
	
	if(remote){
		remote.render($("#buttons"));
	}else{
		remote = new Remote([]);
	}
	
	$(".ui-popup-content button[name='save']").on("click", function() {
		var ip = $('input[name="ip"]').first().val();
		var label = $('input[name="label"]').first().val();
		var device = $('input[name="device"]').first().val();
		var family = $('input[name="family"]').first().val();
		var b = new Button(ip, label, family, device);
		remote.addButton(b);
		save("r1", remote);
		tau.closePopup();
		location.reload();
	});
	
	$(".ui-popup-content button[name='cancel']").on("click", function() {
		tau.closePopup();
	});
	
	

	page.addEventListener("pageshow", function() {
		/* Make swipe list object */
		swipeList = new tau.widget.SwipeList(listElement, {
			swipeTarget : "li",
			swipeElement : ".ui-swipelist"
		});

	});
	page.addEventListener("pagehide", function() {
		/* Release object */
		swipeList.destroy();
	});
})();