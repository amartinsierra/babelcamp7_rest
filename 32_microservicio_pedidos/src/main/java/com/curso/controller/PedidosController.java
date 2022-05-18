package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Pedido;
import com.curso.service.PedidosService;
@CrossOrigin("*")
@RestController
public class PedidosController {
	@Autowired
	PedidosService pedidosService;
	@GetMapping(value="pedidos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> pedidos(){
		return new ResponseEntity<>(pedidosService.pedidos(),HttpStatus.OK);
	}
	@PostMapping(value="pedido",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> altaPedido(@RequestBody Pedido pedido) {
		if(pedidosService.altaPedido(pedido)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
}
