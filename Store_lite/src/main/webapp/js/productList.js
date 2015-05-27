$(document).ready(
		function() {

			addToShoppingCart = function() {
				var type = "";
				var genre = "";
				var name = "";
				var products = [];
				var url = "shoppingCart.do";
				$("input:checkbox[id=product]:checked").each(
						function() {
							{
								var productList = $(this).closest('tr').find(
										'input[id=product]').prop('checked',
										false);
								$(productList).each(function(index, value) {
									if (index == 0) {
									}
									if (index == 1) {
										type = $(this).val();
									} else if (index == 2) {
										genre = $(this).val();
									} else if (index == 3) {
										name = $(this).val();
									} else {
										"Wrong index"
									}
								})
								products.push(type, genre, name);
							}
						})
				$.ajax({
					type : 'post',
					url : url,
					dataType : 'json',
					data : {
						products : products
					},
					success : function() {
					}
				});
			}

			showProductListPage = function(pageNumber) {
				if (pageNumber === null || pageNumber === undefined) {
					alert("Page number does not exist");
					return;
				}
				var url = "showProductList.do"
				$.ajax({
					url : url,
					data : {
						pageNumber : pageNumber
					},
					method : "get"
				}).done(function(data) {
					$("#content").html(data);
					changeBackgrndLoadPage();
				});
			}

			showSelectedProductList = function() {
				var type;
				var genre;
				type = document.getElementById("prodType");
				type = type.options[type.selectedIndex].value;
				genre = document.getElementById("genre");
				genre = genre.options[genre.selectedIndex].text;
				var url = "getProductList.do";
				$.ajax({
					url : url,
					data : {
						type : type,
						genre : genre
					},
					method : "get"

				}).done(function(data) {
					$("#content").html(data);
					changeBackgrndLoadPage();
				});

			}
			showProductList = function(name) {
				if (name === null || name === undefined) {
					alert("Name does not exist");
					return;
				}
				var url = "getProductList.do";
				$.ajax({
					url : url,
					data : {
						name : name
					},
					method : "get"
				}).done(function(data) {
					$("#content").html(data);
					changeBackgrndLoadPage();
				});
			}

			getGenres = function(type) {
				if (type === null || type === undefined) {
					alert("Type does not exist");
					return;
				}
				var url = "getGenres.do";
				$.ajax({
					url : url,
					method : "get",
					contentType : "application/json",
					data : {
						type : type
					},
					dataType : "json",
					async : false,
					success : function(genres) {
						removeall();
						$.each(genres, function(index, value) {
							$("#genre").append($("<option />").text(value));

						});
					}
				});
			}

			removeall = function() {
				$("#genre").empty();
			}
		})

jQuery.cookie = function(name, value, options) {
	if (typeof value != 'undefined') {
		options = options || {};
		if (value === null) {
			value = '';
			options.expires = -1;
		}
		var expires = '';
		if (options.expires) {
			var date;
			if (typeof options.expires == 'number') {
				date = new Date();
				date.setTime(date.getTime()
						+ (options.expires * 24 * 60 * 60 * 1000));
			} else {
				date = options.expires;
			}
			expires = '; expires=' + date.toUTCString();
		}
		var path = options.path ? '; path=' + (options.path) : '';
		var domain = options.domain ? '; domain=' + (options.domain) : '';
		var secure = options.secure ? '; secure' : '';
		document.cookie = [ name, '=', encodeURIComponent(value), expires,
				path, domain, secure ].join('');
	} else {
		var cookieValue = null;
		if (document.cookie) {
			var cookies = document.cookie.split(';');
			for (var i = 0; cookies.length > i; i++) {
				var cookie = jQuery.trim(cookies[i]);
				if (cookie.substring(0, name.length + 1) == (name + '=')) {
					cookieValue = decodeURIComponent(cookie
							.substring(name.length + 1));
					break;
				}
			}
		}
		return cookieValue;
	}
};
if ($.cookie('remember_genres') != null) {
	$("#genre").append(
			$("<option />").text($.cookie('remember_genres')).attr('selected',
					'selected'));
}
if ($.cookie('remember_prodType') != null) {
	$('.prodType option[value="' + $.cookie('remember_prodType') + '"]').attr(
			'selected', 'selected');
}
$('.prodType').change(function() {
	$.cookie('remember_prodType', $('.prodType option:selected').val(), {
		expires : 90,
		path : '/'
	});
});
$('.genre').change(function() {
	$.cookie('remember_genres', $('.genre option:selected').val(), {
		expires : 90,
		path : '/'
	});
});