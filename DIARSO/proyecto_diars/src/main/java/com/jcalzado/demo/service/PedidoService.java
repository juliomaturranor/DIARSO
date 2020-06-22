package com.jcalzado.demo.service;

import java.util.List;
import java.util.Optional;
import com.jcalzado.demo.model.Pedido;

public interface PedidoService {

	public abstract int guardar(Pedido p);
	public abstract List<Pedido>listar();
	public abstract void eliminar(int id);
	public Optional<Pedido> mostrar(int id);
	public Pedido buscarxCodigo(String cod);

}
