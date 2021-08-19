import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import java.io.File;

/**
A classe Janela constroi a interface grafica do programa de labirinto

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Janela {

	/**
  Construtor da TextArea visor com valor vazio
  */
  private JTextArea visor = new JTextArea("");

	/**
  Construtor da Label info que exibe uma mensagem de boas vindas
  */
	private JLabel info = new JLabel("Bem-vindo(a) ao programa labirinto! Selecione um botao.");

	/**
  Contrutor de um vetor de Button contendo 4 botoes
  */
  private JButton botao[] = new JButton[4];

	/**
  Contrutor de uma TextArea log de erros
  */
  private JTextArea log = new JTextArea("Log de erros");
	
    /**
    Construtor padrao da Janela. Define a fonte do visor como monospaced, cria os 4 botoes em um GridLayout, adicionando o tratadorDeMouse a eles; inicialmente, o botao de executar labirinto e desabilitado; adiciona a area de log na parte sul do BorderLayout da janela, restringindo sua edicao; adiciona uma JLabel que auxilia o usuario, indicando as acoes que ele pode realizar; adiciona barra de scroll ao visor.
    */
	  public Janela() {
      visor.setFont(new Font("monospaced", Font.PLAIN, 12));
      JPanel botoes = new JPanel();
      JPanel novol = new JPanel();
      //grid botoes
      botoes.setLayout(new GridLayout(1, 4));
      //texto botoes
      String texto[] = {"Novo labirinto", "Abrir labirinto", "Salvar labirinto", "Executar labirinto"};

      TratadoraDeMouse tratadorDeMouse = new TratadoraDeMouse();

      //criacao dos botoes
      for (int i = 0; i < this.botao.length; i++) {
        this.botao[i] = new JButton(texto[i]);

        this.botao[i].setActionCommand(texto[i]);
        this.botao[i].addActionListener(tratadorDeMouse);

      	//cor no botao
        this.botao[i].setContentAreaFilled(false);
        this.botao[i].setOpaque(true);
        this.botao[i].setBackground(new java.awt.Color(166, 255, 193));
        
        //fonte do botao
        this.botao[i].setForeground(new java.awt.Color(0, 0, 0));
        botoes.add(this.botao[i]);
      }
      botao[3].setEnabled(false);

      //Area do log
      log.setEditable(false);
      log.setForeground(Color.red);
      log.setFont(log.getFont().deriveFont(Font.BOLD));
      log.setLineWrap(true);
      log.setWrapStyleWord(true);
      log.setBackground(new java.awt.Color(212, 212, 212));

      //interface
      JFrame janela = new JFrame("Labirinto");
      janela.setSize(700, 450);
      janela.getContentPane().setLayout(new BorderLayout());

      //Definindo localizacao
      janela.add(novol, BorderLayout.NORTH);
			
      novol.setLayout(new BorderLayout());
      novol.add(this.info, BorderLayout.NORTH);
      novol.add(botoes, BorderLayout.CENTER);
			
      janela.add(this.visor, BorderLayout.CENTER);
      janela.add(this.log, BorderLayout.SOUTH);

      JScrollPane scroll = new JScrollPane(visor);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      janela.getContentPane().add(scroll);

      janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      janela.setVisible(true);
  }

  /*
	Trata acoes de clicar em um botao. Verifica qual botao foi selecionado e executa o que lhe foi programado.
	*/
  private class TratadoraDeMouse implements ActionListener {

		/**
		Armazena o elemento lab em Labirinto
		*/
    private Labirinto lab;

		/**
		Armazena um nome em um tipo String
		*/
    private String nome;

		/**
    Limpa o visor, permitindo a criação de um novo labirinto. Habilita o botao de salvar e bloqueia o de executar, evitando possíveis erros, tambem limpando o visor e a variavel nome.
    */
    private void btnNovoLabirinto(){
      botao[2].setEnabled(true);
      botao[3].setEnabled(false);
      nome = "";
			info.setText("Digite seu Labirinto. ");
      visor.setText("");
      log.setText("Log de erros");
		}

    /**
    Abre um labirinto previamente criado a partir de um arquivo que deve ser selecionado, imprimindo-o no visor. Habilita os botoes de salvar e executar labirinto, abrindo um selecionador de arquivo. Depois de selecionado pelo usuario, o conteudo do arquivo e impresso na tela e o lab e instanciado a partir dele.
    */
		private void btnAbrirLabirinto(){
			botao[2].setEnabled(true);
      botao[3].setEnabled(true);
      log.setText("Log de erros");
			info.setText(" ");
      JFileChooser arquivo = new JFileChooser();
			arquivo.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = arquivo.showOpenDialog(visor);
			if (result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = arquivo.getSelectedFile();
        nome = selectedFile.getName();
			}
      
      BufferedReader in = null;
      try{
        in = new BufferedReader(new FileReader(nome));
        in.readLine();
        visor.setText("");
        String str;
        str = in.readLine();
        visor.setText(str);
        while((str = in.readLine()) != null){
          visor.setText(visor.getText() + "\n" + str);
        }
      in.close();
      } catch(IOException e){}
      
      CriaLabirinto texto = new CriaLabirinto();
      try{
		    lab = texto.labirinto(nome);
	    }
	    catch(Exception erro1){
			  log.setText(erro1.getMessage());
	    }
    }

    /**
    Permite salvar o labirinto escrito, valindando-o. Habilita o botao de execucao do labirinto, abrindo uma janela para selecionar a pasta a ser salvo o arquivo, copiando o conteudo do visor para o mesmo. Posteriormente, o lab e instanciado com esse conteudo e, caso houver alguma incongruencia com o labirinto a ser salvo, e exibido o erro no log de erros e o arquivo e apagado. Caso nao ocorra nenhum erro, o metodo e finalizado.
    */
		private void btnSalvarLabirinto(){
			info.setText(" ");
      botao[3].setEnabled(true);
      log.setText("Log de erros");
 
      JFileChooser arquivo = new JFileChooser();
			arquivo.setDialogTitle("Salvar Arquivo");
      int userSelection = arquivo.showSaveDialog(visor);

      FileWriter salvar = null;
      File f = null;
      try{
        if (userSelection == JFileChooser.APPROVE_OPTION) {
          salvar = new FileWriter(arquivo.getSelectedFile());
          f = arquivo.getSelectedFile();
          nome = f.getName();
          int n = visor.getLineCount();
          salvar.write(n + "\n");
          salvar.write(visor.getText());
          salvar.close();
        }
      }catch(IOException e){}
      CriaLabirinto texto = new CriaLabirinto();
      try{
		    lab = texto.labirinto(nome);
	    }
	    catch(Exception erro1){
			  log.setText(erro1.getMessage());
			  f.delete();
	    }
    }

    /**
    Permite a execucao de um labirinto previamente salvo, informando se foi posivel encontrar a saida ou nao, exibindo o resultado no visor. O botao de salvar e bloqueado, nao permitindo salvar o labirinto resolvido. O lab que foi previamente instanciado e utilizado para chamar os metodos encontrarAdj e andadinha, que iniciam a resolucao do labirinto. Por fim, e exibido no log de erros o caminho percorrido. Caso ocorra algum erro, o erro e exibido no log de erros.
    */
		private void btnExecutarLabirinto(){
      log.setText("Log de erros");
      botao[2].setEnabled(false);
      try{
        lab.encontrarAdj();
        lab.andadinha();
        info.setText(" ");
        visor.setText(lab.toString());

        Pilha<Coordenada> caminho = lab.getCaminho();
        Pilha<Coordenada> inverso = new Pilha<Coordenada>();
        while(!caminho.isVazia())
        {
          inverso.guardeUmItem(caminho.recupereUmItem());
          caminho.removaUmItem();
        }

        log.setText("Caminho percorrido: ");
        while(!inverso.isVazia())
        {
          log.setText(log.getText() + inverso.recupereUmItem().toString() + " ");
          inverso.removaUmItem();
        }
      }
      catch(Exception erro1){
        log.setText(erro1.getMessage());
      }
		}

    /**
    Reimplementa o metodo actionPerformed, possibilitando executar os metodos correspondentes aos botoes selecionados. Ao clicar no botao, e verificado qual o texto correspondente que e exibido no visor, levando ao metodo que deve ser executado.
    @param e possui o registro do evento ocorrido, neste caso, a String que identifica o botao
    */
    @Override
    public void actionPerformed(ActionEvent e) {
      String comando = e.getActionCommand();

      if (comando == "Novo labirinto")
        this.btnNovoLabirinto();
      else if (comando == "Abrir labirinto")
        this.btnAbrirLabirinto();
      else if (comando == "Salvar labirinto")
        this.btnSalvarLabirinto();
      else // comando=="Executar labirinto"
        this.btnExecutarLabirinto();
    }
  }
}