package br.senac.tads.dsw.medicoscrud.repository;

import br.senac.tads.dsw.medicoscrud.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Responsável por:
 * Realizar operações de acesso a dados (persistência) da entidade Medico.
 *
 * Objetivo:
 * Abstrair a comunicação com o banco de dados, delegando ao Spring Data JPA
 * a implementação das operações CRUD e consultas específicas.
 *
 * Realiza:
 * - Extensão de JpaRepository para disponibilizar operações CRUD automaticamente
 * - Gerenciamento da entidade Medico com chave primária do tipo Long
 * - Declaração de consultas derivadas por nome de método
 * - Busca de médico pelo CRM ignorando diferença entre maiúsculas e minúsculas
 * - Verificação de existência de CRM para validações de unicidade
 */

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Optional<Medico> findByCrmIgnoreCase(String crm);
	boolean existsByCrmIgnoreCase(String crm);
}
