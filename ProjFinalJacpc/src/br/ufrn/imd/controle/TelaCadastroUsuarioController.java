package br.ufrn.imd.controle;

import java.io.FileWriter;
import java.io.IOException;

import br.ufrn.imd.controle.Exceptions.CampoVazioException;
import br.ufrn.imd.controle.Exceptions.IgualdadeSenhaInvalidaException;
import br.ufrn.imd.controle.Exceptions.TamanhoSenhaInvalidaException;
import br.ufrn.imd.controle.Exceptions.TipoInvalidoException;
import br.ufrn.imd.controle.Exceptions.ValidacaoException;
import br.ufrn.imd.dao.UsuarioDAO;
import br.ufrn.imd.dao.UsuarioVipDAO;
import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.modelo.UsuarioVip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Classe responsavel por controlar a tela CadastroUsuario.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class TelaCadastroUsuarioController {

	/**
	 * Um estagio da tela CadastroUsuario para Usuarios Comuns
	 * 
	 */
	private Stage UsuarioStage;
	
	/**
	 * Um estagio da tela CadastroUsuario para Usuarios Vips
	 * 
	 */
	private Stage UsuarioVipStage;
	
	/**
	 * Registrador do estado do botao Confirmar
	 * 
	 */
	private boolean btnConfirmarClicked = false;
	
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
	 * Botao de sair da tela
	 * 
	 */
    @FXML
    private Button btnCancelar;

    /**
	 * Botao de registrar novo Usuario
	 * 
	 */
    @FXML
    private Button btnConfirmar;
    
    /**
	 * Label do campo Confirmar Senha
	 * 
	 */
    @FXML
    private Label lbConfSenha;

    /**
	 * Label do campo Login
	 * 
	 */
    @FXML
    private Label lbLogin;

    /**
	 * Label do campo Nome
	 * 
	 */
    @FXML
    private Label lbNome;

    /**
	 * Label do campo Senha
	 * 
	 */
    @FXML
    private Label lbSenha;

    /**
	 * Label do campo Tipo
	 * 
	 */
    @FXML
    private Label lbTipo;

    /**
	 * Campo de texto de Login
	 * 
	 */
    @FXML
    private TextField tfLogin;

    /**
	 * Campo de texto de Nome
	 * 
	 */
    @FXML
    private TextField tfNome;

    /**
	 * Campo de texto de Confirmar Senha
	 * 
	 */
    @FXML
    private PasswordField pfConfSenha;

    /**
	 * Campo de texto de Senha
	 * 
	 */
    @FXML
    private PasswordField pfSenha;
    
    /**
	 * Campo de texto de Tipo
	 * 
	 */
    @FXML
    private TextField tfTipo;

    /**
	 * Metodo responsavel por analisar o estado do botao Confirmar
	 * 
	 * @return estado do botao Confirmar
	 */
    public boolean isBtnConfirmarClicked() {
		return btnConfirmarClicked;
	}

    /**
	 * Metodo responsavel por configurar o estado do botao Confirmar
	 * 
	 * @param btnConfirmarClicked Registrador de estado do botao Confirmar
	 */
	public void setBtnConfirmarClicked(boolean btnConfirmarClicked) {
		this.btnConfirmarClicked = btnConfirmarClicked;
	}
	
	/**
	 * Metodo responsavel por verificar se os campos sao vazios 
	 * 
	 * @throws CampoVazioException se algum campo esta vazio
	 */
	public void validacaoVazio() throws CampoVazioException{
		if(tfNome.getText().compareTo("") == 0 ||
		tfTipo.getText().compareTo("") == 0 ||
		tfLogin.getText().compareTo("") == 0 ||
		pfSenha.getText().compareTo("") == 0 ||
		pfConfSenha.getText().compareTo("") == 0) {
			throw new CampoVazioException("Todos os campos devem ser preenchidos!");
		}
	}
	
	/**
	 * Metodo responsavel por verificar se o campo Tipo eh valido 
	 * 
	 * @throws TipoInvalidoException se o campo Tipo eh invalido
	 */
	public void validacaoTipo() throws TipoInvalidoException {
		if(tfTipo.getText().compareTo("1") != 0 && tfTipo.getText().compareTo("2") != 0) {
			throw new TipoInvalidoException("Tipo precisa ser 1 ou 2!");
		}
	}
	
	/**
	 * Metodo responsavel por verificar se os campos de senha sao validos 
	 * 
	 * @throws TamanhoSenhaInvalidaException se os campos de senha sao invalidos
	 */
	public void validacaoSenhaTam() throws TamanhoSenhaInvalidaException {
		if(pfSenha.getText().length() < 7 || pfConfSenha.getText().length() < 7) {
			throw new TamanhoSenhaInvalidaException("A senha inserida deve conter pelo menos 8 caracteres");
		}
	}
	
	/**
	 * Metodo responsavel por verificar se os campos de senha sao iguais 
	 * 
	 * @throws IgualdadeSenhaInvalidaException se os campos de senha diferem
	 */
	public void validacaoSenhaIg() throws IgualdadeSenhaInvalidaException {
		if(pfSenha.getText().compareTo(pfConfSenha.getText()) != 0) {
			throw new IgualdadeSenhaInvalidaException("Os campos Senha e Confirmar Senha nao coincidem");
		}
	}
	
	/**
	 * Metodo responsavel por verificar se os campos sao validos 
	 * 
	 * @return inteiro que indica se os campos preenchidos sao validos
	 * 
	 * @throws ValidacaoException se os campos estao invalidos
	 */
	public int validacao() throws ValidacaoException{
		int flag = 1;
		try {
			validacaoVazio();
			validacaoTipo();
			validacaoSenhaTam();
			validacaoSenhaIg();
		} catch(CampoVazioException ex) {
			flag = 0;
			throw new ValidacaoException("Excecao gerada pelo metodo validacaoVazio()", ex);
		} catch(TipoInvalidoException ex) {
			flag = 0;
			throw new ValidacaoException("Excecao gerada pelo metodo validacaoTipo()", ex);
		} catch(TamanhoSenhaInvalidaException ex) {
			flag = 0;
			throw new ValidacaoException("Excecao gerada pelo metodo validacaoSenhaTam()", ex);
		} catch(IgualdadeSenhaInvalidaException ex) {
			flag = 0;
			throw new ValidacaoException("Excecao gerada pelo metodo validacaoSenhaIg()", ex);
		}
		return flag;
	}
	
	/**
	 * Metodo responsavel por fechar a tela CadastroUsuario
	 * 
	 * @param event Evento de interacao
	 */
	@FXML
    void cancelarUsuario(ActionEvent event) {
    	UsuarioStage.close();
    	UsuarioVipStage.close();
    }
    
	/**
	 * Metodo responsavel por inserir um Usuario no DAO apropriado
	 * 
	 * @param event Evento de interacao
	 */
	@FXML
    void inserirUsuario(ActionEvent event) {
    	btnConfirmarClicked = true;
    	try {
    		int f = validacao();
    		if(f == 1) {
    			String nome = tfNome.getText();
    			String tipo = tfTipo.getText();
    			String login = tfLogin.getText();
    			String senha = pfSenha.getText();
    			
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
    			FileWriter escritor = new FileWriter("src/br/ufrn/imd/data/usuarios.txt");
    			escritor.write("");
    			bdUsuario = UsuarioDAO.getInstance();
    			bdUsuarioVip = UsuarioVipDAO.getInstance();
    			bdUsuario.registrarUsuarios();
    			bdUsuarioVip.registrarUsuariosVip();
    			escritor.close();
    		}
    	} catch(ValidacaoException ex) {
    		ex.printStackTrace();
    		System.out.println("Falha de validacao!");
    	} catch(IOException ex) {
    		ex.printStackTrace();
    		System.out.println("Falha em registrar o novo usuario!");
    	}
    	UsuarioStage.close();
    	UsuarioVipStage.close();
    }
    
	/**
	 * Metodo responsavel por receber e configurar um estagio da tela CadastroUsuario para Usuarios Comuns
	 * 
	 * @param uStage Estagio vindo da tela Login
	 */
	public void setUsuarioStage(Stage uStage) {
		this.UsuarioStage = uStage;
	}
    
	/**
	 * Metodo responsavel por receber e configurar um estagio da tela CadastroUsuario para Usuarios Vips
	 * 
	 * @param uvStage Estagio vindo da tela Login
	 */
    public void setUsuarioVipStage(Stage uvStage) {
		this.UsuarioVipStage = uvStage;
	}

}
