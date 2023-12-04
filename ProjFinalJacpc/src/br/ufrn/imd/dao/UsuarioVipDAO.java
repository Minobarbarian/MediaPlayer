package br.ufrn.imd.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.modelo.UsuarioVip;

/**
 * Classe responsavel por manipular Usuarios Vips.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class UsuarioVipDAO {
	
	/**
	 * A colecao de Usuarios Vips
	 * 
	 */
	private ArrayList<UsuarioVip> usuarios;
	
	/**
	 * Primeira e ultima criacao de um objeto UsuarioVipDAO
	 * 
	 */
	private static UsuarioVipDAO uvDAO;
	
	/**
	 * Metodo responsavel por construir a classe
	 * 
	 */
	public UsuarioVipDAO() {
		usuarios = new ArrayList<UsuarioVip>();
	}
	
	/**
	 * Metodo responsavel pela inicializacao do unico objeto UsuarioVipDAO
	 * 
	 * @return unica instancia de UsuarioVipDAO
	 */
	public static UsuarioVipDAO getInstance() {
		if(uvDAO == null) {
			uvDAO = new UsuarioVipDAO();
		}
		return uvDAO;
	}
	
	/**
	 * Metodo responsavel pela insercao de Usuario Vip na colecao de Usuarios Vip e criacao de diretorio playlist e playlists.txt apropriado
	 *
	 * @param uv Usuario que sera inserido
	 * 
	 * @throws IOException se nao conseguiu ler o arquivo
	 */
	public void addUsuarioVip(UsuarioVip uv) throws IOException{

		File playlist = new File("src/br/ufrn/imd/data/playlists/"+uv.getNome()+uv.getLogin());
		playlist.mkdir();
		File playlistTxt = new File("src/br/ufrn/imd/data/playlists/play_"+uv.getNome()+uv.getLogin()+".txt");
		playlistTxt.createNewFile();
		
		usuarios.add(uv);
		System.out.println("Usuario Vip "+uv.getNome()+" inserido!!!");
	}
	
	/**
	 * Metodo responsavel por realizar a persistencia dos dados de Usuarios Vips
	 *
	 */
	public void registrarUsuariosVip() {
		for(UsuarioVip uv : usuarios) {
			try {
				FileWriter escritor = new FileWriter("src/br/ufrn/imd/data/usuarios.txt", true);
				escritor.write(uv.getNome()+";"+uv.getLogin()+";"+uv.getSenha()+";"+uv.getTipo()+"\n");
				escritor.close();
			} catch(IOException ex) {
				ex.printStackTrace();
	    		System.out.println("Falha em registrar usuario vip!");
			}
			
		}
	}
	
	/**
	 * Metodo responsavel encontrar um Usuario a partir de dados recebidos por parametro
	 *
	 * @param login Login do Usuario Comum procurado
	 * @param senha Senha do Usuario Comum procurado
	 * 
	 * @return Usuario Vip encontrado ou nulo
	 */
	public UsuarioVip getUsuarioVip(String login, String senha) {
		for(UsuarioVip uv : usuarios) {
			if(uv.getLogin().compareTo(login) == 0 && uv.getSenha().compareTo(senha) == 0) {
				return uv;
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
		for(UsuarioVip uv : usuarios) {
			if(uv.getLogin().compareTo(login) == 0) {
				if(uv.getSenha().compareTo(senha) == 0) {
					return 2;
				}
				return 1;
			}
		}
		return 0;
	}
}
