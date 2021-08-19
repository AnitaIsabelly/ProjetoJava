package Servidor;
import java.sql.*;
import Servidor.core.*;

/**
A classe Labirintosbd e responsavel por preparar e executar os comandos SQL e receber os resultados de consulta. 

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Labirintosbd{

  /**
  Metodo que seleciona todos os labirintos cadastrados no IP do cliente, retornando um objeto da classe MeuResultSet. O comando e preparado e o IP do cliente e substituido pelo valor inserido no parametro. Assim que o comando e executado, o resultado e armazenado e retornado.
  @param ipCli IP do cliente que verificara os labirintos cadastrados.
  @throws Exception quando ocorrer erro ao executar o comando.
  */
  public static MeuResultSet getLabirintos(String ipCli) throws Exception{
    MeuResultSet rs = null;
    try{
      String sql = "select * from LABIRINTO where IP_CLIENTE = ?";
      BDSQLServer.COMANDO.prepareStatement(sql);
      BDSQLServer.COMANDO.setString(1, ipCli);
      rs = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
    }catch (SQLException ex) {
      throw new Exception("Erro ao recuperar labirintos");
    }
    return rs;         
  }
    
  /**
  Metodo que inclui o labirinto digitado no banco de dados.
  Apos a validacao do labirinto, e inserido os comandos de INSERT no BDSQLServer com o nome, conteudo, data de criacao, data de alteracao e o ip do cliente. Em seguida o comando e comitado e executado e exibe uma mensagem caso de erro de insercao.
  @param lab DBO do labirinto a ser incluido.
  @throws Exception quando nao for digitado o labirinto ou erro ao inserir o labirinto no banco de dados.
  */
  public static void incluir(Labirintobd lab)throws Exception{
    if(lab == null)
      throw new Exception ("Labirinto nao fornecido");
    try{
      String sql = "insert into LABIRINTO(NOME,CONTEUDO,DATA_CRIACAO,DATA_ALTERACAO,IP_CLIENTE) values(?,?,?,?,?)";
      BDSQLServer.COMANDO.prepareStatement(sql);

      BDSQLServer.COMANDO.setString(1, lab.getNome());
      BDSQLServer.COMANDO.setString(2, lab.getConteudo());
      BDSQLServer.COMANDO.setString(3, lab.getDataCriacao());
      BDSQLServer.COMANDO.setString(4, lab.getDataAlteracao());
      BDSQLServer.COMANDO.setString(5, lab.getIpCliente());

      BDSQLServer.COMANDO.executeUpdate();
      BDSQLServer.COMANDO.commit();
    }catch (SQLException erro){
      BDSQLServer.COMANDO.rollback();
      throw new Exception ("Erro ao inserir labirinto");
    }
  }

  /**
  Metodo que altera o labirinto digitado no banco de dados.
  Apos a validacao do labirinto, e inserido os comandos de UPDATE no BDSQLServer com o nome, conteudo, data de criacao, data de alteracao e o ip do cliente. Em seguida o comando e comitado e executado e exibe uma mensagem caso de erro de atualizacao do labirinto.
  @param lab DBO do labirinto a ser alterado.
  @throws Exception quando nao o labirinto nao estiver cadastrado no sistema ou nao for digitado o labirinto ou erro ao inserir o labirinto no banco de dados.
  */
  public static void alterar(Labirintobd lab) throws Exception{
    if(lab == null)
      throw new Exception ("Labirinto nao fornecido");
    
    if (!cadastrado(lab.getNome()))
      throw new Exception ("Nao cadastrado");

    try {
      String sql = "UPDATE LABIRINTO SET CONTEUDO = ?, DATA_CRIACAO = ?, DATA_ALTERACAO = ?, IP_CLIENTE = ? WHERE NOME = ?";

      BDSQLServer.COMANDO.prepareStatement(sql);

      BDSQLServer.COMANDO.setString(1, lab.getConteudo());
      BDSQLServer.COMANDO.setString(2, lab.getDataCriacao());
      BDSQLServer.COMANDO.setString(3, lab.getDataAlteracao());
      BDSQLServer.COMANDO.setString(4, lab.getIpCliente());
      BDSQLServer.COMANDO.setString(5, lab.getNome());

      BDSQLServer.COMANDO.executeUpdate();
      BDSQLServer.COMANDO.commit();
    }catch (SQLException erro){
      BDSQLServer.COMANDO.rollback();
      throw new Exception ("Erro ao atualizar dados de labirinto");
    }
  }
    
  /**
  Metodo que exclui o labirinto digitado no banco de dados.

  Apos a validacao do labirinto, e inserido os comandos de DELETE no BDSQLServer com o nome, conteudo, data de criacao, data de alteracao e o ip do cliente. Em seguida o comando e comitado e executado e exibe uma mensagem caso de erro de atualizacao do labirinto.
  @param lab DBO do labirinto a ser excluido.
  @throws Exception quando nao o labirinto nao estiver cadastrado no sistema.
  */
  public static void excluir(String nome) throws Exception{
    if (!cadastrado(nome))
      throw new Exception ("Nao cadastrado");

    try{
      String sql = "DELETE from LABIRINTO WHERE NOME = ?";

      BDSQLServer.COMANDO.prepareStatement(sql);

      BDSQLServer.COMANDO.setString(1, nome);

      BDSQLServer.COMANDO.executeUpdate();
      BDSQLServer.COMANDO.commit();
    }catch (SQLException erro){
      BDSQLServer.COMANDO.rollback();
      throw new Exception ("Erro ao excluir labirinto");
    } 
 }

  /**
  Metodo que verifica se o labirinto esta cadastrado no banco de dados.
  Primeiramente, o nome do labirinto e inserido no comando SELECT. Em seguida o comando e comitado e executado e exibe uma mensagem caso de erro ao procurar do labirinto. Por fim, verifica-se se obteve resultado, caso tenha, e retornado true, caso contrario, e retornado false.
  @param nome Nome do labirinto a ser buscado.
  @throws Exception quando nao o labirinto nao estiver cadastrado no sistema.
  */
  public static boolean cadastrado(String nome)throws Exception{
    boolean ret = false;

    try{
      String sql = "SELECT * from LABIRINTO WHERE NOME = ?";

      BDSQLServer.COMANDO.prepareStatement(sql);

      BDSQLServer.COMANDO.setString(1, nome);
      MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
      ret = resultado.first();
    }catch(SQLException erro){
      throw new Exception("Erro ao procurar labirinto");
    }
    return ret;
  }

  /**
  Metodo que retorna o labirinto cadastrado no banco de dados.
  Primeiramente, o nome do labirinto e inserido no comando SELECT. Em seguida o comando e comitado e executado e exibe uma mensagem caso de erro ao procurar do labirinto. Por fim, verifica-se se obteve resultado, caso tenha, o objeto e retornado, caso contrario, e lancada uma excessao.
  @param nome Nome do labirinto a ser selecionado.
  @throws Exception quando nao o labirinto nao estiver cadastrado no sistema ou erro ao procurar labirinto.
  */
  public static Labirintobd getLabirinto(String nome) throws Exception{
    Labirintobd lab = null;

    try{
      String sql = "SELECT * from LABIRINTO where NOME = ?";

      BDSQLServer.COMANDO.prepareStatement(sql);

      BDSQLServer.COMANDO.setString(1, nome);
      MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();

      if(!resultado.first())
        throw new Exception("Labirinto nao cadastrado");

      lab = new Labirintobd(resultado.getString("NOME"), resultado.getString("CONTEUDO"), resultado.getString("DATA_CRIACAO"), resultado.getString("DATA_ALTERACAO"), resultado.getString("IP_CLIENTE"));
    }catch(SQLException erro){
      throw new Exception("Erro ao procurar labirinto");
    }

    return lab;
  }
}