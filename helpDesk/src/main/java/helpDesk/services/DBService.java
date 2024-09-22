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
		Tecnico t2 = new Tecnico(null, "Maria dos Santos", "07253648072", "Maria@email.com", "123");
		t1.addPerfil(Perfil.Tecnico);
		Tecnico t3 = new Tecnico(null, "Karem Amanda", "53260129014", "karem@email.com", "123");
		t1.addPerfil(Perfil.Tecnico);
		Tecnico t4 = new Tecnico(null, "Jose da Silva", "38047769065", "Jose@email.com", "123");
		t1.addPerfil(Perfil.Admin);
		
		
		Cliente c1 = new Cliente(null, "Josepe Novo", "57279112064", "josepe@email.com", "123");
		Cliente c2 = new Cliente(null, "Fernando", "42595924087", "fernando@email.com", "123");
		Cliente c3 = new Cliente(null, "Maria", "03534217071", "maria@email.com", "123");
		Cliente c4 = new Cliente(null, "Helena", "99676774065", "helena@email.com", "123");
		
		Chamado ch1 = new Chamado(null, Prioridade.Media, Status.Andamento, "Chamado 01", "Primeiro chamado", t1, c1);
		Chamado ch2 = new Chamado(null, Prioridade.Baixa, Status.Aberto, "Chamado 02", "Segundo chamado", t1, c1);
		Chamado ch3 = new Chamado(null, Prioridade.Alta, Status.Aberto, "Chamado 03", "Terceiro chamado", t1, c1);
		Chamado ch4 = new Chamado(null, Prioridade.Media, Status.Encerrado, "Chamado 04", "quarto chamado", t1, c1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1,t2,t3,t4));
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		chamadoRepository.saveAll(Arrays.asList(ch1,ch2,ch3,ch4));
	}
}
