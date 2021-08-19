package Servidor;
import java.net.*;
import java.util.*;

/**
A classe Aceitadora De Conexao e uma thread responsavel por aceitar as conexoes com os usuarios e instanciar um Array de todos os parceiros conectados.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class AceitadoraDeConexao extends Thread
{
  /**
  Armazena o pedido de conexao a ser aceito.
  */
  private ServerSocket pedido;
  /**
  Armazena o Array contendo todos os usuarios conectados.
  */
  private ArrayList<Parceiro> usuarios;

  /**
  Construtor padrao da classe, indicando a porta que sera utilizada e instanciando a lista de usuarios. O metodo verifica a porta inserida, instanciando um novo ServerSocket e verifica se o array e null, caso seja, e lancado uma excessao.
  @param porta Porta a ser utilizada pelo servidor.
  @param usuarios Array contendo todos os usuario conectados.
  @throws Exception quando a porta ou array estiver ausente ou porta for invalida.
  */
  public AceitadoraDeConexao(String porta, ArrayList<Parceiro> usuarios)throws Exception
  {
    if (porta==null)
      throw new Exception ("Porta ausente");

    try
    {
      this.pedido = new ServerSocket (Integer.parseInt(porta));
    }
    catch (Exception  erro)
    {
      throw new Exception ("Porta invalida");
    }

    if (usuarios==null)
      throw new Exception ("Usuarios ausentes");
    
    this.usuarios = usuarios;
  }

  /**
  Metodo que sera rodado na thread, responsavel por aceitar o pedido de conexao e adicionar o usuario a SupervisoraDeConexao. Isso fara com que o cliente consiga enviar os pedidos ao servidor e este os tratara.
  */
  public void run ()
  {
    for(;;)
    {
      Socket conexao=null;
      try
      {
        conexao = this.pedido.accept();
      }
      catch (Exception erro)
      {
        continue;
      }

      SupervisoraDeConexao supervisoraDeConexao=null;
      try
      {
        supervisoraDeConexao = new SupervisoraDeConexao (conexao, usuarios);
      }
      catch (Exception erro)
      {}
      supervisoraDeConexao.start();
    }
  }
}
