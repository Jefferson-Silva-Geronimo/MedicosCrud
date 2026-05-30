package br.senac.tads.dsw.medicoscrud.exception;


/**
 * Responsável por:
 * Representar uma exceção específica para cenários onde um recurso não é encontrado.
 *
 * Objetivo:
 * Sinalizar, de forma explícita, a ausência de dados durante a execução das operações,
 * permitindo o tratamento adequado na camada de controle.
 *
 * Realiza:
 * - Extensão de RuntimeException para exceções não verificadas
 * - Encapsulamento de mensagem descritiva do erro
 * - Integração com mecanismos de tratamento global de exceções (ExceptionHandler)
 * - Diferenciação semântica de erros de "não encontrado" na aplicação
 */


public class NaoEncontradoException extends RuntimeException{
	public NaoEncontradoException(String message){
		super(message);
	}
}
