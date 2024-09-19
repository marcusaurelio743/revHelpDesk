package helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import helpDesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
