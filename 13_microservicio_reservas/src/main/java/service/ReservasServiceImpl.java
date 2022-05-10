package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.ReservasDao;
import model.Reserva;

@Service
public class ReservasServiceImpl implements ReservasService {
	@Autowired
	ReservasDao reservasDao;
	
	@Autowired
	RestTemplate template;
	
	String urlVuelos="http://localhost:8001/";

	@Override
	public void reservar(Reserva reserva, int plazas) {
		//template.put(urlVuelos+"Vuelo/{id}/{plazas}", null,reserva.getVuelo(),plazas);
		ResponseEntity<String> response=template.exchange(urlVuelos+"Vuelo/{id}/{plazas}", 
															HttpMethod.PUT,
															null, //new HttpEntity(dato_cuerpo)
															String.class,
															reserva.getVuelo(),
															plazas);
		//solo guardamos la reserva si se ha actualizado el número de plazas de los vuelos
		String cuerpo=response.getBody();
		if(cuerpo.equals("true")) {
			reservasDao.save(reserva);
		}
		
	}

	@Override
	public List<Reserva> reservas() {
		return reservasDao.findAll();
	}

}
