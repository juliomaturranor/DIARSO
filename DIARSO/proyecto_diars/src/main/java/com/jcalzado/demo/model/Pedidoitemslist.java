package com.jcalzado.demo.model;

import java.io.Serializable;
import javax.persistence.Column;

public class Pedidoitemslist implements Serializable{

	@Column
	private String lidproducto;
	
	@Column
	private String lcantidad;
	
	@Column
	private String lprecio;
	
	@Column
	private String lsubtotal_item;
	
	@Column
	private String lnombre;
	
	@Column
	private String ldescripcion;
	
	@Column
	private String lfoto;
	
	public String getLidproducto() {
		return lidproducto;
	}

	public void setLidproducto(String lidproducto) {
		this.lidproducto = lidproducto;
	}
	
	public String getLcantidad() {
		return lcantidad;
	}

	public void setLcantidad(String lcantidad) {
		this.lcantidad = lcantidad;
	}
	
	public String getLprecio() {
		return lprecio;
	}

	public void setLprecio(String lprecio) {
		this.lprecio = lprecio;
	}
	
	public String getLsubtotal_item() {
		return lsubtotal_item;
	}

	public void setLsubtotal_item(String lsubtotal_item) {
		this.lsubtotal_item = lsubtotal_item;
	}

	public String getLnombre() {
		return lnombre;
	}

	public void setLnombre(String lnombre) {
		this.lnombre = lnombre;
	}

	public String getLdescripcion() {
		return ldescripcion;
	}

	public void setLdescripcion(String ldescripcion) {
		this.ldescripcion = ldescripcion;
	}
	
	public String getLfoto() {
		return lfoto;
	}

	public void setLfoto(String lfoto) {
		this.lfoto = lfoto;
	}
	
	@Override
	public String toString() {
		return "Pedidoitemlist [lcantidad=" + lcantidad + ", lprecio=" + lprecio + ", lsubtotal_item=" + lsubtotal_item + ", lnombre="
				+ lnombre + ", ldescripcion=" + ldescripcion + ", lfoto=" + lfoto
				+ "]";
	}

	private static final long serialVersionUID = 1L;
}
