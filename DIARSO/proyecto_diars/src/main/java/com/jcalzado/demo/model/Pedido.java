package com.jcalzado.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idpedido;
	
	@Column
	@NotEmpty
	private String codigo;
	
	@Column
	@NotEmpty
	private String moneda;
	
	@Column
	@NotEmpty
	private String email;
	
	@Column
	private String observaciones;
	
	@Column
	@NotNull
	@Min(1)
	private double subtotal_precio;
	
	@Column
	@NotNull
	private double total_impuesto;
	
	@Column
	@NotNull
	@Min(1)
	private double total_precio;
	
	@Column
	@NotEmpty
	private String estado_pago;
	
	@Column
	private String fecha_cierre;
}
