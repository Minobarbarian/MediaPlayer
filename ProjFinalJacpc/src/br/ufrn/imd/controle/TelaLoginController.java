package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.controle.Exceptions.AutenticacaoException;
import br.ufrn.imd.controle.Exceptions.CampoVazioException;
import br.ufrn.imd.controle.Exceptions.ValidacaoException;
import br.ufrn.imd.dao.UsuarioDAO;
import br.ufrn.imd.dao.UsuarioVipDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Classe responsavel por controlar a tela Login.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class TelaLoginController {

	/**
	 * Registrador do estado do botao Cadastrar
	 * 
	 */
	private boolean btnCadastrarClicked = false;
	
	/**
	 * Registrador do estado do botao Entrar
	 * 
	 */
	private boolean btnEntrarClicked = false;
	
	/**
	 * DAO de Usuarios Comuns
	 * 
	 */
	UsuarioDAO bdUsuario;
	
	/**
	 * DAO de Usuarios Vips
	 * 
	 */
	UsuarioVipDAO bdUsuarioVip;
	
	/**
	 * Botao de abrir a tela CadastrarUsuario
	 * 
	 */
	@FXML
    private Button btnCadastrar;

	/**
	 * Botao de abrir a tela Player
	 * 
	 */
	@FXML
    private Button btnEntrar;

	/**
	 * Label do campo Login
	 * 
	 */
    @FXML
    private Label lbLogin;

    /**
	 * Label do campo Senha
	 * 
	 */
    @FXML
    private Label lbSenha;

    /**
	 * Campo de texto de Senha
	 * 
	 */
    @FXML
    private PasswordField pfSenha;

    /**
	 * Campo de texto de Login
	 * 
	 */
    @FXML
    private TextField tfLogin;

    /**
	 * Metodo responsavel por analisar o estado do botao Cadastrar
	 * 
	 * @return estado do botao Cadastrar
	 */
    public boolean isBtnCadastrarClicked() {
		return btnCadastrarClicked;
	}

    /**
	 * Metodo responsavel por configurar o estado do botao Cadastrar
	 * 
	 * @param btnCadastrarClicked Registrador de estado do botao Cadastrar
	 */
	public void setBtnCadastrarClicked(boolean btnCadastrarClicked) {
		this.btnCadastrarClicked = btnCadastrarClicked;
	}
	
	/**
	 * Metodo responsavel por analisar o estado do botao Entrar
	 * 
	 * @return estado do botao Entrar
	 */
	public boolean isBtnEntrarClicked() {
		return btnEntrarClicked;
	}

	/**
	 * Metodo responsavel por configurar o estado do botao Entrar
	 * 
	 * @param btnEntrarClicked Registrador de estado do botao Entrar
	 */
	public void setBtnEntrarClicked(boolean btnEntrarClicked) {
		this.btnEntrarClicked = btnEntrarClicked;
	}
    
	/**
	 * Metodo responsavel por verificar se os campos sao vazios 
	 * 
	 * @throws CampoVazioException se algum campo esta vazio
	 */
	public void validacaoVazio() throws CampoVazioException{
		if(tfLogin.getText().compareTo("") == 0 ||
		pfSenha.getText().compareTo("") == 0) {
			throw new CampoVazioException("Todos os campos devem ser preenchidos!");
		}
	}
	
	/**
	 * Metodo responsavel por verificar se os campos sao validos 
	 * 
	 * @return inteiro que indica se os campos preenchidos sao validos
	 * 
	 * @throws ValidacaoException se os campos estao invalidos
	 */
	public int validacao() throws ValidacaoException {
		for (int i = 0; i < 1; ++i) System.out.println();
		
		int flag = 1;
		try {
			validacaoVazio();
		} catch(CampoVazioException ex) {
			flag = 0;
			throw new ValidacaoException("Excecao gerada pelo metodo validacaoVazio()", ex);
		}
		return flag;
	}
	
	/**
	 * Metodo responsavel por verificar a autenticidade dos campos
	 * 
	 * @return inteiro que indica a autenticidade dos campos
	 * 
	 * @throws AutenticacaoException se algum dos campos nao eh conhecido
	 */
	public int autenticacao() throws AutenticacaoException {
		bdUsuario = UsuarioDAO.getInstance();
		bdUsuarioVip = UsuarioVipDAO.getInstance();
		
		int aut1 = bdUsuario.isIn(tfLogin.getText(), pfSenha.getText());
		int aut2 = bdUsuarioVip.isIn(tfLogin.getText(), pfSenha.getText());
		// adimite que o usuario eh comum por padrao
		int aut = 1;
		
		
		if(aut1 < 1 && aut2 < 1) {
			aut = 0;
			throw new AutenticacaoException("Login e senha desconhecidos");
		} else if(aut1 == 1 || aut2 == 1) {
			aut = 0;
			throw new AutenticacaoException("Senha desconhecida");
		} else {
			//se estiver no bd dos vips, entao o usuario eh vip
			if (aut2 == 2) aut = 2;
		}
		
		return aut;
	}
	
	/**
	 * Metodo responsavel por abrir a tela CadastroUsuario
	 * 
	 * @param event Evento de interacao
	 */
	@FXML
    void abrirTelaCadastro(ActionEvent event) throws IOException{
    	btnCadastrarClicked = true;
    	
    	if(btnCadastrarClicked) {
    		FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(TelaCadastroUsuarioController.class.getResource("/br/ufrn/imd/visao/TelaCadastroUsuario.fxml"));
        	AnchorPane page = (AnchorPane) loader.load();
        	
        	Stage usuarioStage = new Stage();
        	usuarioStage.setTitle("Cadastro de Usuarios");
        	usuarioStage.setResizable(false);
        	Scene scene = new Scene(page);
        	usuarioStage.setScene(scene);
        	
        	TelaCadastroUsuarioController controller = loader.getController();
        	
        	controller.setUsuarioStage(usuarioStage);
        	controller.setUsuarioVipStage(usuarioStage);
        	usuarioStage.showAndWait();
    	}
    }

	/**
	 * Metodo responsavel por abrir a tela Player
	 * 
	 * @param event Evento de interacao
	 */
	@FXML
    void abrirTelaPrincipal(ActionEvent event) throws IOException{
    	btnEntrarClicked = true;
    	if(btnEntrarClicked) {
    		try {
    			int val = validacao();
    			if(val == 1) {
    				int aut = autenticacao();
    				
    				if(aut > 0) System.out.println(tfLogin.getText()+" logado!!!\n");
    				
    				
    				FXMLLoader loader = new FXMLLoader();
    				loader.setLocation(TelaPlayerController.class.getResource("/br/ufrn/imd/visao/TelaPlayer.fxml"));
    				AnchorPane page = (AnchorPane) loader.load();
    	        	
    				Stage playerStage = new Stage();
    				playerStage.setTitle("Menu Player");
    				playerStage.setResizable(false);
    				Scene scene = new Scene(page);
    				playerStage.setScene(scene);
    	        	
    				TelaPlayerController controller = loader.getController();
    				
    				controller.setMusicasTitle("musicas");
    				controller.setTADir();
    				controller.setTAMusicas(1);
    					
    				controller.escreveDirsTxt();
    				controller.escreveMusicasTxt();
    				
    				if(aut == 1) {
    					bdUsuario = UsuarioDAO.getInstance();
    					controller.setVisibilites();
    					controller.setNome(bdUsuario.getUsuario(tfLogin.getText(), pfSenha.getText()).getNome());
    					controller.setTipoUsuario("Usuário Comum");
    				} else {
    					bdUsuarioVip = UsuarioVipDAO.getInstance();
    					controller.setNome(bdUsuarioVip.getUsuarioVip(tfLogin.getText(), pfSenha.getText()).getNome());
    					controller.setTipoUsuario("Usuário Vip");
    					controller.setLoginVip(tfLogin.getText());
    					controller.setTAPlaylists();
        				controller.escrevePlaylistsTxt();
    				}
    				controller.setPlayerStage(playerStage);
    				playerStage.showAndWait();
    		}
    	} catch(ValidacaoException ex) {
    		ex.printStackTrace();
    		System.out.println("Falha de validacao!");
    	} catch(AutenticacaoException ex) {
    		ex.printStackTrace();
    		System.out.println("Falha de autenticacao!");
    	}
    }
    }

}
