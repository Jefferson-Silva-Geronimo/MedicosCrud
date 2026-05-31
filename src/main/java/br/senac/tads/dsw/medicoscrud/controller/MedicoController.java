package br.senac.tads.dsw.medicoscrud.controller;

import br.senac.tads.dsw.medicoscrud.dto.MedicoDto;
import br.senac.tads.dsw.medicoscrud.exception.NaoEncontradoException;
import br.senac.tads.dsw.medicoscrud.exception.RegraNegocioException;
import br.senac.tads.dsw.medicoscrud.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


/**
 * Responsável por:
 * Expor os endpoints REST para gerenciamento de médicos e intermediar a comunicação
 * entre cliente e camada de serviço.
 *
 * Objetivo:
 * Centralizar o tratamento das requisições HTTP (GET, POST, PUT, DELETE) relacionadas
 * à entidade Médico, garantindo validação de entrada e padronização das respostas.
 *
 * Realiza:
 * - Recebimento e roteamento de requisições HTTP para a camada de serviço (MedicoService)
 * - Conversão de dados via DTO (MedicoDto)
 * - Validação de dados de entrada utilizando Bean Validation (@Valid)
 * - Construção de respostas HTTP adequadas (ResponseEntity, status codes e headers)
 * - Geração de URI para recursos criados (Location header)
 * - Tratamento de exceções específicas (Não encontrado e regras de negócio)
 * - Padronização de erros utilizando ProblemDetail e retorno de mensagens de validação
 * - Atua como a camada de Controladora dentro do ecossistema Spring MVC/REST
 */


@RestController
@RequestMapping("/medicos")
public class MedicoController {
	private final MedicoService medicoService;

	public MedicoController(MedicoService medicoService){
		this.medicoService = medicoService;
	}

	@GetMapping
	public List<MedicoDto> obterMedicos(){
		return medicoService.obterMedicos();
	}

	@GetMapping("/{id}")
	public MedicoDto obterMedico(@PathVariable("id") Long id){
		Optional<MedicoDto> optMedico = medicoService.obterMedico(id);
		if(optMedico.isEmpty()){
			throw new NaoEncontradoException("Médico ID " + id + " não encontrado.");
		}
		return optMedico.get();
	}

	@PostMapping
	public ResponseEntity<?> incluirNovo(@RequestBody @Valid MedicoDto medico){
		MedicoDto medicoSalvo = medicoService.incluirNovoMedico(medico);

		URI location = ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path("/medicos/{id}")
			.buildAndExpand(medicoSalvo.getId())
			.toUri();

		return ResponseEntity.created(location).body(medicoSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable("id") Long id,
									 @RequestBody @Valid MedicoDto medico){
		MedicoDto medicoAlterado = medicoService.alterarMedico(id, medico);
		return ResponseEntity.ok(medicoAlterado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id){
		medicoService.removerMedico(id);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<ProblemDetail> tratarNaoEncontrado(NaoEncontradoException ex){
		ProblemDetail pd = ProblemDetail.forStatusAndDetail(
			HttpStatusCode.valueOf(404),
			ex.getMessage()
		);

		return ResponseEntity.of(pd).build();
	}

	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<ProblemDetail> tratarRegraNegocio(RegraNegocioException ex){
		ProblemDetail pd = ProblemDetail.forStatusAndDetail(
			HttpStatusCode.valueOf(400),
			ex.getMessage()
		);

		return ResponseEntity.of(pd).build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> tratarErroValidacao(MethodArgumentNotValidException ex){
		return ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(FieldError::getDefaultMessage)
			.toList();
	}



}
