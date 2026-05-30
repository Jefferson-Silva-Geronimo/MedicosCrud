package br.senac.tads.dsw.medicoscrud.repository;

import br.senac.tads.dsw.medicoscrud.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Optional<Medico> findByCrmIgnoreCase(String crm);
	boolean existsByCrmIgnoreCase(String crm);
}
