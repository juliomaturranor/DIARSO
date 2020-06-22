package com.jcalzado.demo.service;

import java.util.List;
import java.util.Optional;
import com.jcalzado.demo.model.Pedidoenvios;

public interface PedidoenvioService {

	public abstract int guardar(Pedidoenvios p);
	public abstract List<Pedidoenvios>listar(int idpedido);
	public abstract void eliminar(int id);
	public Optional<Pedidoenvios>mostrar(int id);
	
}
