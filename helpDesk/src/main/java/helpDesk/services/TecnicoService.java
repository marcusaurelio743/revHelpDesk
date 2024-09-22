package helpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpDesk.domain.Tecnico;
import helpDesk.repositories.TecnicoRepository;
import helpDesk.services.exception.ObjectNotFundException;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFundException("Objeto não encontrado !! id: "+id));
	}

	public List<Tecnico> findAll() {
		
		return tecnicoRepository.findAll();
	}
}
