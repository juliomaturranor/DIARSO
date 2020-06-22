package com.jcalzado.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.jcalzado.demo.dao.Pedidopagodao;
import com.jcalzado.demo.model.Pedidopagos;
import com.jcalzado.demo.service.PedidopagoService;

@Service("pedidopagoservice")
public class PedidopagoServiceImpl implements PedidopagoService{

	@Autowired
	@Qualifier("pedidopagodao")
	private Pedidopagodao pedidopagodao;

	@Override
	public int guardar(Pedidopagos p) {
		int res=0;
		Pedidopagos pedidopago = pedidopagodao.save(p);
		if(!pedidopago.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void eliminar(int id) {
		pedidopagodao.deleteById(id);
	}

	@Override
	public List<Pedidopagos> listar(int idpedido) {
		List<Pedidopagos> pedidopagos = new ArrayList<>();
		List<Pedidopagos> all = pedidopagodao.findAll();
		for(Pedidopagos pa: all) {
			if(pa.getPedido().getIdpedido() == idpedido) {
				pedidopagos.add(pa);
			}
		}
		return pedidopagos;
	}

	@Override
	public Optional<Pedidopagos> mostrar(int id) {
		return pedidopagodao.findById(id);
	}
	
}
