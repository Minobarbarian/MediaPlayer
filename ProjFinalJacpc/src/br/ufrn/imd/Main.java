package br.ufrn.imd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.ufrn.imd.dao.UsuarioDAO;
import br.ufrn.imd.dao.UsuarioVipDAO;
import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.modelo.UsuarioVip;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe responsavel por rodar todo o codigo.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class Main extends Application {
	
	/**
	 * Metodo responsavel por iniciar a tela login e ler o que estiver escrito em usuarios.txt
	 * 
	 * @param stage Estagio principal
	 */
	@Override
	public void start(Stage stage) throws Exception{
		UsuarioDAO bdUsuario;
		UsuarioVipDAO bdUsuarioVip;
		try {
			File usuarios = new File("src/br/ufrn/imd/data/usuarios.txt");
			Scanner leitor = new Scanner(usuarios);
			while(leitor.hasNextLine()) {
				String linha = leitor.nextLine();
				String nome;
				String login;
				String senha;
				String tipo;
				int indice;
				
				indice = linha.indexOf(';');
				nome = linha.substring(0, indice);
				linha = linha.substring(indice+1);
				
				indice = linha.indexOf(';');
				login = linha.substring(0, indice);
				linha = linha.substring(indice+1);
				
				indice = linha.indexOf(';');
				senha = linha.substring(0, indice);
				linha = linha.substring(indice+1);
				
				tipo = linha.substring(0);
				
				
				if(tipo.compareTo("1") == 0) {
					bdUsuario = UsuarioDAO.getInstance();
    				Usuario u = new Usuario();
    				u.setNome(nome);
    				u.setTipo(tipo);
    				u.setLogin(login);
    				u.setSenha(senha);
    				bdUsuario.addUsuario(u);
				} else {
					bdUsuarioVip = UsuarioVipDAO.getInstance();
    				UsuarioVip uv = new UsuarioVip();
    				uv.setNome(nome);
    				uv.setTipo(tipo);
    				uv.setLogin(login);
    				uv.setSenha(senha);
    				bdUsuarioVip.addUsuarioVip(uv);
				}
			}
			leitor.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo nao encontrado!");
		}
		
		Parent root = FXMLLoader.load(getClass().getResource("visao/TelaLogin.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.setResizable(false);
		stage.show();
	}
	
	/**
	 * Metodo responsavel por compilar e executar o código
	 * 
	 * @param args Lista de strings
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
