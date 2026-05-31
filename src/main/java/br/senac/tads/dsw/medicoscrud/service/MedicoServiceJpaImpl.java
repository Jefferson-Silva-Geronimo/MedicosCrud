package br.senac.tads.dsw.medicoscrud.service;

import br.senac.tads.dsw.medicoscrud.dto.MedicoDto;
import br.senac.tads.dsw.medicoscrud.entity.Medico;
import br.senac.tads.dsw.medicoscrud.exception.NaoEncontradoException;
import br.senac.tads.dsw.medicoscrud.exception.RegraNegocioException;
import br.senac.tads.dsw.medicoscrud.repository.MedicoRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Responsável por:
 * Implementar a lógica de negócio da entidade Médico e orquestrar a comunicação
 * entre a camada de persistência (Repository) e a camada de apresentação (Controller).
 *
 * Objetivo:
 * Executar operações de CRUD aplicando regras de negócio, validações e controle transacional,
 * garantindo integridade e consistência dos dados.
 *
 * Realiza:
 * - Implementação do contrato definido pela interface MedicoService.
 * - Interação com o MedicoRepository para operações de persistência.
 * - Conversão bidirecional entre Entity (Medico) e DTO (MedicoDto) por meio de métodos utilitários privados.
 * - Aplicação de regras de negócio (ex: validação de unicidade do CRM, impedindo duplicidade no cadastro e na atualização).
 * - Tratamento de cenários de não encontrado com o lançamento de exceções customizadas de tempo de execução.
 * - Controle transacional das operações (@Transactional) para garantir a atomicidade do banco de dados.
 * - Separação entre operações somente leitura (readOnly = true) e escrita para otimização de performance.
 * - Atualização controlada dos atributos da entidade antes da sincronização com o banco.
 * - Registro do componente no contêiner do Spring como a implementação prioritária utilizando @Service e @Primary.
 */

@Primary
@Service
public class MedicoServiceJpaImpl implements MedicoService {

	private final MedicoRepository medicoRepository;

	public MedicoServiceJpaImpl(MedicoRepository medicoRepository){
		this.medicoRepository = medicoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MedicoDto> obterMedicos() {
		List<MedicoDto> resultado = new ArrayList<>();
		for(Medico medico: medicoRepository.findAll()){
			resultado.add(toDto(medico));
		}
		return resultado;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<MedicoDto> obterMedico(Long id) {
		Optional<Medico> optMedico = medicoRepository.findById(id);
		if(optMedico.isEmpty()){
			 return Optional.empty();
		}
		return Optional.of(toDto(optMedico.get()));
	}

	@Override
	@Transactional
	public MedicoDto incluirNovoMedico(MedicoDto dto) {
		if(medicoRepository.existsByCrmIgnoreCase(dto.getCrm())){
			throw new RegraNegocioException("Já existe médico cadastrado com o CRM cadastrado.");
		}
		Medico medico = toEntity(dto);
		Medico salvo = medicoRepository.save(medico);

		return toDto(salvo);
	}

	@Override
	@Transactional
	public MedicoDto alterarMedico(Long id, MedicoDto medicoDto) {
		Optional<Medico> optMedico = medicoRepository.findById(id);
		if(optMedico.isEmpty()){
			throw new NaoEncontradoException("Médico ID " + id + " não encontrado.");
		}
		Optional<Medico> optCrm = medicoRepository.findByCrmIgnoreCase(medicoDto.getCrm());

		if(optCrm.isPresent() && !optCrm.get().getId().equals(id)){
			throw new RegraNegocioException("Já existe outro médico cadastrado com o CRM informado.");
		}

		Medico medico = optMedico.get();
		medico.setNome(medicoDto.getNome());
		medico.setCrm(medicoDto.getCrm());
		medico.setEspecialidade(medicoDto.getEspecialidade());
		medico.setObservacoes(medicoDto.getObservacoes());
		medico.setCadastroAtivo(medicoDto.isCadastroAtivo());

		Medico salvo = medicoRepository.save(medico);
		return toDto(salvo);
	}

	@Override
	@Transactional
	public void removerMedico(Long id) {
		Optional<Medico> optMedico = medicoRepository.findById(id);
		if(optMedico.isEmpty()){
			throw new NaoEncontradoException("Médico ID " + id + " não encontrado.");
		}
		medicoRepository.delete(optMedico.get());
	}

	private MedicoDto toDto(Medico medico){
		MedicoDto dto = new MedicoDto();
		dto.setId(medico.getId());
		dto.setNome(medico.getNome());
		dto.setCrm(medico.getCrm());
		dto.setEspecialidade(medico.getEspecialidade());
		dto.setObservacoes(medico.getObservacoes());
		dto.setCadastroAtivo(medico.getCadastroAtivo());

		return dto;
	}

	private Medico toEntity(MedicoDto dto){
		Medico medico = new Medico();
		medico.setId(dto.getId());
		medico.setNome(dto.getNome());
		medico.setCrm(dto.getCrm());
		medico.setEspecialidade(dto.getEspecialidade());
		medico.setObservacoes(dto.getObservacoes());
		medico.setCadastroAtivo(dto.isCadastroAtivo());

		return medico;
	}
}
