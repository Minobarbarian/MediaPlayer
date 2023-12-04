package br.ufrn.imd.modelo;

/**
 * Classe responsavel por definir um Usuario Comum.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class Usuario {
	
	/**
	 * Nome de Usuario
	 * 
	 */
	protected String nome;
	
	/**
	 * Login de Usuario
	 * 
	 */
	protected String login;
	
	/**
	 * Senha de Usuario
	 * 
	 */
	protected String senha;
	
	/**
	 * Tipo de Usuario
	 * 
	 */
	protected String tipo;
	
	/**
	 * Metodo responsavel por buscar o Nome do Usuario
	 * 
	 * @return Nome do Usuario
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Metodo responsavel por buscar o Login do Usuario
	 * 
	 * @return Login do Usuario
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Metodo responsavel por buscar a Senha do Usuario
	 * 
	 * @return Senha do Usuario
	 */
	public String getSenha() {
		return senha;
	}
	
	/**
	 * Metodo responsavel por buscar o Tipo do Usuario
	 * 
	 * @return Tipo do Usuario
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Metodo responsavel por registrar o Nome do Usuario
	 * 
	 * @param nome Nome do Usuario
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Metodo responsavel por registrar o Login do Usuario
	 * 
	 * @param login Login do Usuario
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Metodo responsavel por registrar a Senha do Usuario
	 * 
	 * @param senha Senha do Usuario
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
	 * Metodo responsavel por registrar o Tipo do Usuario
	 * 
	 * @param tipo Tipo do Usuario
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
