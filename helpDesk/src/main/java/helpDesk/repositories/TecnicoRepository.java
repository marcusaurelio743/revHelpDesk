package helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import helpDesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
