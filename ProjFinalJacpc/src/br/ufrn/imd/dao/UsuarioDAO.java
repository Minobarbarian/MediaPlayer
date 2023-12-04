package br.ufrn.imd.dao;

import br.ufrn.imd.modelo.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe responsavel por manipular Usuarios Comuns.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class UsuarioDAO {
	
	/**
	 * A colecao de Usuarios Comuns
	 * 
	 */
	private ArrayList<Usuario> usuarios;
	
	/**
	 * Primeira e ultima criacao de um objeto UsuarioDAO
	 * 
	 */
	private static UsuarioDAO uDAO;
	
	/**
	 * Metodo responsavel por construir a classe
	 * 
	 */
	public UsuarioDAO() {
		usuarios = new ArrayList<Usuario>();
	}
	
	/**
	 * Metodo responsavel pela inicializacao do unico objeto UsuarioDAO
	 * 
	 * @return unica instancia de UsuarioDAO
	 */
	public static UsuarioDAO getInstance() {
		if(uDAO == null) {
			uDAO = new UsuarioDAO();
		}
		return uDAO;
	}
	
	/**
	 * Metodo responsavel pela insercao de Usuario Comum na colecao de Usuarios Comuns
	 *
	 * @param u Usuario que sera inserido
	 */
	public void addUsuario(Usuario u) {
		usuarios.add(u);
		System.out.println("Usuario comum "+u.getNome()+" inserido!!!");
	}
	
	/**
	 * Metodo responsavel por realizar a persistencia dos dados de Usuarios Comuns
	 *
	 */
	public void registrarUsuarios() {
		for(Usuario u : usuarios) {
			try {
				FileWriter escritor = new FileWriter("src/br/ufrn/imd/data/usuarios.txt", true);
				escritor.write(u.getNome()+";"+u.getLogin()+";"+u.getSenha()+";"+u.getTipo()+"\n");
				escritor.close();
			} catch(IOException ex) {
				ex.printStackTrace();
	    		System.out.println("Falha em registrar usuario comum!");
			}
			
		}
	}
	
	/**
	 * Metodo responsavel por encontrar um Usuario a partir de dados recebidos por parametro
	 *
	 * @param login Login do Usuario Comum procurado
	 * @param senha Senha do Usuario Comum procurado
	 * 
	 * @return Usuario Comum encontrado ou nulo
	 */
	public Usuario getUsuario(String login, String senha) {
		for(Usuario u : usuarios) {
			if(u.getLogin().compareTo(login) == 0 && u.getSenha().compareTo(senha) == 0) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * Metodo responsavel por verificar se um Usuario esta na colecao a partir de dados recebidos por parametro
	 *
	 * @param login Login do Usuario Comum procurado
	 * @param senha Senha do Usuario Comum procurado
	 * 
	 * @return Identificador de resultado da busca
	 */
	public int isIn(String login, String senha) {
		for(Usuario u : usuarios) {
			if(u.getLogin().compareTo(login) == 0) {
				if(u.getSenha().compareTo(senha) == 0) {
					return 2;
				}
				return 1;
			}
		}
		return 0;
	}
}
