package com.jcalzado.demo.service;

import java.util.List;
import java.util.Optional;
import com.jcalzado.demo.model.Rol;

public interface RolService {

	public abstract List<Rol> lsitarrol();
	public Optional<Rol>mostrar(int id);
}
