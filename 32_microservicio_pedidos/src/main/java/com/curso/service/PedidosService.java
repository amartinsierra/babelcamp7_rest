package com.curso.service;

import java.util.List;

import com.curso.model.Pedido;

public interface PedidosService {
	List<Pedido> pedidos();
	boolean altaPedido(Pedido pedido);
}
