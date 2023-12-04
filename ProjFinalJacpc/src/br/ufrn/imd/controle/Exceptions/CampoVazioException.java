package br.ufrn.imd.controle.Exceptions;

/**
 * Classe responsavel por identificar Excecao de Campo Vazio.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class CampoVazioException extends Exception{
	
	/**
	 * Metodo responsavel por construir a classe
	 * 
	 * @param s Explicacao da causa da excecao
	 */
	public CampoVazioException(String s) {
		super(s);
	}
}
