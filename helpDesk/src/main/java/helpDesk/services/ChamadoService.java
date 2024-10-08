package helpDesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpDesk.domain.Chamado;
import helpDesk.domain.Cliente;
import helpDesk.domain.Tecnico;
import helpDesk.domain.dtos.ChamadoDTO;
import helpDesk.domain.enun.Prioridade;
import helpDesk.domain.enun.Status;
import helpDesk.repositories.ChamadoRepository;
import helpDesk.services.exception.ObjectNotFundException;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFundException("Objeto Não encontrado!!! ID: "+id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado created(@Valid ChamadoDTO chamadoDTO) {
		
		return chamadoRepository.save(newChamado(chamadoDTO));
	}
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
	}

	public Chamado update(Integer id, ChamadoDTO chamadoDTO) {
		chamadoDTO.setId(id);
		Chamado chamado = newChamado(chamadoDTO);
		return chamadoRepository.save(chamado);
	}
}
