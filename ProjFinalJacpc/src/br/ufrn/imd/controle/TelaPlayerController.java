package br.ufrn.imd.controle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import br.ufrn.imd.controle.Exceptions.AutenticacaoException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Classe responsavel por controlar a tela Player.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
public class TelaPlayerController {
	
	/**
	 * Estagio da tela Player
	 * 
	 */
	private Stage PlayerStage;
	
	/**
	 * Arquivo .mp3
	 * 
	 */
	private Media media;
	
	/**
	 * Tocador de arquivos .mp3
	 * 
	 */
	private MediaPlayer medPlayer;
	
	/**
	 * Diretorio principal
	 * 
	 */
	private File mainDir = new File("src/br/ufrn/imd/data/diretorios");
	
	/**
	 * Principal diretorio de diretorios
	 * 
	 */
	private File mainMusicas = new File("src/br/ufrn/imd/data/musicas");
	
	/**
	 * Principal diretorio de playlists
	 * 
	 */
	private File mainPlaylists = new File("src/br/ufrn/imd/data/playlists");
	
	/**
	 * Diretorio atual
	 * 
	 */
	private File actualDir = new File("src/br/ufrn/imd/data/musicas");
	
	/**
	 * Playlist atual
	 * 
	 */
	private File actualPlaylist;
	
	/**
	 * Diretorio de playlists associadas ao Usuario Vip da vez
	 * 
	 */
	private File playDir;
	
	/**
	 * Valor inicial do Slider
	 * 
	 */
	private int changedSlider = 0;
	
	/**
	 * Login do Usuario Vip da vez
	 * 
	 */
	private String loginVip = "";
	
	/**
	 * Indicador do tipo de arquivo aberto atual
	 * 
	 */
	private String estaAberto = "Diretorio";
	
	/**
	 * Registrador do estado atual do Slider
	 * 
	 */
	private boolean changing = false;
	
	/**
	 * Painel principal da tela Player
	 * 
	 */
	@FXML
    private AnchorPane ancPaneTelaPlayer;

	/**
	 * Label da area de texto dos diretorios
	 * 
	 */
    @FXML
    private Label lbDiretorio;

    /**
	 * Label da acao de escolha de .mp3
	 * 
	 */
    @FXML
    private Label lbEscolha;

    /**
	 * Label da area de texto das musicas
	 * 
	 */
    @FXML
    private Label lbMusicas;

    /**
	 * Label do Nome do Usuario da sessao
	 * 
	 */
    @FXML
    private Label lbNome;

    /**
	 * Label do Tipo do Usuario da sessao
	 * 
	 */
    @FXML
    private Label lbUsuario;

    /**
	 * Label da area de texto das playlists
	 * 
	 */
    @FXML
    private Label lbPlaylistNome;

    /**
	 * Opcao de abrir diretorio
	 * 
	 */
    @FXML
    private MenuItem mnItemAbrirDir;

    /**
	 * Opcao de abrir playlist
	 * 
	 */
    @FXML
    private MenuItem mnItemAbrirPlay;

    /**
	 * Opcao de inserir diretorio
	 * 
	 */
    @FXML
    private MenuItem mnItemAddDir;

    /**
	 * Opcao de inserir musica
	 * 
	 */
    @FXML
    private MenuItem mnItemAddMus;
    
    /**
	 * Opcao de inserir playlist
	 * 
	 */
    @FXML
    private MenuItem mnItemAddPlay;

    /**
	 * Opcao de fechar diretorio
	 * 
	 */
    @FXML
    private MenuItem mnItemFecharDir;
    
    /**
	 * Opcao de remover diretorio
	 * 
	 */
    @FXML
    private MenuItem mnItemRemDir;

    /**
	 * Opcao de remover musica
	 * 
	 */
    @FXML
    private MenuItem mnItemRemMus;
    
    /**
	 * Opcao de remover playlist
	 * 
	 */
    @FXML
    private MenuItem mnItemRemPlay;

    /**
	 * Opcao de sair da tela Player
	 * 
	 */
    @FXML
    private MenuItem mnItemSair;

    /**
	 * Opcao de conferir instrucoes e observacoes
	 * 
	 */
    @FXML
    private MenuItem mnItemSob;

    /**
	 * Area de texto dos diretorios
	 * 
	 */
    @FXML
    private TextArea taDiretorio;

    /**
	 * Area de texto das musicas
	 * 
	 */
    @FXML
    private TextArea taMusicas;

    /**
	 * Area de texto das playlists
	 * 
	 */
    @FXML
    private TextArea taPlaylists;
    
    /**
	 * Slider do progresso do .mp3 atual
	 * 
	 */
    @FXML
    private Slider sAndamentoMusica;
    
    /**
	 * Metodo responsavel por abrir um diretorio
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void abrirDir(ActionEvent event) {
    	DirectoryChooser chooser = new DirectoryChooser();
    	chooser.setTitle("Selecione o diretório");
    	chooser.setInitialDirectory(mainDir);
    	File file = chooser.showDialog(PlayerStage);
    	if(file != null) {
    		actualDir = file;
    		estaAberto  = "Diretorio";
    		setMusicasTitle(actualDir.getName());
    		setTAMusicas(1);
    	}
    }
    
    /**
	 * Metodo responsavel por abrir uma playlist
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void abrirPlaylist(ActionEvent event) {
    	DirectoryChooser chooser = new DirectoryChooser();
    	chooser.setTitle("Selecione a playlist");
    	chooser.setInitialDirectory(playDir);
    	File file = chooser.showDialog(PlayerStage);
    	if(file != null) {
    		actualPlaylist = file;
    		estaAberto  = "Playlist";
    		setMusicasTitle(actualPlaylist.getName());
    		setTAMusicas(2);
    	}
    }
    
    /**
	 * Metodo responsavel por fechar um diretorio
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void fecharDir(ActionEvent event) {
    	actualDir = new java.io.File("src/br/ufrn/imd/data/musicas");
    	setMusicasTitle(actualDir.getName());
		setTAMusicas(1);
    }
    
    /**
	 * Metodo responsavel por inserir um diretorio
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void addDir(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaCriacaoDirController.class.getResource("/br/ufrn/imd/visao/TelaCriacaoDir.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage mkStage = new Stage();
    	mkStage.setTitle("Criação de Diretório");
    	mkStage.setResizable(false);
    	Scene scene = new Scene(page);
    	mkStage.setScene(scene);
    	
    	TelaCriacaoDirController controller = loader.getController();
    	controller.setMkDirStage(mkStage);
    	mkStage.showAndWait();
    	
    	escreveDirsTxt();
    	setTADir();
    }

    /**
	 * Metodo responsavel por inserir uma musica
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void addMusica(ActionEvent event) throws IOException{
    	FileChooser chooser = new FileChooser();
    	chooser.setTitle("Selecione a música");
    	chooser.setInitialDirectory(new java.io.File("src/br/ufrn/imd/data/musicas"));
    	File file = chooser.showOpenDialog(PlayerStage);
    	if(file != null) {
    		
    		File[] files;
    		
    		if(estaAberto.compareTo("Diretorio") == 0) {
    			files = actualDir.listFiles();
    		} else {
    			files = actualPlaylist.listFiles();
    		}
    		
    		boolean flag = true;
    		for(File f : files) {
    			if(file.getName().compareTo(f.getName()) == 0) flag = false;
    		}
    		if(flag) {
    			File fi;
    			
    			if(estaAberto.compareTo("Diretorio") == 0) {
    				fi = new File(actualDir, file.getName());
    				Files.copy(file.toPath(), fi.toPath());
            		setTAMusicas(1);
        		} else {
        			fi = new File(actualPlaylist, file.getName());
        			Files.copy(file.toPath(), fi.toPath());
            		setTAMusicas(2);
        		}
    			
        		escreveMusicasTxt();
    		} else {
    			System.out.println(file.getName()+" nao foi inserida pois ja existe!");
    		}
    	}
    }
    
    /**
	 * Metodo responsavel por inserir uma playlist
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void addPlaylist(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaCriacaoDirController.class.getResource("/br/ufrn/imd/visao/TelaCriacaoDir.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage mkStage = new Stage();
    	mkStage.setTitle("Criação de Playlist");
    	mkStage.setResizable(false);
    	Scene scene = new Scene(page);
    	mkStage.setScene(scene);
    	
    	TelaCriacaoDirController controller = loader.getController();
    	controller.setMkDirStage(mkStage);
    	controller.setCaminho("src/br/ufrn/imd/data/playlists/"+lbNome.getText()+loginVip+"/");
    	mkStage.showAndWait();
    	
    	escrevePlaylistsTxt();
    	setTAPlaylists();
    }
    
    /**
	 * Metodo responsavel por remover um diretorio
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void remDir(ActionEvent event){
    	DirectoryChooser chooser = new DirectoryChooser();
    	chooser.setTitle("Selecione o diretório");
    	chooser.setInitialDirectory(mainDir);
    	File file = chooser.showDialog(PlayerStage);
    	if(file != null) {
    		File[] files = file.listFiles();
    		for(File f : files) { 
    			f.delete();
    		}
    		if(actualDir.getName() .compareTo(file.getName()) == 0) fecharDir(null);
    		if(file.getName().compareTo("musicas") != 0) {
    			file.delete();
        		setTADir();
        		try {
        			escreveDirsTxt();
        			escreveMusicasTxt();
        		} catch(IOException ex) {
        			ex.printStackTrace();
        			System.out.println("Nao conseguiu alterar diretorios.txt!");
        		}
    		} else {
    			System.out.println(file.getName()+" nao pode ser removido pois a pasta musicas eh protegida!");
    		}
    	}
    }
    
    /**
	 * Metodo responsavel por remover uma musica
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void remMus(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
    	chooser.setTitle("Selecione a música");
    	
    	if(estaAberto.compareTo("Diretorio") == 0) {
    		chooser.setInitialDirectory(actualDir);
		} else {
			chooser.setInitialDirectory(actualPlaylist);
		} 
    	
    	File file = chooser.showOpenDialog(PlayerStage);
    	if(file != null) {
    		
    		if(estaAberto.compareTo("Playlist") == 0) {
    			file.delete();
    			setTAMusicas(2);
    		} else {
    			if(actualDir.getName().compareTo("musicas") != 0) {
    				file.delete();
    				setTAMusicas(1);
    			} else {
    				System.out.println(file.getName()+" nao pode ser removido pois a pasta musicas eh protegida!");
    			}
    		}
    		
    		try{
				escreveMusicasTxt();
			} catch(IOException ex) {
				ex.printStackTrace();
				System.out.println("Nao conseguiu alterar diretorios.txt!");
			}
    	}
    }
    
    /**
	 * Metodo responsavel por remover uma playlist
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void remPlaylist(ActionEvent event) {
    	DirectoryChooser chooser = new DirectoryChooser();
    	chooser.setTitle("Selecione a playlist");
    	chooser.setInitialDirectory(playDir);
    	File file = chooser.showDialog(PlayerStage);
    	
    	if(file != null) {
    		File[] files = file.listFiles();
    		for(File f : files) { 
    			f.delete();
    		}
    		if(playDir.getName() .compareTo(file.getName()) == 0) fecharDir(null);
    		file.delete();
    		setTAPlaylists();
        	try {
        		escrevePlaylistsTxt();
        		escreveMusicasTxt();
        	} catch(IOException ex) {
        		ex.printStackTrace();
        		System.out.println("Nao conseguiu realizar a persistencia!");
        	}
    	}
    }
    
    /**
	 * Metodo responsavel por abrir a tela Ajuda
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void sobre(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaAjudaController.class.getResource("/br/ufrn/imd/visao/TelaAjuda.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage aStage = new Stage();
    	aStage.setTitle("Instruções de Uso");
    	aStage.setResizable(false);
    	Scene scene = new Scene(page);
    	aStage.setScene(scene);
    	
    	TelaAjudaController controller = loader.getController();
    	controller.setTAInstrucoes();
    	controller.setAjudaStage(aStage);
    	aStage.showAndWait();
    }
    
    /**
	 * Metodo responsavel por fechar a tela Player
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void sairPlayer(ActionEvent event) {
		PlayerStage.close();
    }
    
    /**
	 * Metodo responsavel por selecionar uma musica no diretorio ou playlist apropriado(a)
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void escolhaMusica(MouseEvent event) {
    	parar(null);
    	FileChooser chooser = new FileChooser();
    	chooser.setTitle("Selecione a música");
    	
    	if(estaAberto.compareTo("Playlist") == 0) {
			chooser.setInitialDirectory(actualPlaylist);
		} else {
			chooser.setInitialDirectory(actualDir);
		}
    	
    	File file = chooser.showOpenDialog(PlayerStage);
    	if(file != null) {
    		String selectedFile = file.toURI().toString();
    		media = new Media(selectedFile);
    		medPlayer = new MediaPlayer(media);
    		medStart(file.getName());
    	}
    }
    
    /**
	 * Metodo responsavel por parar a execucao do .mp3 atual
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void parar(MouseEvent event) {
    	if(medPlayer != null) medPlayer.stop();
    }

    /**
	 * Metodo responsavel por pausar a execucao do .mp3 atual
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void pausar(MouseEvent event) {
    	if(medPlayer != null) medPlayer.pause();
    }

    /**
	 * Metodo responsavel por tocar o .mp3 atual
	 * 
	 * @param event Evento de interacao
	 */
    @FXML
    void tocar(MouseEvent event) {
    	if(medPlayer != null) medPlayer.play();
    }
	
    /**
	 * Metodo responsavel por inicializar o tocador de arquivos .mp3
	 * 
	 * @param s Nome do .mp3 selecionado
	 */
    public void medStart(String s) {
		medPlayer.setOnReady(() -> {
			lbEscolha.setText(s);
			
			sAndamentoMusica.setMax(0);
			sAndamentoMusica.setMax(medPlayer.getTotalDuration().toSeconds());
			
			sAndamentoMusica.valueChangingProperty().addListener((observableValue, aBoolean, t1) -> {
				   changing = t1;
				   if (!changing) {
				      medPlayer.seek(Duration.seconds(changedSlider));
				   }
			});
			
			sAndamentoMusica.valueProperty().addListener((observableValue, number, t1) -> {
				   if (changing) {
				      changedSlider = t1.intValue();
				   } else {
				      int change = Math.abs(t1.intValue() - number.intValue());
				      if (change > 10) {
				    	  medPlayer.seek(Duration.seconds(t1.intValue()));
				      }
				   }
			});
			medPlayer.currentTimeProperty().addListener(
					 (observableValue, duration, t1) -> {
					 if (!changing) {
						 sAndamentoMusica.setValue(t1.toSeconds());
					 }
			});
		});
		
		
	}
	
    /**
	 * Metodo responsavel por receber e configurar o estagio da tela Player
	 * 
	 * @param pStage Estagio vindo da tela Login
	 */
    public void setPlayerStage(Stage pStage) {
		this.PlayerStage = pStage;
	}
	
    /**
	 * Metodo responsavel por configurar o Nome do Usuario da vez
	 * 
	 * @param s Nome de Usuario
	 */
    public void setNome(String s) {
		lbNome.setText(s);
	}
	
    /**
	 * Metodo responsavel por configurar o Nome do Label da area de texto de musicas
	 * 
	 * @param s Nome do Label
	 */
	public void setMusicasTitle(String s) {
		if(s.compareTo("musicas") == 0) {
			lbMusicas.setText("Todas as Músicas");
		} else {
			lbMusicas.setText(s);
		}
	}
	
	/**
	 * Metodo responsavel por preencher a area de texto de diretorios
	 * 
	 */
	public void setTADir() {
		taDiretorio.setText("");
		File[] files = mainDir.listFiles();
		for(File f : files) {
			taDiretorio.setText(taDiretorio.getText()+f.getName()+"\n");
		}
	}
	
	/**
	 * Metodo responsavel por preencher a area de texto de musicas
	 * 
	 * @param flag Indicador do tipo de arquivo aberto atual
	 */
	public void setTAMusicas(int flag) {
		taMusicas.setText("");
		File[] files = actualDir.listFiles();
		
		if(flag == 2) files = actualPlaylist.listFiles();
		
		for(File f : files) {
			if(f.isFile()) taMusicas.setText(taMusicas.getText()+f.getName()+"\n");
		}
	}
	
	/**
	 * Metodo responsavel por preencher a area de texto de playlists
	 * 
	 */
	public void setTAPlaylists() {
		taPlaylists.setText("");
		File[] files = playDir.listFiles();
		for(File f : files) {
			if(f.isDirectory()) taPlaylists.setText(taPlaylists.getText()+f.getName()+"\n");
		}
	}
	
	/**
	 * Metodo responsavel por realizar a persistencia dos caminhos de diretorios
	 * 
	 * @throws IOException se nao conseguiu ler o arquivo
	 */
	public void escreveDirsTxt() throws IOException{
		FileWriter escritor = new FileWriter(new java.io.File("src/br/ufrn/imd/data/diretorios.txt"), true);
		FileWriter apagador = new FileWriter(new java.io.File("src/br/ufrn/imd/data/diretorios.txt"));
		apagador.write("");
		
		File[] dirs = mainDir.listFiles();
		
		for(File f : dirs) {
			escritor.write(f.toURI().toString()+"\n");
		}
		escritor.close();
		apagador.close();
	}
	
	/**
	 * Metodo responsavel por realizar a persistencia dos caminhos de musicas
	 * 
	 * @throws IOException se nao conseguiu ler o arquivo
	 */
	public void escreveMusicasTxt() throws IOException{
		FileWriter escritor = new FileWriter(new java.io.File("src/br/ufrn/imd/data/musicas.txt"), true);
		FileWriter apagador = new FileWriter(new java.io.File("src/br/ufrn/imd/data/musicas.txt"));
		apagador.write("");
		
		File[] musicas = mainMusicas.listFiles();
		File[] dirs = mainDir.listFiles();
		File[] arquivos = mainPlaylists.listFiles();
		
		for(File f : musicas) {
			escritor.write(f.toURI().toString()+"\n");
		}
		
		for(File f : dirs) {
			File[] musicasF = f.listFiles();
			for(File g : musicasF) {
				if(g.isFile()) escritor.write(g.toURI().toString()+"\n");
			}
		}
		for(File f : arquivos) {
			if(f.isDirectory()) {
				File[] playDirs = f.listFiles();
				for(File playlists : playDirs) {
					File[] mus = playlists.listFiles();
					for(File g : mus) {
						if(g.isFile()) escritor.write(g.toURI().toString()+"\n");
					}
				}
			}
		}
		
		escritor.close();
		apagador.close();
	}
	
	/**
	 * Metodo responsavel por realizar a persistencia dos caminhos de playlists
	 * 
	 * @throws IOException se nao conseguiu ler o arquivo
	 */
	public void escrevePlaylistsTxt() throws IOException{
		FileWriter escritor = new FileWriter(new java.io.File("src/br/ufrn/imd/data/playlists/play_"+lbNome.getText()+loginVip+".txt"), true);
		FileWriter apagador = new FileWriter(new java.io.File("src/br/ufrn/imd/data/playlists/play_"+lbNome.getText()+loginVip+".txt"));
		apagador.write("");
		
		File[] files = new java.io.File("src/br/ufrn/imd/data/playlists/"+lbNome.getText()+loginVip).listFiles();
		for(File f : files) {
				escritor.write(f.toURI().toString()+"\n");
		}
		escritor.close();
		apagador.close();
	}
	
	/**
	 * Metodo responsavel por configurar a visibilidades das acoes dos Usuarios Vips
	 * 
	 */
	public void setVisibilites() {
		lbPlaylistNome.setVisible(false);
		taPlaylists.setVisible(false);
		mnItemAbrirPlay.setVisible(false);
		mnItemAddPlay.setVisible(false);
		mnItemRemPlay.setVisible(false);
	}
	
	/**
	 * Metodo responsavel por configurar o Tipo do Usuario da vez
	 * 
	 * @param s Tipo de Usuario
	 */
	public void setTipoUsuario(String s) {
		lbUsuario.setText(s);
	}
	
	/**
	 * Metodo responsavel por registrar o Login do Usuario Vip
	 * 
	 * @param s Login de Usuario Vip
	 */
	public void setLoginVip(String s) {
		loginVip = s;
		playDir = new File(mainPlaylists, lbNome.getText()+loginVip);
	}
}
