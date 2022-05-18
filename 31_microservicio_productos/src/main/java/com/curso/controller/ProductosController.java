package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Producto;
import com.curso.service.ProductosService;
@CrossOrigin("*")
@RestController
public class ProductosController {
	@Autowired
	ProductosService productosService;
	@GetMapping(value="productos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> productos(){
		return productosService.productos();
	}
	@PutMapping(value="producto/{codigo}/{unidades}")
	public ResponseEntity<Void> actualizarStock(@PathVariable("codigo") int codigo,@PathVariable("unidades") int unidades) {
		if(productosService.actualizarStock(codigo, unidades)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.PRECONDITION_FAILED);
	}
	@GetMapping(value="precio/{codigo}",produces=MediaType.TEXT_PLAIN_VALUE)
	public String precioProducto(@PathVariable("codigo") int codigo) {
		return String.valueOf(productosService.precioProducto(codigo));
	}
}
