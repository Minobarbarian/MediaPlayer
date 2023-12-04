package br.ufrn.imd.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Classe responsavel por controlar a tela Ajuda.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class TelaAjudaController {

	/**
	 * O estagio da tela Ajuda
	 * 
	 */
	private Stage ajudaStage;
	
	/**
	 * Botao de sair da tela Ajuda
	 * 
	 */
    @FXML
    private Button btnSair;

    /**
	 * Area de texto da tela Ajuda
	 * 
	 */
    @FXML
    private TextArea taInstrucoes;

    /**
	 * Metodo responsavel por fechar a tela Ajuda
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void sair(ActionEvent event) {
    	ajudaStage.close();
    }
    
    /**
	 * Metodo responsavel por preencher o campo de texto
	 * 
	 */
    public void setTAInstrucoes() {
    	String lines = "Instruções:\n"
    				 + "Para abrir diretórios e playlists ou fechar diretórios, acesse a aba Arquivo.\n"
    				 + "Para manipular diretórios, músicas e playlists  acesse a aba Editar.\n"
    				 + "Para retornar a esta tela ou sair do player acesse a aba Ajuda.\n"
    				 + "Para selecionar uma música, clique em 'Escolha a música' ou o '.mp3' atual.\n"
    				 + "Para interagir com a música selecionada, utilize os botões: 'Tocar', 'Pausar' e 'Parar'.\n"
    				 + "Se quiser adiantar ou atrasar o .mp3 atual, interaja com o Slider por meio do Mouse.\n"
    				 + "Para sair dessa tela, utilize o botão 'Sair' abaixo.\n"
    				 + "\n"
    				 + "Observações:\n"
    				 + "Por razões de segurança, não será possível remover músicas da pasta musicas ou remover o diretório em si.\n"
    				 + "Quando uma música termina de tocar, se quiser que toque de novo, clique em 'Parar' seguido de 'Tocar'.\n"
    				 + "'Fechar Diretório' sempre retornará para a pasta com todas as músicas"
    				 + "'Adicionar música' sempre colocará o .mp3 no arquivo aberto atual.\n"
    				 + "Usuários Vip vêem somente suas próprias playlists\n";
    	taInstrucoes.setText(lines);
    }
    
    /**
	 * Metodo responsavel por receber e configurar o estagio da tela Ajuda
	 * 
	 * @param aStage Estagio vindo da tela Player
	 */
    public void setAjudaStage(Stage aStage) {
		this.ajudaStage = aStage;
	}

}
