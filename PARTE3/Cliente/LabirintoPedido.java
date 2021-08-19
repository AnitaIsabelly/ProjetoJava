package Cliente;

/**
Guarda um objeto da classe LabirintoResultado, que pode ser passado entre o cliente e servidor. A classe LabirintoPedido herda da classe de Comunicado.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class LabirintoPedido extends Comunicado
{
  /**
  Armazena o LabirintoResultado.
  */
  private LabirintoResultado labirinto;

  /**
  Construtor da classe, que recebe o LabirintoResultado como parametro.
  @param lab Labirinto que vai ser armazenado.
  */
  public LabirintoPedido (LabirintoResultado lab)
  {
    try{
      labirinto = new LabirintoResultado(lab.getNome(), lab.getDataCriacao(), lab.getDataUltimaAtualizacao(), lab.getConteudo());
    }catch(Exception erro){}
  }

  /**
  Retorna o labirinto que esta guardado no objeto.
  @return clone do labirinto armazenado.
  */
  public LabirintoResultado getLabirinto(){
    return (LabirintoResultado)labirinto.clone();
  }

  /**
  Permite alterar o conteudo do LabirintoPedido, que e recebido como parametro.
  */
  public void setLabirinto(LabirintoResultado lab){
    this.labirinto = (LabirintoResultado)lab.clone();
  }

  /**
  Reimplementa o metodo toString, retornando o conteudo do labirinto em uma unica string.
  @return String contendo o conteudo labirinto.
  */
  @Override
  public String toString()
  {
    return (this.labirinto.toString());
  }

  /**
  Reimplementa o metodo equals, comparando tanto o endereco quanto o valor dos atributos. Para tal, compara-se o endereco do chamante com o atributo, depois verifica se o obj e null e por fim verifica se sao da mesma classe, para realizar a revelacao de tipo e comparar os atributos.
  @param obj LabirintoPedido que sera comparado com o chamante.
  @return Boolean que deve ter a seguinte interpretacao:
          <ol>
            <li>
            true se possuir os mesmos valores do LabirintoResultado ou mesmo enderecamento;
            </li>
            <li>
            false se possuirem valores ou classes diferentes ou quando obj for null.
            </li>
          </ol>
  */
  @Override
  public boolean equals(Object obj){
    if(this == obj) return true;
    if(obj == null) return false;
    if(obj.getClass()!=LabirintoPedido.class) return false;
    LabirintoPedido lab = (LabirintoPedido)obj;
    if(!this.labirinto.equals(lab.labirinto)) return false;
    return true;
  }

  /**
  Reimplementa o metodo hashCode, calculando-o com base nos hash codes dos atributos. Realiza a multiplicacao de um numero inteiro por um numero primo e soma o numero do hash code dos atributos, retornando o resultado positivo. 
  @return Numero inteiro positivo correspondente ao hash code calculado.
  */
  @Override
  public int hashCode(){
    int ret=666;
    ret = 13*ret + (this.labirinto).hashCode();

    if (ret<0){
      ret=-ret;
    }
    return ret;
  }
}