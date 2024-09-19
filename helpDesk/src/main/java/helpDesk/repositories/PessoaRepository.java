package helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import helpDesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
