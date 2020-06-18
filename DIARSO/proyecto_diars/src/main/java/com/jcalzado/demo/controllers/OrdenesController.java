package com.jcalzado.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.jcalzado.demo.model.Producto;
import com.jcalzado.demo.service.CategoriaService;
import com.jcalzado.demo.service.ProductoService;
import com.jcalzado.demo.service.UsuarioService;

@Controller
public class OrdenesController {
	@Autowired
	@Qualifier("productoservice")
	private ProductoService productoService;

	@Autowired
	@Qualifier("categoriaservice")
	private CategoriaService categoriaservice;

	@Autowired
	@Qualifier("usuarioservice")
	private UsuarioService usuarioservice;
	
	@GetMapping("/checkout")
	public String listar(Model model) {
		model.addAttribute("producto", new Producto());
		return "checkout";
	}
}
