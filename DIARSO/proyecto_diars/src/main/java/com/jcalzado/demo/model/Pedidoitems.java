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
import com.sun.istack.NotNull;

@Entity
@Table(name="pedido_items")
public class Pedidoitems implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idpedidoitem;
	
	@Column
	@NotNull
	@Min(1)
	private int cantidad;
	
	@Column
	@NotNull
	@Min(1)
	private double precio;
	
	@Column
	@NotNull
	@Min(1)
	private double subtotal_item;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@Column
	private String foto;
	
	@Column
	private boolean estado;
	
	@OneToOne
	@JoinColumn(name="idpedido")
	private Pedido pedido;
	
	@OneToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public int getIdpedidoitem() {
		return idpedidoitem;
	}

	public void setIdpedidoitem(int idpedidoitem) {
		this.idpedidoitem = idpedidoitem;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public double getSubtotal_item() {
		return subtotal_item;
	}

	public void setSubtotal_item(double subtotal_item) {
		this.subtotal_item = subtotal_item;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Pedidoitem [idpedidoitem=" + idpedidoitem + ", cantidad=" + cantidad + ", precio=" + precio + ", subtotal_item="
				+ subtotal_item + ", nombre=" + nombre + ", descripcion=" + descripcion + ", foto=" + foto + ", estado=" + estado 
				+ ", pedido=" + ", producto="
				+ "]";
	}

	private static final long serialVersionUID = 1L;
}
