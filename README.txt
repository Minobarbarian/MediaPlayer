==================================================================================
=============================== AMBIENTALIZAÇÃO ==================================
==================================================================================
Recomendo utilizar a versão 2022-3 do eclipse disponível em https://www.eclipse.org/downloads/packages/release.

Adicionalmente, serão necessários: SceneBuilder versão 19 e bibliotecas do javafx
(javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media,
(javafx.swing, javafx.web e javafx-swt).

Após instalar tudo e arranjar as libs javafx, abra o eclipse e instale o
plugin e(fx)clipse 3.8.0.

Quando terminar, configure os caminhos:

Window > Preferences > Java > Build Path > User Libraries > Crie uma lib(New) para
as libs javafx e insira elas nessa lib (Add External JARs).

Window > Preferences > JavaFX >
Encontre o caminho do .exe do SceneBuilder instalado.
Encontre o caminho da lib contendo as libs do javafx.

==================================================================================
================================== COMPILAÇÃO ====================================
==================================================================================
Após importar o projeto no eclipse workspace apropriado:

 Se houver uma exclamação vermelha no Projeto abaixo de Package Explorer:
  O JRE utilizado está unbounded, para ajeitar terá que: clique com o direito no
  projeto > Build Path > Configure Build Path > Aba Libraries > Remova a lib
  problemática > Add Library > JRE System Library > Workspace default JRE > finish

 Se houver um x branco numa caixa vermelha no Projeto abaixo de Package Explorer:
  Ela está informando sobre um erro de .css inconsequente a execução do código.

 Se não tiver avisos problemáticos, acesse a classe Main.java em
 ProjFinal/src/br/ufrn/imd e rode o código (ctrl + f11).