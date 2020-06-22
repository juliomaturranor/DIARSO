package com.jcalzado.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jcalzado.demo.model.Pedidoenvios;

@Repository("pedidoenviodao")
public interface Pedidoenviodao extends JpaRepository<Pedidoenvios, Integer>{

}
