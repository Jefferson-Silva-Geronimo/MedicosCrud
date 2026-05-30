package br.senac.tads.dsw.medicoscrud.entity;

import jakarta.persistence.*;

/**
 * Responsável por:
 * Representar a entidade de domínio Médico e mapear sua persistência no banco de dados.
 *
 * Objetivo:
 * Definir a estrutura da tabela "tb_medicos" e estabelecer o mapeamento objeto-relacional (ORM)
 * utilizando JPA, garantindo integridade e consistência dos dados armazenados.
 *
 * Realiza:
 * - Mapeamento da classe para a tabela do banco de dados (@Entity, @Table)
 * - Definição da chave primária com geração automática (IDENTITY)
 * - Configuração de restrições de persistência (nullable, unique, length)
 * - Representação dos atributos persistidos (nome, CRM, especialidade, etc.)
 * - Controle de unicidade do campo CRM
 * - Encapsulamento dos dados por meio de getters e setters
 */


@Entity
@Table(name = "tb_medicos")
public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = false, unique = true, length = 20)
	private String crm;

	@Column(nullable = false, length = 100)
	private String especialidade;

	@Column(length = 500)
	private String observacoes;

	@Column(name = "cadastro_ativo", nullable = false)
	private Boolean cadastroAtivo;

	public Medico() {
	}

	public Medico(Long id,
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

	public Boolean getCadastroAtivo() {
		return cadastroAtivo;
	}

	public void setCadastroAtivo(Boolean cadastroAtivo) {
		this.cadastroAtivo = cadastroAtivo;
	}
}
