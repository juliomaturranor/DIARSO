package com.jcalzado.demo.model;

import java.io.Serializable;
import javax.persistence.Column;

public class PaymentMethods implements Serializable{

	@Column
	private String pay_codigo;
	
	@Column
	private String pay_nombre;
	
	@Column
	private String pay_moneda;
	
	@Column
	private String pay_imagen;
	
	public String getPay_codigo() {
		return pay_codigo;
	}

	public void setPay_codigo(String pay_codigo) {
		this.pay_codigo = pay_codigo;
	}
	
	public String getPay_nombre() {
		return pay_nombre;
	}

	public void setPay_nombre(String pay_nombre) {
		this.pay_nombre = pay_nombre;
	}
	
	public String getPay_moneda() {
		return pay_moneda;
	}

	public void setPay_moneda(String pay_moneda) {
		this.pay_moneda = pay_moneda;
	}
	
	public String getPay_imagen() {
		return pay_imagen;
	}

	public void setPay_imagen(String pay_imagen) {
		this.pay_imagen = pay_imagen;
	}
	
	@Override
	public String toString() {
		return "Paymentmethod [pay_codigo=" + pay_codigo + ", pay_nombre=" + pay_nombre + ", pay_imagen=" + pay_imagen
				+ "]";
	}

	private static final long serialVersionUID = 1L;
}
