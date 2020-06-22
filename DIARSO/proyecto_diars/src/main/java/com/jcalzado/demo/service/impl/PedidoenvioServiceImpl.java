package com.jcalzado.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.jcalzado.demo.dao.Pedidoenviodao;
import com.jcalzado.demo.model.Pedidoenvios;
import com.jcalzado.demo.service.PedidoenvioService;

@Service("pedidoenvioservice")
public class PedidoenvioServiceImpl implements PedidoenvioService{

	@Autowired
	@Qualifier("pedidoenviodao")
	private Pedidoenviodao pedidoenviodao;

	@Override
	public int guardar(Pedidoenvios p) {
		int res=0;
		Pedidoenvios pedidoenvio = pedidoenviodao.save(p);
		if(!pedidoenvio.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void eliminar(int id) {
		pedidoenviodao.deleteById(id);
	}

	@Override
	public List<Pedidoenvios> listar(int idpedido) {
		List<Pedidoenvios> pedidoenvios = new ArrayList<>();
		List<Pedidoenvios> all = pedidoenviodao.findAll();
		for(Pedidoenvios pe: all) {
			if(pe.getPedido().getIdpedido() == idpedido) {
				pedidoenvios.add(pe);
			}
		}
		return pedidoenvios;
	}

	@Override
	public Optional<Pedidoenvios> mostrar(int id) {
		return pedidoenviodao.findById(id);
	}
	
}
