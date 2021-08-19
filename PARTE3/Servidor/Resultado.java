package Servidor;
import java.util.*;

/**
A classe Resultado possui um array de LabirintoResultado, que armazena os labirintos encontrados.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Resultado extends Comunicado
{
  /**
  Array que armazena todos os labirintos pedidos.
  */
  private ArrayList<LabirintoResultado> labirintos;

  /**
  Construtor padrao da classe, que instancia um novo array.
  */
  public Resultado ()
  {
    labirintos = new ArrayList<LabirintoResultado>();
  }

  /**
  Adiciona um novo LabirintoResultado ao array.
  @param novo O labirinto a ser adicionado na lista.
  */
  public void addLabirinto(LabirintoResultado novo)
  {
    labirintos.add((LabirintoResultado)novo.clone());
  }

  /**
  Retorna a quantidade de elementos na lista.
  @return Numero double que representa a quantidade de elementos na lista.
  */
  public double getQtd ()
  {
    return labirintos.size();
  }

  /**
  Retorna o labirinto presente na posicao solicitada no parametro.
  @param i A posicao do LabirintoResultado a ser retornado.
  @return O LabirintoResultado presente na posicao.
  */
  public LabirintoResultado getLabirinto(int i)
  {
    return labirintos.get(i);
  }
}