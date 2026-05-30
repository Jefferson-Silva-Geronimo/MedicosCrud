package br.senac.tads.dsw.medicoscrud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MedicoDto {
	private Long id;
	@NotBlank(message = "Nome é obrigatório.")
	@Size(min = 5, max = 100, message = "Nome deve ter entre 5 a 100 caracteres.")
	private String nome;

	@NotBlank(message = "CRM é obrigatório.")
	@Size(max = 20, message = "CRM deve ter no máximo 20 caracteres.")
	private String crm;

	@NotBlank(message = "Especialidade é obrigatório.")
	@Size(min = 5, max = 100, message = "Especialidade deve ter entre 5 a 100 caracteres.")
	private String especialidade;

	@Size(max = 500, message = "observações devem ter no máximo 500 caracteres.")
	private String observacoes;

	@NotNull(message = "Informe se o cadastro está ativado.")
	private Boolean cadastroAtivo;

	public MedicoDto() {
	}

	public MedicoDto(Long id,
					 String nome,
					 String crm,
					 String especialidade,
					 String observacoes,
					 Boolean cadastroAtivo) {
		this.id = id;
		this.nome = nome;
		this.crm = crm;
		this.especialidade = especialidade;
		this.observacoes = observacoes;
		this.cadastroAtivo = cadastroAtivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Boolean isCadastroAtivo() {
		return cadastroAtivo;
	}

	public void setCadastroAtivo(Boolean cadastroAtivo) {
		this.cadastroAtivo = cadastroAtivo;
	}
}
