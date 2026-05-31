package br.senac.tads.dsw.medicoscrud.entity;

import jakarta.persistence.*;

/**
 * Responsável por:
 * Representar a entidade de domínio Médico e mapear sua persistência no banco de dados.
 *
 * Objetivo:
 * Definir a estrutura da tabela "tb_medicos" e estabelecer o mapeamento objeto-relacional (ORM)
 * utilizando JPA, garantindo a integridade, consistência e o correto armazenamento das regras relacionais.
 *
 * Realiza:
 * - Mapeamento completo da classe para a tabela do banco de dados utilizando as anotações @Entity e @Table.
 * - Definição da chave primária (id) com estratégia de autoincremento via banco de dados (GenerationType.IDENTITY).
 * - Configuração de restrições rígidas de persistência nas colunas, como limites de tamanho (length) e obrigatoriedade (nullable = false).
 * - Controle de unicidade a nível de banco de dados para o campo CRM (unique = true), impedindo registros duplicados.
 * - Padronização e mapeamento explícito do atributo booleano "cadastroAtivo" para a coluna "cadastro_ativo" em formato snake_case.
 * - Disponibilização de um construtor padrão (sem argumentos) exigido pela especificação JPA para a instanciação via reflexão.
 * - Encapsulamento seguro dos dados persistidos por meio de métodos seletores (getters) e modificadores (setters).
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
