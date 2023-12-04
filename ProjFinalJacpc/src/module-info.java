
/**
 * Classe responsavel por ler modulos.
 * 
 * 
 * @author Joao Antonio Costa Paiva Chagas
 * @version 1.0
 */
module ProjFinal {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;

	exports br.ufrn.imd;

	opens application to javafx.graphics, javafx.fxml;
	opens br.ufrn.imd.controle to javafx.fxml;
}
