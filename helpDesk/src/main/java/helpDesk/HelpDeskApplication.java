package helpDesk;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import helpDesk.domain.Chamado;
import helpDesk.domain.Cliente;
import helpDesk.domain.Tecnico;
import helpDesk.domain.enun.Perfil;
import helpDesk.domain.enun.Prioridade;
import helpDesk.domain.enun.Status;
import helpDesk.repositories.ChamadoRepository;
import helpDesk.repositories.ClienteRepository;
import helpDesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpDeskApplication implements CommandLineRunner {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico t1 = new Tecnico(null, "Marcus Aurelio", "66118108030", "Marcus@email.com", "123");
		t1.addPerfil(Perfil.Admin);
		
		Cliente c1 = new Cliente(null, "Josepe Novo", "57279112064", "josepe@email.com", "123");
		
		Chamado ch1 = new Chamado(null, Prioridade.Media, Status.Andamento, "Chamado 01", "Primeiro chamado", t1, c1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
		
	}

}
