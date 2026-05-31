package br.senac.tads.dsw.medicoscrud.service;

import br.senac.tads.dsw.medicoscrud.dto.MedicoDto;

import java.util.List;
import java.util.Optional;

/**
 * Responsável por:
 * Definir o contrato da camada de serviço para operações relacionadas à entidade Médico.
 *
 * Objetivo:
 * Abstrair a lógica de negócio da aplicação, estabelecendo os métodos que devem ser
 * implementados para manipulação dos dados, sem expor detalhes de implementação.
 *
 * Realiza:
 * - Declaração das operações de negócio (CRUD) para Médicos
 * - Definição do fluxo de dados utilizando DTO (MedicoDto)
 * - Uso de Optional para representar possíveis ausências de resultado
 * - Desacoplamento entre Controller e implementação da lógica de negócio
 * - Padronização das assinaturas dos métodos da camada de serviço
 * - Facilita a inversão de controle (IoC) e a criação de mocks para testes unitários
 */

public interface MedicoService {
	List<MedicoDto> obterMedicos();
	Optional<MedicoDto> obterMedico(Long id);
	MedicoDto incluirNovoMedico(MedicoDto medico);
	MedicoDto alterarMedico(Long id, MedicoDto medicoDto);
	void removerMedico(Long id);
}

