package com.jcalzado.demo.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.stereotype.Component;

@Component
public class Functions {
	
	private Functions() {
		super();		
	}
	
	public static String currentDate() {
		String result = "";
		Date date = new Date();
		result = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		return result;
	}
	
	public static List<String> paymentMethods(){
		/*
		 * "CODIGO ; NOMBRE ; MONEDA; IMAGEN"
		 * */
		List<String> listObj = new ArrayList<>();
		listObj.add("COD;Pago contra entrega;PEN;/img/cod.jpg");
		listObj.add("PAYPAL;Paypal USD;USD;/img/paypal.jpg");
		return listObj;
	}
	
	public static String encodingPassword(String passText) {
		SecureRandom random = new SecureRandom();
		char[] password = passText.toCharArray();
	    byte[] salt = new byte[128/8];
	    random.nextBytes(salt);
	    byte[] dk = pbkdf2(password, salt, 1 << 16);
	    byte[] hash = new byte[salt.length + dk.length];
	    System.arraycopy(salt, 0, hash, 0, salt.length);
	    System.arraycopy(dk, 0, hash, salt.length, dk.length);
	    Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
	    return enc.encodeToString(hash);
	}
	
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations){
	    KeySpec spec = new PBEKeySpec(password, salt, iterations, 128);
	    try {
	      SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	      return f.generateSecret(spec).getEncoded();
	    }
	    catch (NoSuchAlgorithmException ex) {
	      throw new IllegalStateException("Missing algorithm: PBKDF2WithHmacSHA1", ex);
	    }
	    catch (InvalidKeySpecException ex) {
	      throw new IllegalStateException("Invalid SecretKeyFactory", ex);
	    }
	}
}
