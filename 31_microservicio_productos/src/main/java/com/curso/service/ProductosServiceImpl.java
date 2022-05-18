package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.dao.ProductosDao;
import com.curso.model.Producto;
@Service
public class ProductosServiceImpl implements ProductosService {
	@Autowired
	ProductosDao productosDao;

	@Override
	public List<Producto> productos() {
		return productosDao.findAll();
	}

	@Override
	public double precioProducto(int codigoProducto) {
		Producto producto=productosDao.findById(codigoProducto).orElse(null);
		return producto!=null?producto.getPrecioUnitario():0;
	}

	@Override
	public boolean actualizarStock(int codigoProducto, int unidades) {
		Producto producto=productosDao.findById(codigoProducto).orElse(null);
		if(producto!=null&&producto.getStock()>unidades) {
			producto.setStock(producto.getStock()-unidades);
			productosDao.save(producto);
			return true;
		}
		return false;

	}

}
