package br.ufrn.imd.controle;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Classe responsavel por controlar a tela CriacaoDiretorio.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class TelaCriacaoDirController {
	
	/**
	 * Estagio da tela CriacaoDiretorio
	 * 
	 */
	private Stage mkDirStage;
	
	/**
	 * Principal diretorio de diretorios
	 * 
	 */
	private File mainDir = new File("src/br/ufrn/imd/data/diretorios");
	
	/**
	 * Caminho do diretorio que sera criado
	 * 
	 */
	private String caminhoDir = "src/br/ufrn/imd/data/diretorios/";
	
	/**
	 * Botao de criar diretorio
	 * 
	 */
    @FXML
    private Button btnCriar;
    
    /**
	 * Botao de sair da tela
	 * 
	 */
    @FXML
    private Button btnCancelar;

    /**
	 * Campo de texto de Nome
	 * 
	 */
    @FXML
    private TextField tfDirNome;

    /**
	 * Metodo responsavel por criar um novo diretorio
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void criar(ActionEvent event) {
    	if(tfDirNome.getText().compareTo("") != 0) {
    		if(!isIn(tfDirNome.getText())) {
    			File file = new File(caminhoDir+tfDirNome.getText());
            	file.mkdir();
            	sair();
    		} else {
    			System.out.println("Nome do diretorio nao pode ser repetido!");
    		}
    	} else {
    		System.out.println("Nome do diretorio nao pode ser vazio!");
    	}
    }
    
    /**
	 * Metodo responsavel por verificar a presenca de um diretorio no diretorio principal
	 * 
	 * @param s Nome do diretorio a ser procurado
	 * @return resultado da busca
	 */
    public boolean isIn(String s) {
    	File[] files = mainDir.listFiles();
    	for(File f : files) {
    		if(f.getName().compareTo(s) == 0) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
	 * Metodo responsavel por receber e configurar o estagio da tela CriacaoDiretorio
	 * 
	 * @param mkStage Estagio vindo da tela Player
	 */
    public void setMkDirStage(Stage mkStage) {
		this.mkDirStage = mkStage;
	}
    
    /**
	 * Metodo responsavel por receber e configurar o caminho do novo diretorio
	 * 
	 * @param s Caminho de diretorio
	 */
    public void setCaminho(String s) {
    	caminhoDir = s;
    	
    }
    
    /**
	 * Metodo responsavel por fechar a tela CriacaoDiretorio
	 * 
	 */
    public void sair() {
    	mkDirStage.close();
    }

}
