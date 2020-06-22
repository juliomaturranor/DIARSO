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
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="pedido_envios")
public class Pedidoenvios implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idpedidoenvio;
	
	@Column
	@NotEmpty
	private String tipo_direccion;
	
	@Column
	@NotEmpty
	private String direccion1;
	
	@Column
	private String direccion2;
	
	@Column
	private String recibe_pedido;
	
	@Column
	private boolean estado;
	
	@OneToOne
	@JoinColumn(name="idpedido")
	private Pedido pedido;
	
	public int getIdpedidoenvio() {
		return idpedidoenvio;
	}

	public void setIdpedidoenvio(int idpedidoenvio) {
		this.idpedidoenvio = idpedidoenvio;
	}
	
	public String getTipo_direccion() {
		return tipo_direccion;
	}

	public void setTipo_direccion(String tipo_direccion) {
		this.tipo_direccion = tipo_direccion;
	}
	
	public String getDireccion1() {
		return direccion1;
	}

	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}
	
	public String getDireccion2() {
		return direccion2;
	}

	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}
	
	public String getRecibe_pedido() {
		return recibe_pedido;
	}

	public void setRecibe_pedido(String recibe_pedido) {
		this.recibe_pedido = recibe_pedido;
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
		return "Pedidoenvio [idpedidoenvio=" + idpedidoenvio + ", tipo_direccion=" + tipo_direccion + ", direccion1=" + direccion1
				+ ", direccion2=" + direccion2 + ", recibe_pedido=" + recibe_pedido + ", estado=" + estado 
				+ ", pedido="
				+ "]";
	}

	private static final long serialVersionUID = 1L;
}
