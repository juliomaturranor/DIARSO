
function eliminar(id) {
	swal({
		  title: "¿Estas seguro de eliminar?",
		  text: "Eliminaras este producto del stock",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				   url:"/eliminar/"+id,
				   success: function(res){
					   console.log(res);
				   }
			  });
		    swal("Producto eliminado", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok) {
		    		location.href="/lproducto";
		    	}
		    });
		  } else {
		    swal("No se elimino el producto");
		  }
		});
}

var diarsfy = {
	version: "1.0.1",
	moneda: "S/. ",
	bienvenido: function(){
		return "Carrito de compras v."+this.version;
	},
	objectToJson: function objectToJson(strObj){
		var tmp = strObj.replace("Producto [", "").replace("]", "");
		tmp = tmp.split(",");
		var jsonText = {};
		for(var i=0; i<tmp.length; i++){
			var part1 = tmp[i].replace(/^\s+|\s+$/g, "");
			if(part1!==""){
				var part2 = part1.split("=");
				if(part2.length===2){
					jsonText[part2[0]] = part2[1]!==""?(isNaN(part2[1])?part2[1]:parseFloat(part2[1])):"";
				}
			}
		}
		return jsonText;
	}, 
	isJSON: function isJSON(str){
	    try {
	        JSON.parse(str);
	    } catch (e) {
	        return false;
	    }
	    return true;
	},
	formatoMoneda: function formatoMoneda(amount, decimals=2){
		var _this = this;
	    amount += ''; // por si pasan un numero en vez de un string
	    amount = parseFloat(amount.replace(/[^0-9\.]/g, '')); // elimino cualquier cosa que no sea numero o punto

	    decimals = decimals || 0; // por si la variable no fue fue pasada

	    // si no es un numero o es igual a cero retorno el mismo cero
	    if (isNaN(amount) || amount === 0) 
	        return parseFloat(0).toFixed(decimals);

	    // si es mayor o menor que cero retorno el valor formateado como numero
	    amount = '' + amount.toFixed(decimals);

	    var amount_parts = amount.split('.'),
	        regexp = /(\d+)(\d{3})/;

	    while (regexp.test(amount_parts[0]))
	        amount_parts[0] = amount_parts[0].replace(regexp, '$1' + ',' + '$2');

	    return _this.moneda+amount_parts.join('.');
	},
	renderMiniCart: function renderMiniCart(){
		var _this = this;
		if($('.mini-cart-icon').length){
			var totalItems = 0;
			var carritojs = $.cookie('carritojs');
			if(typeof(carritojs)!=="undefined" && _this.isJSON(carritojs)){
				var carrito = JSON.parse(carritojs);
				for(var i=0; i<carrito.items.length; i++){
					totalItems += carrito.items[i].cantidad;
				}
			}
			$('.mini-cart-icon').find('label').html(totalItems>0?('('+totalItems+')'):'');
		}
	}, 
	renderCarrito: function renderCarrito(){
		var _this = this;
		if($('.container-carrito-js').length){
			var totalItems = 0;
			var totalPrice = 0;
			var carritojs = $.cookie('carritojs');
			var _table = '';
			if(typeof(carritojs)!=="undefined" && _this.isJSON(carritojs)){
				var carrito = JSON.parse(carritojs);
				totalPrice = carrito.total;
				for(var i=0; i<carrito.items.length; i++){
					var row = carrito.items[i];
					totalItems += row.cantidad;
					
					_table += '<tr>';
						_table += '<td scope="row">'+(i+1)+'</th>';
						_table += '<td class="picture-cart"><img src="uploads/'+row.foto+'" alt="'+row.nombre+'" /></td>';
						_table += '<td class="description-cart">'+row.nombre+' <small>'+row.descripcion+'</small></td>';
						_table += '<td class="quantity-cart">'+row.cantidad+'</td>';
						_table += '<td class="money-cart">'+_this.formatoMoneda(row.subtotal)+'</td>';
						_table += '<td class="remove-cart"><a href="javascript:void(0);" class="delete-item-cart" data-id="'+row.id+'"><svg class="svg-inline--fa fa-trash fa-w-14" aria-hidden="true" data-prefix="fa" data-icon="trash" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M0 84V56c0-13.3 10.7-24 24-24h112l9.4-18.7c4-8.2 12.3-13.3 21.4-13.3h114.3c9.1 0 17.4 5.1 21.5 13.3L312 32h112c13.3 0 24 10.7 24 24v28c0 6.6-5.4 12-12 12H12C5.4 96 0 90.6 0 84zm415.2 56.7L394.8 467c-1.6 25.3-22.6 45-47.9 45H101.1c-25.3 0-46.3-19.7-47.9-45L32.8 140.7c-.4-6.9 5.1-12.7 12-12.7h358.5c6.8 0 12.3 5.8 11.9 12.7z"></path></svg></a></td>';
					_table += '</tr>';
				}
			}
			$('.carrito-items-all').html(totalItems>0?('Tienes '+totalItems+' items'):'Tu carrito está vacío');
			$('.carrito-items-table tbody').html(_table!==''?_table:'<tr><td colspan="6"></td></tr>');
			$('.subtotal-cart').html(_this.formatoMoneda(totalPrice));
		}
	},
	vaciarCarrito: function vaciarCarrito(){
		$.cookie('carritojs', undefined, { expires: 0.5 });
	}, 
	agregarAlCarrito: function agregarAlCarrito(objAdd, objProd, _callback){
		var _this = this;
		var respuestaJS = { processOk: false, carritojs: null, mensaje: "" };
		var validacionesOk = true;
		
		//validaciones
		if(!objAdd.id || !objAdd.cantidad){
			respuestaJS.mensaje = "Los campos id y cantidad son obligatorios en el objeto 'Agregar'";
			validacionesOk = false;
		}
		if(!objProd.idproducto){
			respuestaJS.mensaje = "Los campos del producto enviado no cumplen con el formato requerido";
			validacionesOk = false;
		}
		if(objAdd.id !== objProd.idproducto){
			respuestaJS.mensaje = "Los ids enviados no coinciden";
			validacionesOk = false;
		}
		if(!validacionesOk){
			setTimeout(function(){
				if(typeof(_callback)==="function"){
					_callback(respuestaJS);
				}else{
					console.error(respuestaJS.mensaje);
				}
			}, 1500);
			return false;
		}
		
		//proceso
		var carrito = {};
		var carritojs = $.cookie('carritojs');
		if(typeof(carritojs)!=="undefined" && _this.isJSON(carritojs)){
			carrito = JSON.parse(carritojs);
		}else{
			carrito = {
				usuario: {},
				items: [], 
				total: 0
			};
		}
		var _procesarLuego = true;
		var _total = 0;
		for(var i=0; i<carrito.items.length; i++){
			if(carrito.items[i].id === objAdd.id){
				_procesarLuego = false;
				var curr = carrito.items[i];
				var _cantidad = curr.cantidad+objAdd.cantidad;
				var _subtotal = _cantidad * curr.precio;
				carrito.items[i].cantidad = _cantidad;
				carrito.items[i].subtotal = _subtotal;
			}
			_total += carrito.items[i].subtotal;
		}
		if(_procesarLuego){
			_total += (objProd.precio*objAdd.cantidad);
			var newAdd = {
				id: objProd.idproducto,// id de producto
				cantidad: objAdd.cantidad, // cantidad numérica
				precio: objProd.precio,//  precio a aplicar
				subtotal: (objAdd.cantidad*objProd.precio),
				nombre: objProd.nombre, 
				stock: objProd.cantidad, 
				descripcion: objProd.descripcion, 
				estado: objProd.estado, 
				foto: objProd.foto, 
				categorias: objProd.categorias
			};
			carrito.items.push(newAdd);
		}
		carrito.total = _total;
		
		setTimeout(function(){
			$.cookie('carritojs', JSON.stringify(carrito), { expires: 0.5 })
			
			respuestaJS.processOk = true;
			respuestaJS.carritojs = carrito;
			_this.renderMiniCart();
			
			if(typeof(_callback)==="function")
				_callback(respuestaJS);
		}, 1500);
	}
};

$(document).on('click', '.add-to-cart', function(e){
	e.preventDefault();
	var button = $(this);
	var producto = diarsfy.objectToJson(button.attr("data-product"));
	var cantidad = 1;
	var agregar = {
		id: producto.idproducto,// id de producto
		cantidad: cantidad // cantidad numérica
	};
	button.attr("disabled", "disabled");
	button.html("Agregando...");
	diarsfy.agregarAlCarrito(agregar, producto, function(res){
		//callback
		console.log(res);
		button.removeAttr("disabled");
		button.html("Agregar a carrito");
	});
});

$(document).ready(function(){
	console.log(diarsfy.bienvenido());
	diarsfy.renderMiniCart();
	diarsfy.renderCarrito();
});
