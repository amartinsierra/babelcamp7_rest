package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import model.Hotel;
import service.HotelesService;

@CrossOrigin("*")
@RestController
public class HotelesController {
	@Autowired
	HotelesService hotelesService;
	//@Value("#{spring.application.name}")
	String data;
	@ApiOperation(value="Devuelve una lista con los datos de los hoteles disponibles")
    @GetMapping(value="Hoteles",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> hoteleDisponible(){
    	
		return hotelesService.hotelesDisponibles();
	}
    @ApiOperation(value="Devuelve los datos del hotel cuyo nombre se recibe en URL")
    @GetMapping(value="Hotel/{nombre}",produces=MediaType.APPLICATION_JSON_VALUE)
   	public Hotel hotelNombre(@ApiParam(value="Nombre del hotel a buscar")
   							 @PathVariable("nombre") String nombre){
   		return hotelesService.hotel(nombre);
   	}
	
}

