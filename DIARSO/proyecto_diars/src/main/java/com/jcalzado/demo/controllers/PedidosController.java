package com.jcalzado.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.jcalzado.demo.model.PaymentMethods;
import com.jcalzado.demo.model.Pedido;
import com.jcalzado.demo.model.Pedidoenvios;
import com.jcalzado.demo.model.Pedidoitems;
import com.jcalzado.demo.model.Pedidopagos;
import com.jcalzado.demo.model.Producto;
import com.jcalzado.demo.service.PedidoService;
import com.jcalzado.demo.service.PedidoenvioService;
import com.jcalzado.demo.service.PedidoitemService;
import com.jcalzado.demo.service.PedidopagoService;
import com.jcalzado.demo.service.UsuarioService;
import com.jcalzado.demo.util.Constants;
import com.jcalzado.demo.util.Functions;

@Controller
public class PedidosController {

	@Autowired
	@Qualifier("usuarioservice")
	private UsuarioService usuarioservice;
	
	@Autowired
	@Qualifier("pedidoservice")
	private PedidoService pedidoServ;
	
	@Autowired
	@Qualifier("pedidoenvioservice")
	private PedidoenvioService penvServ;
	
	@Autowired
	@Qualifier("pedidoitemservice")
	private PedidoitemService pitmServ;
	
	@Autowired
	@Qualifier("pedidopagoservice")
	private PedidopagoService pagoServ;
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		List<PaymentMethods> metodos_pago = new ArrayList<>();
		for(String p: Functions.paymentMethods()) {
			String[] part = p.split(";");
			PaymentMethods pm = new PaymentMethods();
			pm.setPay_codigo(part[0]);
			pm.setPay_nombre(part[1]);
			pm.setPay_moneda(part[2]);
			pm.setPay_imagen(part[3]);
			metodos_pago.add(pm);
		}
		model.addAttribute("producto", new Producto());
		model.addAttribute("pedido", new Pedido());
		model.addAttribute("pedidopagos", new Pedidopagos());
		model.addAttribute("metodos_pago", metodos_pago);
		model.addAttribute("tipo_direccion", Constants.tipo_direccion.values());
		model.addAttribute("tipo_moneda", Constants.tipo_moneda.PEN);
		model.addAttribute("tipo_estadopago", Constants.estados_pago.PENDIENTE);
		model.addAttribute("idusuario", 0);
		return "checkout";
	}
	
	@GetMapping("/thankyou/{codigopedido}")
	public String thankyou(@PathVariable String codigopedido, Model model) {
		Pedido pedido = pedidoServ.buscarxCodigo(codigopedido);
		if(pedido != null) {
			List<Pedidoitems> pedido_items = pitmServ.listar(pedido.getIdpedido());
			List<Pedidoenvios> pedido_envios = penvServ.listar(pedido.getIdpedido());
			List<Pedidopagos> pedido_pagos = pagoServ.listar(pedido.getIdpedido());
			
			model.addAttribute("producto", new Producto());
			model.addAttribute("pedido", pedido);
			model.addAttribute("pedido_items", pedido_items);
			model.addAttribute("pedido_envios", pedido_envios);
			model.addAttribute("pedido_pagos", pedido_pagos);
			return "thankyou";			
		}
		return "error404";
	}
	
}
