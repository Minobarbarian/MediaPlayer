package br.ufrn.imd.controle.Exceptions;

/**
 * Classe responsavel por identificar Excecao de validacao.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class ValidacaoException extends Exception{
	/**
	 * Metodo responsavel por construir a classe
	 * 
	 * @param s Explicacao da causa da excecao
	 * @param cause Causa da excecao
	 */
	public ValidacaoException(String s, Throwable cause) {
		super(s,cause);
	}
}
