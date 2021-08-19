package Servidor;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;
import Servidor.core.*;

/**
A classe SupervisoraDeConexao e uma thread responsavel pela verificacao da conexao com o cliente e fornecer acesso ao banco de dados. Neste classe sao tratados os pedidos do cliente e retornados os resultados esperados.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since 2021
*/
public class SupervisoraDeConexao extends Thread {
  /**
  Armazena o usuario que enviara os pedidos e recebera as respostas.
  */
  private Parceiro usuario;

  /**
  Armazena o socket que realiza a conexao.
  */
  private Socket conexao;

  /**
  Armazena a lista de usuario conectados.
  */
  private ArrayList<Parceiro> usuarios;

  /**
  Construtor da classe, responsavel por instanciar os objetos. Recebe por parametro o Socket da conexao e o Array de usuarios, verificando se sao null. Caso sejam, e lancada uma excessao.
  @param conexao Socket de conexao com o cliente.
  @param usuarios Array que contem todos os usuarios conectados ao servidor.
  @thows Exception quando conexao esta ausente, ou usuario esta ausente.
  */
  public SupervisoraDeConexao(Socket conexao, ArrayList<Parceiro> usuarios) throws Exception {
    if (conexao == null)
      throw new Exception("Conexao ausente");

    if (usuarios == null)
      throw new Exception("Usuarios ausentes");

    this.conexao = conexao;
    this.usuarios = usuarios;
  }

  /**
  Metodo que sera rodado na thread, recebendo as instancias de comunicado e realizando o que e necessario. Primeiramente, o transmissor e receptor sao instanciados, caso ocorra algum erro, a conexao e fechada e o metodo finalizado. Caso contrario, a conexao e feita e o usuario adicionado ao Array de usuarios. Por fim, o metodo fica em um loop recebendo comunicados e verificando a que classe pertencem. Os comunicados podem ser de 4 classes:
  <ol>
    <li>
    PedidoDeSalvamento : Recebe uma instancia com nome, conteudo e IP do labirinto e do cliente, assim instanciando a data com a data atual do sistema e incluindo-a no banco de dados atraves do DAO. Caso o labirinto ja esteja cadastrado, o conteudo dele e alterado para o novo e a data de ultima atualizacao e atualizada.
    </li>
    <li>
    PedidoLabirintos : Recebe uma instancia com o IP do cliente e acessa o banco de dados atraves do DAO para selecionar todos sos labirintos correspondentes ao IP, recebendo um MeuResultSet, que e passado para uma lista de LabirintoResultado e retornado ao cliente.
    </li>
    <li> 
    PedidoLabirinto : Recebe uma instancia com o nome do labirinto a ser procurado atraves do DAO. Posteriormente, caso encontrado, e instanciado um LabirintoResultado com o conteudo encontrado e enviado ao cliente. Caso nao seja encontrado, e enviado uma instancia com os campos vazios.
    </li>
    <li>
    PedidoParaSair : Faz o uso sincronizado da Array de usuarios para retira-lo do mesmo, desfazendo a conexao com o servidor.
    </li>
  </ol>
  */
  public void run() {
    ObjectOutputStream transmissor;
    try {
      transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
    } catch (Exception erro) {
      return;
    }
    ObjectInputStream receptor = null;
    try {
      receptor = new ObjectInputStream(this.conexao.getInputStream());
    } catch (Exception erro) {
      try {
        transmissor.close();
      } catch (Exception falha) {
      }
      return;
    }
    try {
      this.usuario = new Parceiro(this.conexao, receptor, transmissor);
    } catch (Exception erro) {}
    try {
      synchronized (this.usuarios) {
        this.usuarios.add(this.usuario);
      }
      for (;;) {
        Comunicado comunicado = this.usuario.envie();

        if (comunicado == null)
          return;
        else if (comunicado instanceof PedidoDeSalvamento) {
          PedidoDeSalvamento pedido = (PedidoDeSalvamento) comunicado;
          String nome = pedido.getNome();
          String conteudo = pedido.getConteudo();
          String ip = pedido.getIp();
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
          Date data = new Date();
          String dataCri = formatter.format(data);

          Labirintobd labSalvar = new Labirintobd(nome, conteudo, dataCri, null, ip);
          if (!Labirintosbd.cadastrado(nome)) {
            Labirintosbd.incluir(labSalvar);
          }
          else {
            try {
              Labirintobd cadastrado = Labirintosbd.getLabirinto(nome);
              labSalvar.setDataCriacao(cadastrado.getDataCriacao());
              labSalvar.setDataAlteracao(dataCri);
              Labirintosbd.alterar(labSalvar);
            } catch (Exception erro) {
              throw new Exception(erro.getMessage());
            }
          }
          // desconectar o usuario
        } 
        else if (comunicado instanceof PedidoLabirintos) {
          PedidoLabirintos pedido = (PedidoLabirintos) comunicado;
          String ip = pedido.getIdCliente();
          try {
            MeuResultSet rs = Labirintosbd.getLabirintos(ip);
            Resultado lista = new Resultado();
            if (!rs.first()){
              this.usuario.receba(lista);
              return;
		        }
            LabirintoResultado lab;
            while (!rs.isAfterLast()) {
              lab = new LabirintoResultado(rs.getString("NOME"), rs.getString("DATA_CRIACAO"), rs.getString("DATA_ALTERACAO"), rs.getString("CONTEUDO"));
              lista.addLabirinto(lab);
              rs.next();
            }
            this.usuario.receba(lista);
          } catch (Exception erro) {
            throw new Exception(erro.getMessage());
          }
          // desconecta o usuario
        }
        else if (comunicado instanceof PedidoLabirinto) {
          PedidoLabirinto pedido = (PedidoLabirinto) comunicado;
          String nome = pedido.getNome();
          try {
            Labirintobd lab = Labirintosbd.getLabirinto(nome);
            LabirintoResultado labirinto = new LabirintoResultado(lab.getNome(), lab.getDataCriacao(), lab.getDataAlteracao(), lab.getConteudo());
            LabirintoPedido labPedido = new LabirintoPedido(labirinto);
            this.usuario.receba(labPedido);
          } catch (Exception erro) {
            LabirintoPedido lab = new LabirintoPedido (new LabirintoResultado("", "", "", ""));
            this.usuario.receba(lab);
          }
        }
        else if (comunicado instanceof PedidoParaSair){
          synchronized(this.usuarios)
          {
            this.usuarios.remove(this.usuario);
          }
          this.usuario.adeus();
		    }
      }
    } catch (Exception erro) {
      try {
        transmissor.close();
        receptor.close();
      } catch (Exception falha) {
      }
      return;
    }
  }
}