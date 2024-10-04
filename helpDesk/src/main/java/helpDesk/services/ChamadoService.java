package helpDesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpDesk.domain.Chamado;
import helpDesk.repositories.ChamadoRepository;
import helpDesk.services.exception.ObjectNotFundException;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFundException("Objeto Não encontrado!!! ID: "+id));
	}
}
