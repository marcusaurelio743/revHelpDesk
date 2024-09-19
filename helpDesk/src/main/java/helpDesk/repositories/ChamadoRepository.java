package helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import helpDesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
