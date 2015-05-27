$(document).ready(function() {
	removeProduct = function(productIndex) {
		if (productIndex === null || productIndex === undefined) {
			alert("Product index does not exist");
			return;
		}
		var url = "shoppingCart.do";
		$.ajax({
			url : url,
			data : {
				productIndex : productIndex
			},
			type : "get"
				
		}).done(function(data) {
			
			$("#content").html(data);
			
		});
	}
})
