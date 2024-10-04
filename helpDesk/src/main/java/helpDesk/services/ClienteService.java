package helpDesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpDesk.domain.Cliente;
import helpDesk.domain.Pessoa;
import helpDesk.domain.dtos.ClienteDTO;
import helpDesk.repositories.ClienteRepository;
import helpDesk.repositories.PessoaRepository;
import helpDesk.services.exception.DataIntegrityViolationException;
import helpDesk.services.exception.ObjectNotFundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFundException("Objeto não encontrado !! id: "+id));
	}

	public List<Cliente> findAll() {
		
		return clienteRepository.findAll();
	}

	public Cliente created(ClienteDTO objDto) {
		
		objDto.setId(null);
		
		validaPorCpfeEmail(objDto);
		
		Cliente novoCliente = new Cliente(objDto); 
		
		return clienteRepository.save(novoCliente);
		
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDto) {
		//atribuir o valor de id vindo da url para o objeto evitando falhas de segurança
		objDto.setId(id);
		
		Cliente oldObj = findById(id);
		validaPorCpfeEmail(objDto);
		oldObj = new Cliente(objDto);
		
		return clienteRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O tecnico Possui ordens de serviço e não pode ser Deletado!!");
		}else {
			clienteRepository.delete(obj);
		}
		
	}

	private void validaPorCpfeEmail(ClienteDTO objDto) {
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
