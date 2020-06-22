package com.jcalzado.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jcalzado.demo.model.Pedido;

@Repository("pedidodao")
public interface Pedidodao extends JpaRepository<Pedido, Integer>{

}
