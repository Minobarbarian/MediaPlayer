package br.ufrn.imd.controle.Exceptions;

/**
 * Classe responsavel por identificar Excecao de Senhas de tamanho invalido.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class TamanhoSenhaInvalidaException extends Exception{
	
	/**
	 * Metodo responsavel por construir a classe
	 * 
	 * @param s Explicacao da causa da excecao
	 */
	public TamanhoSenhaInvalidaException(String s) {
		super(s);
	}
}
