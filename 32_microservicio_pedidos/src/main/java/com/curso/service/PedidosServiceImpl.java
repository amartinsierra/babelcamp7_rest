package com.curso.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.curso.dao.PedidosDao;
import com.curso.model.Pedido;
@Service
public class PedidosServiceImpl implements PedidosService {
	@Autowired
	PedidosDao pedidosDao;
	@Autowired
	RestTemplate restTemplate;
	String url="http://servicio-productos/";
	@Override
	public List<Pedido> pedidos() {
		return pedidosDao.findAll();
	}

	@Override
	public boolean altaPedido(Pedido pedido) {
		try {
			actualizarStock(pedido.getCodigoProducto(),pedido.getUnidades());
			pedido.setFechaPedido(new Date());
			pedido.setTotal(pedido.getUnidades()*obtenerPrecio(pedido.getCodigoProducto()));
			pedidosDao.save(pedido);
			return true;
		}
		catch(HttpClientErrorException ex){
			return false;
		}
		
	}
	
	private double obtenerPrecio(int codigoProducto) {
		return Double.parseDouble(restTemplate.getForObject(url+"precio/{codigo}", String.class,codigoProducto));
	}
	private void actualizarStock(int codigoProducto, int unidades) {
		
		restTemplate.put(url+"producto/{codigo}/{unidades}", null, codigoProducto,unidades);
		
	}
}
