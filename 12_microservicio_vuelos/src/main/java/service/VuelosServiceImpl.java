package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.VuelosDao;
import model.Vuelo;
@Service
public class VuelosServiceImpl implements VuelosService {
	@Autowired
	VuelosDao vuelosDao;
	@Override
	public List<Vuelo> recuperarVuelosDisponibles(int plazas) {
		return vuelosDao.findDisponibles(plazas);
	}

	@Override
	public boolean actualizarPlazas(int id, int plazas) {
		Optional<Vuelo> vuelo = vuelosDao.findById(id);
		if(vuelo.isPresent()&&vuelo.get().getPlazas()>=plazas) {
			Vuelo v=vuelo.get();
			v.setPlazas(v.getPlazas()-plazas);
			vuelosDao.save(v);
			return true;
		}
		return false;

	}

}
