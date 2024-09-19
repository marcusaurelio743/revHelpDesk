package helpDesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpDesk.domain.Chamado;
import helpDesk.domain.Cliente;
import helpDesk.domain.Tecnico;
import helpDesk.domain.enun.Perfil;
import helpDesk.domain.enun.Prioridade;
import helpDesk.domain.enun.Status;
import helpDesk.repositories.ChamadoRepository;
import helpDesk.repositories.ClienteRepository;
import helpDesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Marcus Aurelio", "66118108030", "Marcus@email.com", "123");
		t1.addPerfil(Perfil.Admin);
		
		Cliente c1 = new Cliente(null, "Josepe Novo", "57279112064", "josepe@email.com", "123");
		
		Chamado ch1 = new Chamado(null, Prioridade.Media, Status.Andamento, "Chamado 01", "Primeiro chamado", t1, c1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
	}
}
