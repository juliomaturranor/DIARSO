package com.jcalzado.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.jcalzado.demo.dao.Pedidoitemdao;
import com.jcalzado.demo.model.Pedidoitems;
import com.jcalzado.demo.service.PedidoitemService;

@Service("pedidoitemservice")
public class PedidoitemServiceImpl implements PedidoitemService{

	@Autowired
	@Qualifier("pedidoitemdao")
	private Pedidoitemdao pedidoitemdao;

	@Override
	public int guardar(Pedidoitems p) {
		int res=0;
		Pedidoitems pedidoitem = pedidoitemdao.save(p);
		if(!pedidoitem.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void eliminar(int id) {
		pedidoitemdao.deleteById(id);
	}

	@Override
	public List<Pedidoitems> listar(int idpedido) {
		List<Pedidoitems> pedidoitems = new ArrayList<>();
		List<Pedidoitems> all = pedidoitemdao.findAll();
		for(Pedidoitems pi: all) {
			if(pi.getPedido().getIdpedido() == idpedido) {
				pedidoitems.add(pi);
			}
		}
		return pedidoitems;
	}

	@Override
	public Optional<Pedidoitems> mostrar(int id) {
		return pedidoitemdao.findById(id);
	}
	
}
