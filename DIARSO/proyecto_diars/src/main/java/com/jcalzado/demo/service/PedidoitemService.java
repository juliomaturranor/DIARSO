package com.jcalzado.demo.service;

import java.util.List;
import java.util.Optional;
import com.jcalzado.demo.model.Pedidoitems;

public interface PedidoitemService {
	
	public abstract int guardar(Pedidoitems p);
	public abstract List<Pedidoitems>listar(int idpedido);
	public abstract void eliminar(int id);
	public Optional<Pedidoitems>mostrar(int id);
	
}
