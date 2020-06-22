package com.jcalzado.demo.service;

import java.util.List;
import java.util.Optional;
import com.jcalzado.demo.model.Pedidopagos;

public interface PedidopagoService {
	
	public abstract int guardar(Pedidopagos p);
	public abstract List<Pedidopagos>listar(int idpedido);
	public abstract void eliminar(int id);
	public Optional<Pedidopagos>mostrar(int id);
	
}
