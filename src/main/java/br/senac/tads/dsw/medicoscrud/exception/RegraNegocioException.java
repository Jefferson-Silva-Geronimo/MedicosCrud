package br.senac.tads.dsw.medicoscrud.exception;


/**
 * Responsável por:
 * Representar uma exceção específica para violações de regras de negócio.
 *
 * Objetivo:
 * Sinalizar inconsistências ou invalidações relacionadas à lógica da aplicação,
 * garantindo que regras definidas sejam respeitadas durante as operações.
 *
 * Realiza:
 * - Extensão de RuntimeException para tratamento de exceções não verificadas
 * - Encapsulamento de mensagens descritivas sobre a violação
 * - Integração com a camada de controle para retorno adequado ao cliente (HTTP 400)
 * - Diferenciação semântica entre erro de regra de negócio e outros tipos de falha
 */


public class RegraNegocioException extends RuntimeException{
	public RegraNegocioException(String message){
		super(message);
	}
}
