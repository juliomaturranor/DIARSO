
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
});
