package Cliente;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import java.io.File;

/**
A classe Janela constroi a interface grafica do programa de labirinto, possuindo todas as funcionalidades que o cliente pode realizar, sendo elas construir um novo labirinto, apresentar todos os labirintos salvos pelo usuario, salvar labirinto, executá-lo e selecionar labirinto ja salvo.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Janela {

	/**
  Construtor da TextArea visor com valor vazio.
  */
  private JTextArea visor = new JTextArea("");

	/**
  Construtor da Label info que exibe uma mensagem de boas vindas.
  */
	private JTextArea info = new JTextArea("");

	/**
  Contrutor de um vetor de Button contendo 6 botoes.
  */
  private JButton botao[] = new JButton[6];

	/**
  Contrutor de uma TextArea log de erros.
  */
  private JTextArea log = new JTextArea("Log de erros");

	/**
  Construtor que mantem armazenado o IP do usuario.
	*/
  private String ip;

	/**
	Construtor que armazena a conexao com o servidor.
	*/
  private Parceiro serv;
  
  private JLabel inst = new JLabel("Insira aqui o nome do labirinto.");
	
    /**
    Construtor padrao da Janela. Define a fonte do visor como monospaced, cria os 4 botoes em um GridLayout, adicionando o tratadorDeMouse a eles; inicialmente, o botao de executar labirinto e desabilitado; adiciona a area de log na parte sul do BorderLayout da janela, restringindo sua edicao; adiciona uma JLabel que auxilia o usuario, indicando as acoes que ele pode realizar; adiciona barra de scroll ao visor.
		Atribui valor ao servidor, que foi passado por parametro.
		@param servidor armazenara a conexão com o servidor
    */
	  public Janela(Parceiro servidor) {
      serv = servidor;
      visor.setFont(new Font("monospaced", Font.PLAIN, 12));
      JPanel botoes = new JPanel();
      JPanel novol = new JPanel();
      //grid botoes
      botoes.setLayout(new GridLayout(1, 5));
      //texto botoes
      String texto[] = {"Novo labirinto", "Abrir labirinto", "Salvar labirinto", "Executar labirinto", "Selecionar labirinto", "Desconectar"};

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
      janela.setSize(880, 500);
      janela.getContentPane().setLayout(new BorderLayout());

      //Definindo localizacao
      janela.add(novol, BorderLayout.NORTH);
			
      novol.setLayout(new BorderLayout());
      novol.add(this.info, BorderLayout.CENTER);
      novol.add(this.inst, BorderLayout.NORTH);
      novol.add(botoes, BorderLayout.SOUTH);
			
      janela.add(this.visor, BorderLayout.CENTER);
      janela.add(this.log, BorderLayout.SOUTH);

      JScrollPane scroll = new JScrollPane(visor);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      janela.getContentPane().add(scroll);

      janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      janela.setVisible(true);
  }

	/**
  Constroi e valida nova instancia String ipCli.
  Atribui um valor para ipCli com a nova instancia criada e informa o erro caso o ipCli for igual a null
	@param ipCli armazenara o IP do usuario
	@throws Exception quando o IP for null
	*/
  public void setIp(String ipCli) throws Exception{
    if(ipCli == null) throw new Exception("Ip invalido");
    ip = ipCli;
  }

	/**
  Retorna o IP do usuario
  @return ip do usuario
	*/
  public String getIp(){
    return ip;
  }

  /**
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
    Limpa o visor, permitindo a criação de um novo labirinto. Habilita o botao de salvar e bloqueia o de executar, evitando possíveis erros, tambem limpando a variavel nome.
    */
    private void btnNovoLabirinto(){
      botao[2].setEnabled(true);
      botao[3].setEnabled(false);
      nome = "";
	    info.setText("");
      visor.setText("");
      log.setText("Log de erros");
	  }

  /**
  Abre os labirintos previamente criados, imprimindo-os no visor. Desabilita os botoes de salvar e executar labirinto. Instancia um pedido de labirintos com o IP do cliente e espera receber um comunicado de resultado. Caso nao haja labirintos salvos no IP do cliente, e impresso no log que nao ha labirintos cadastrados.
  */
	private void btnAbrirLabirinto(){
		botao[2].setEnabled(false);
		botao[3].setEnabled(false);
		log.setText("Log de erros");
		info.setText("");
		PedidoLabirintos pedido = new PedidoLabirintos(ip);
		try{
			serv.receba(pedido);
			Comunicado comunicado = null;
			do{
			  comunicado = (Comunicado)serv.espie();
			}while (!(comunicado instanceof Resultado));
			Resultado resultado = (Resultado)serv.envie ();
			visor.setText("");
			if(resultado.getQtd() > 0){
				for(int i = 0; i<resultado.getQtd(); i++){
				  visor.setText(visor.getText() + resultado.getLabirinto(i).toString() + "\n\n");
				}
			}
			else
				log.setText("Nenhum labirinto cadastrado");
		}catch(Exception erro){
			log.setText("Erro de comunicacao com o servidor. Tente novamente!\nCaso o erro persista, termine o programa e volte a tentar mais tarde!");
		}
  }

  /**
  Permite salvar o labirinto escrito, valindando-o. Habilita o botao de execucao do labirinto caso o mesmo seja valido. Posteriormente, o lab e instanciado com o conteudo do visor e e enviado ao servidor o pedido de salvamento com o nome inserido no campo info, conteudo do visor e ip do cliente. Caso houver alguma incongruencia com o labirinto a ser salvo, e exibido o erro no log de erros. Caso nao ocorra nenhum erro, o metodo e finalizado.
  */
	private void btnSalvarLabirinto(){
    if(info.getText().isEmpty())
		{
			log.setText("Insira um nome para o labirinto");
			return;
		}
		log.setText("Log de erros");
		try{
			int n = visor.getLineCount();
			int m = 1;
			String conteudo = visor.getText();
			while(conteudo.charAt(m)!='\n')
				m++;
			char[][] matriz = new char[n][m];
			int pos=0;
			int i=0, j=0;
			for(i=0; i<n; i++)
			{
				for(j=0; j<=m; j++)
				{
					if(i == n-1 && j == m)
						break;
					if(conteudo.charAt(pos) != '\n')
					{
						matriz[i][j] = conteudo.charAt(pos);
					}
					pos++;
				}
			}
			lab = new Labirinto(matriz);
			lab.encontrarEntradaSaida(matriz);
      nome = info.getText();
			PedidoDeSalvamento salvarLab = new PedidoDeSalvamento(nome, conteudo, ip);
			serv.receba(salvarLab);
			log.setText("Labirinto salvo com sucesso!");
			botao[3].setEnabled(true);
		}catch(IndexOutOfBoundsException erro){
			log.setText("Numero de linhas inconsistente");
		}
		catch(Exception erro){
			log.setText(erro.getMessage());
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
    Permite selecionar um labirinto salvo a partir de seu nome. Os botoes de salvar e executar sao habilitados. O nome e instanciado com o conteudo da TextArea info, que por fim e parametro de um objeto de pedido de labirinto. E esperado o comunicado do servidor com o labirinto pedido, que posteriormente tem seu conteudo exposto no visor. A matriz e o labirinto sao instanciados com esse conteudo. Caso nao seja encontrado um labirinto com o nome inserido, o log informa ao usuario que o labirinto nao esta cadastrado.
    */
		private void btnSelecionarLabirinto(){
		  botao[2].setEnabled(true);
		  botao[3].setEnabled(true);
		  nome = info.getText();
      visor.setText("");
      PedidoLabirinto abrirLab = new PedidoLabirinto(nome);
      try{
			  serv.receba(abrirLab);
			  Comunicado comunicado = null;
			  do{
				comunicado = (Comunicado)serv.espie();
			  }while(!(comunicado instanceof LabirintoPedido));
			  LabirintoPedido labPedido = (LabirintoPedido)serv.envie();
			  LabirintoResultado labResultado = labPedido.getLabirinto();
			  if(labResultado.getConteudo().isEmpty()){
          log.setText("Labirinto nao cadastrado");
          return;
			  }
			  visor.setText(labResultado.getConteudo());
			  int n = visor.getLineCount();
			  int m = 1;
			  String conteudo = visor.getText();
			  while(conteudo.charAt(m)!='\n')
				m++;
			  char[][] matriz = new char[n][m];
			  int pos=0;
			  int i=0, j=0;
			  try{
				for(i=0; i<n; i++)
				{
				  for(j=0; j<=m; j++)
				  {
					if(i == n-1 && j == m)
					  break;
					if(conteudo.charAt(pos) != '\n')
					{
					  matriz[i][j] = conteudo.charAt(pos);
					}
					pos++;
				  }
				}
				lab = new Labirinto(matriz);
				lab.encontrarEntradaSaida(matriz);
			  }catch(IndexOutOfBoundsException erro){
				  log.setText("Numero de linhas inconsistente");
			  }
		  }catch(Exception erro){
        log.setText(erro.getMessage());
      }
    }

    /**
    Fecha a coneccao socket com o servidor, enviando um pedido para sair e desabilitando todos os botoes.
    */
    private void btnDesconectar(){
		  try{
			  serv.receba(new PedidoParaSair());
		  }catch(Exception erro){}
		  log.setText("Voce se desconectou do servidor! Reinicie o programa para utiliza-lo novamente");
		  botao[0].setEnabled(false);
		  botao[1].setEnabled(false);
		  botao[2].setEnabled(false);
		  botao[3].setEnabled(false);
		  botao[4].setEnabled(false);
		  botao[5].setEnabled(false);
	  }

    /**
    Reimplementa o metodo actionPerformed, possibilitando executar os metodos correspondentes aos botoes selecionados. Ao clicar no botao, e verificado qual o texto correspondente que e exibido no visor, levando ao metodo que deve ser executado.
    @param e possui o registro do evento ocorrido, neste caso, a String que identifica o botao.
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
      else if (comando == "Selecionar labirinto")
		    this.btnSelecionarLabirinto();
      else if (comando == "Desconectar")
		    this.btnDesconectar();
      else // comando=="Executar labirinto"
        this.btnExecutarLabirinto();
    }
  }
}