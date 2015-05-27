$(document).ready(function() {

	$("#content").load("getNewProducts.do");

	$('#changeBackgrnd').on('change', function() {
		$("div").css("backgroundColor", this.value);
	});

	resetButton = function() {
		$(".button").each(function() {
			var currentColor = $(this).css("backgroundColor");
			if (currentColor == "rgb(78, 238, 148)") {
				$(this).css("backgroundColor", "rgb( 230, 185, 129)");
			}
		})
	}

	changeButtonColor = function(buttonId) {
		if (buttonId === null || buttonId === undefined) {
			alert("Button id does not exist");
			return;
		}
		resetButton();
		$("#" + buttonId).css("backgroundColor", "rgb( 78, 238, 148)")
	}

	nextPage = function(buttonId) {
		if (buttonId === null || buttonId === undefined) {
			alert("Button id does not exist");
			return;
		}
		var buttonValue = $("#" + buttonId).val();
		
		changeButtonColor(buttonId);
		
		switch (buttonValue) {
		case "Main":
			$("#content").load("getNewProducts.do");
			break;

		case "Catalogue":
			var url = "getProductList.do";

			$.ajax({

				url : "getProductList.do",
				data : {
					getMain : status
				},
				type : "get",
				success : function(data) {
					
					$('#content').html(data);
					
					changeBackgrndLoadPage();
				}
			})

			break;

		case "Shopping cart":
			var url = "shoppingCart.do";
			$.ajax({
				url : url,
				data : {
					getShoppingCart : "genShoppingCart"
				},
				type : "get"
			}).done(function(data) {
				
				$("#content").html(data);
				
				changeBackgrndLoadPage();
			});
			break;
		}
	}
});

function changeBackgrndLoadPage() {
	var currentColor = document.getElementById('menu').style.backgroundColor;
	if (currentColor != "") {
		var count = document.getElementById("content").getElementsByTagName(
				"div").length;
		for (var i = 0; count > i; i++) {
			document.getElementById("content").getElementsByTagName("div")[i].style.backgroundColor = currentColor;
		}

	} else {}

}