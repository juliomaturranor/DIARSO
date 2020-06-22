package com.jcalzado.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import com.sun.istack.NotNull;

@Entity
@Table(name="pedido_pagos")
public class Pedidopagos implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idpedidopago;
	
	@Column
	@NotEmpty
	private String metodo;
	
	@Column
	@NotNull
	@Min(1)
	private double monto;
	
	@Column
	@NotEmpty
	private String moneda;
	
	@Column
	private String cuenta;
	
	@Column
	private String cuenta_tipo;
	
	@Column
	private String cuenta_fechav;
	
	@Column
	private boolean estado;
	
	@OneToOne
	@JoinColumn(name="idpedido")
	private Pedido pedido;
	
	public int getIdpedidopago() {
		return idpedidopago;
	}

	public void setIdpedidopago(int idpedidopago) {
		this.idpedidopago = idpedidopago;
	}
	
	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	
	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	public String getCuenta_tipo() {
		return cuenta_tipo;
	}

	public void setCuenta_tipo(String cuenta_tipo) {
		this.cuenta_tipo = cuenta_tipo;
	}
	
	public String getCuenta_fechav() {
		return cuenta_fechav;
	}

	public void setCuenta_fechav(String cuenta_fechav) {
		this.cuenta_fechav = cuenta_fechav;
	}
	
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public String toString() {
		return "Pedidopago [idpedidopago=" + idpedidopago + ", metodo=" + metodo + ", monto=" + monto + ", cuenta="
				+ cuenta + ", cuenta_tipo=" + cuenta_tipo + ", cuenta_fechav=" + cuenta_fechav + ", estado=" + estado 
				+ ", pedido="
				+ "]";
	}

	private static final long serialVersionUID = 1L;
}
