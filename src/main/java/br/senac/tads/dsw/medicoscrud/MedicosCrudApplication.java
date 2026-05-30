// hash-identificacao: e4856e6b12c28bc7080d63a8255b3ae2a4334863862c7566ae19e60deaee5bbb
package br.senac.tads.dsw.medicoscrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Responsável por:
 * Inicializar e configurar a aplicação Spring Boot.
 *
 * Objetivo:
 * Servir como ponto de entrada da aplicação, acionando o processo de bootstrap
 * e carregamento do contexto do Spring.
 *
 * Realiza:
 * - Inicialização da aplicação via SpringApplication.run
 * - Ativação da configuração automática do Spring Boot (@SpringBootApplication)
 * - Escaneamento de componentes para injeção de dependência
 * - Carregamento do contexto da aplicação (ApplicationContext)
 */
@SpringBootApplication
public class MedicosCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicosCrudApplication.class, args);
	}

}
