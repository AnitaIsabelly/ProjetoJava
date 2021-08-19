package Servidor;

/**
A classe Pedido Labirintos armazena o ID do cliente que deseja verificar todos os labirintos salvos no servidor.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class PedidoLabirintos extends Comunicado{

  /**
  Armazena o ID do cliente.
  */
  private String idCliente;

  /**
  Construtor da classe, que recebe como parametro o ID do cliente.
  @param idCliente O ID do cliente.
  */
  public PedidoLabirintos (String idCliente){
    this.idCliente = idCliente;
  }

  /**
  Retorna o ID do cliente que esta armazenado.
  @return O ID do cliente.
  */
  public String getIdCliente (){
    return this.idCliente;
  }

  /**
  Constroi nova istancia para a String idCliente.
  @param id O novo ID do cliente.
  */
  public void setIdCliente (String id){
    this.idCliente = id;
  }

  /**
  Reimplementa o metodo toString, retornando o ID do cliente em uma string.
  @return String contendo o ID do cliente.
  */
  @Override
  public String toString (){
    return (this.idCliente);
  }

  /**
  Reimplementa o metodo equals, comparando tanto o endereco quanto o valor dos atributos. Para tal, compara-se o endereco do chamante com o atributo, depois verifica se o obj e null e por fim verifica se sao da mesma classe, para realizar a revelacao de tipo e comparar os atributos.
  @param obj PedidoLabirintos que sera comparado com o chamante.
  @return Boolean que deve ter a seguinte interpretacao:
          <ol>
            <li>
            true se possuir os mesmos valores de ID ou mesmo enderecamento;
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
    if(obj.getClass()!=PedidoLabirinto.class) return false;
    PedidoLabirintos pedido = (PedidoLabirintos)obj;
    if(this.idCliente != pedido.idCliente) return false;
    return true;
  }

  /**
  Reimplementa o metodo hashCode, calculando-o com base nos hash codes dos atributos. Realiza a multiplicacao de um numero inteiro por um numero primo e soma o numero do hash code dos atributos, retornando o resultado positivo. 
  @return Numero inteiro positivo correspondente ao hash code calculado.
  */
  @Override
  public int hashCode(){
    int ret=666;
    ret = 13*ret + (this.idCliente).hashCode();

    if (ret<0){
      ret=-ret;
    }
    return ret;
  }

  /**
  Construtor de copia do PedidoLabirintos. Utiliza um pedido modelo inserido para se obter o seu valore de idCliente e atribuir aos valores do chamante.
  @param modelo O pedido que sera copiado.
  @throws Exception quando o modelo inserido for null.
  */
  public PedidoLabirintos(PedidoLabirintos modelo)throws Exception{
    if (modelo == null){
      throw new Exception("Modelo inválido");
    }
    this.idCliente = modelo.idCliente;
  }

  /**
  Faz um clone do PedidoLabirinto.
  Produz e resulta a copia do pedido representada pela instancia a qual o metodo foi aplicado.
  @return A copia do pedido representada pela instancia a qual o metodo for aplicado.
  */
  public Object clone(){
    PedidoLabirintos ret = null;
    try{
      ret = new PedidoLabirintos(this);
    }catch (Exception erro){}
    return ret;
  }
}