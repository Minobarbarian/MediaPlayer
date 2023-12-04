package br.ufrn.imd.controle.Exceptions;

/**
 * Classe responsavel por identificar Excecao de Senhas diferentes.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class IgualdadeSenhaInvalidaException extends Exception{
	
	/**
	 * Metodo responsavel por construir a classe
	 * 
	 * @param s Explicacao da causa da excecao
	 */
	public IgualdadeSenhaInvalidaException(String s) {
		super(s);
	}
}