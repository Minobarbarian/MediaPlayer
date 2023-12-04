package br.ufrn.imd.controle.Exceptions;

/**
 * Classe responsavel por identificar Excecao de Tipo invalido.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class TipoInvalidoException extends Exception{
	
	/**
	 * Metodo responsavel por construir a classe
	 * 
	 * @param s Explicacao da causa da excecao
	 */
	public TipoInvalidoException(String s) {
		super(s);
	}
}
