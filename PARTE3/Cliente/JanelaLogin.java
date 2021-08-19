package Cliente;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.BorderFactory;

/**
A classe Janela Login constroi a interface grafica do login do usuario no sistema

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class JanelaLogin {

	/**
  Construtor da TextArea visor que permite a entrada de um IP.
  */
	private JTextArea visor = new JTextArea ("");

	/**
  Construtor da Label info que exibe uma mensagem informacional.
  */
	private JLabel info = new JLabel("");

	/**
  Contrutor de um vetor de Button contendo 2 botoes.
  */
	private JButton botao[] = new JButton[2];

	/**
	Construtor de um JFrame janela login.
	*/
	private JFrame janela = new JFrame("Login");

	/**
  Construtor que mantem armazenado o IP do usuario.
	*/
	private String ipCli;

	/**
	Construtor que armazena a conexao com o servidor.
	*/
  private Parceiro serv;

    /**
    Construtor padrao da JanelaLogin. Define a fonte do visor como monospaced, cria os 2 botoes em um GridLayout, adicionando o tratadorDeMouse a eles;
		O botao limpar limpa o JFormattedTextField do IP do usuário e o botao enviar Envia o IP do usuario para armazenar e entrar no programa de labirinto caso seja valido.
    @param servidor armazenara a conexao com o servidor
    */
	public JanelaLogin(Parceiro servidor) {
		serv = servidor;
		visor.setFont(new Font("monospaced", Font.PLAIN, 12));

		JPanel botoes = new JPanel();
		JPanel novol = new JPanel();

		botoes.setMinimumSize(new Dimension(50, 60));
		botoes.setPreferredSize(new Dimension(10, 20));
		botoes.setMaximumSize(new Dimension(50, 60));

		// grid botoes
		botoes.setLayout(new GridLayout(1, 2));
		// texto botoes
		String texto[] = { "Limpar", "Confirmar" };

		TratadoraDeMouse tratadorDeMouse = new TratadoraDeMouse();

		// criacao dos botoes
		for (int i = 0; i < this.botao.length; i++) {
			this.botao[i] = new JButton(texto[i]);

			this.botao[i].setActionCommand(texto[i]);
			this.botao[i].addActionListener(tratadorDeMouse);

			// cor no botao
			this.botao[i].setContentAreaFilled(false);
			this.botao[i].setOpaque(true);
			this.botao[i].setBackground(new java.awt.Color(166, 255, 193));

			// fonte do botao
			this.botao[i].setForeground(new java.awt.Color(0, 0, 0));

		}
		botoes.add(botao[0], BorderLayout.LINE_START);
		botoes.add(botao[1], BorderLayout.LINE_END);

		// interface
		janela.setSize(350, 110);
		janela.getContentPane().setLayout(new BorderLayout());

		// Definindo localizacao
		janela.add(novol, BorderLayout.NORTH);

		info.setText("Digite seu Login. ");

		novol.setLayout(new BorderLayout());
		novol.add(this.info, BorderLayout.NORTH);
		novol.add(this.visor, BorderLayout.CENTER);
		novol.add(botoes, BorderLayout.SOUTH);

		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}

  /**
  Retorna a o IP do usuario.
  @return IP do usuario.
	*/
	public String getIpCliente() {
		return ipCli;
	}

  /**
  Constroi e valida nova instancia String ipCli.
  Atribui um valor para ipCli com a nova instancia criada e informa o erro caso o ipCli for igual a null.
	@param ipCli Armazenar o IP do usuario.
	@throws Exception quando o IP for null.
  */
  public void setIpCliente(String ip) throws Exception{
    if(ip == null)
      throw new Exception("IP invalido");
		ipCli = ip;
	}

  /**
	Trata acoes de clicar em um botao. Verifica qual botao foi selecionado e executa o que lhe foi programado.
	*/
	private class TratadoraDeMouse implements ActionListener {

    /**
    Limpa o visor que sera inserido o IP do cliente.
    */
		private void btnLimpar() {
			visor.setText("");
		}

    /**
    Confirma o IP inserido no visor, armazenando-o na variavel ipCli, verificando se o mesmo nao esta vazio. Em seguida, a janela de login e fechada e a interface principal do programa e aberta, passando o IP inserido como parametro. Caso nao seja inserido um IP, e exibido no campo info que o usuario deve inserir um login e o metodo e finalizado.
    */
		private void btnEnviar() {
			ipCli = visor.getText();
			if(ipCli.length() == 0){
				info.setText("Insira um login");
				return;
			}
			janela.setVisible(false);
			Janela j = new Janela(serv);
			try {
				j.setIp(ipCli);
			} catch (Exception erro) {}
		}

    /**
    Reimplementa o metodo actionPerformed, possibilitando executar os metodos correspondentes aos botoes selecionados. Ao clicar no botao, e verificado qual o texto correspondente que e exibido no visor, levando ao metodo que deve ser executado.
    @param e possui o registro do evento ocorrido, neste caso, a String que identifica o botao
    */
		@Override
		public void actionPerformed(ActionEvent e) {
			String comando = e.getActionCommand();

			if (comando == "Limpar")
				this.btnLimpar();
			else
				this.btnEnviar();
		}
	}
}