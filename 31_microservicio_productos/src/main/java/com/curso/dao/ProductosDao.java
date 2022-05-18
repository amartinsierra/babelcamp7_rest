package com.curso.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.model.Producto;

public interface ProductosDao extends JpaRepository<Producto, Integer> {
	/*@Query("select p.precio from Producto p where p.codigo=?1")
	double precioProducto(int codigoProducto);
	
	@Query("select avg(p.precio) from Producto p")
	double precioMedio();
	
	@Query("select p.precio, p.stock from Producto p")
	Object[] datos();
	
	@Query(value="select precio, stock from productos",nativeQuery=true)
	Object[] info();*/
}
