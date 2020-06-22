package com.jcalzado.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.jcalzado.demo.dao.Pedidodao;
import com.jcalzado.demo.model.Pedido;
import com.jcalzado.demo.service.PedidoService;

@Service("pedidoservice")
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	@Qualifier("pedidodao")
	private Pedidodao pedidodao;

	@Override
	public int guardar(Pedido p) {
		int res=0;
		Pedido pedido = pedidodao.save(p);
		if(!pedido.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void eliminar(int id) {
		pedidodao.deleteById(id);
	}

	@Override
	public List<Pedido> listar() {
		return pedidodao.findAll();
	}

	@Override
	public Optional<Pedido> mostrar(int id) {
		return pedidodao.findById(id);
	}
	
	@Override
	public Pedido buscarxCodigo(String cod) {
		Pedido pedido = null;
		List<Pedido> all = pedidodao.findAll();
		for(Pedido p: all) {
			if(p.getCodigo().equals(cod)) {
				pedido = p;
			}
		}
		return pedido;
	}

}
