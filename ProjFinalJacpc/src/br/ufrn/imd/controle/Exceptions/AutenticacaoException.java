package br.ufrn.imd.controle.Exceptions;

/**
 * Classe responsavel por identificar Excecao de Autenticacao.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class AutenticacaoException extends Exception{
	
	/**
	 * Metodo responsavel por construir a classe
	 * 
	 * @param s Explicacao da causa da excecao
	 */
	public AutenticacaoException(String s) {
		super(s);
	}
}