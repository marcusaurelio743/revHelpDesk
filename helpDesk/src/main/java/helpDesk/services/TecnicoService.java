package helpDesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpDesk.domain.Pessoa;
import helpDesk.domain.Tecnico;
import helpDesk.domain.dtos.TecnicoDTO;
import helpDesk.repositories.PessoaRepository;
import helpDesk.repositories.TecnicoRepository;
import helpDesk.services.exception.DataIntegrityViolationException;
import helpDesk.services.exception.ObjectNotFundException;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFundException("Objeto não encontrado !! id: "+id));
	}

	public List<Tecnico> findAll() {
		
		return tecnicoRepository.findAll();
	}

	public Tecnico created(TecnicoDTO objDto) {
		
		objDto.setId(null);
		
		validaPorCpfeEmail(objDto);
		
		Tecnico novoTecnico = new Tecnico(objDto);
		
		return tecnicoRepository.save(novoTecnico);
		
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDto) {
		//atribuir o valor de id vindo da url para o objeto evitando falhas de segurança
		objDto.setId(id);
		
		Tecnico oldObj = findById(id);
		validaPorCpfeEmail(objDto);
		oldObj = new Tecnico(objDto);
		
		return tecnicoRepository.save(oldObj);
	}

	private void validaPorCpfeEmail(TecnicoDTO objDto) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
		
		if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("O CPF já Existe no Sistema!!");
		}
		
		obj = pessoaRepository.findByEmail(objDto.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("O E-Mail já Existe no Sistema!!");
		}
	}

	

	
}
