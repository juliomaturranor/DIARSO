package com.jcalzado.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jcalzado.demo.model.Pedidoitems;

@Repository("pedidoitemdao")
public interface Pedidoitemdao extends JpaRepository<Pedidoitems, Integer>{

}
