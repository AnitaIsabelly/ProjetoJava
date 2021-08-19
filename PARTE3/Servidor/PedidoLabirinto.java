package Servidor;

/**
A classe Pedido Labirinto armazena o nome do labirinto que sera selecionado pelo cliente e buscado pelo servidor.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class PedidoLabirinto extends Comunicado{
  
  /**
  Armazena o nome do tipo String.
  */
  private String nome;

  /**
  Construtor da classe, que receba o nome como parametro do tipo String.
  @param nome Nome do labirinto a ser buscado.
  */
  public PedidoLabirinto (String nome){
    this.nome = nome;
  }

  /**
  Retorna o nome do labirinto.
  @return Nome do labirinto.
  */
  public String getNome (){
    return this.nome;
  }

  /**
  Constroi nova instancia String nome.
  Atribui um valor para nome com a string passada por parametro.
  @param nome Novo nome do labirinto.
  */
  public void setNome (String nome){
    this.nome = nome;
  }

  /**
  Reimplementa o metodo toString, retornando o nome do labirinto em uma string.
  @return String contendo o nome do labirinto pedido.
  */
  public String toString (){
    return (this.nome);
  }

  /**
  Reimplementa o metodo equals, comparando tanto o endereco quanto o valor dos atributos. Para tal, compara-se o endereco do chamante com o atributo, depois verifica se o obj e null e por fim verifica se sao da mesma classe, para realizar a revelacao de tipo e comparar os atributos.
  @param obj PedidoLabirinto que sera comparado com o chamante.
  @return Boolean que deve ter a seguinte interpretacao:
          <ol>
            <li>
            true se possuir os mesmos valores de nome ou mesmo enderecamento;
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
    PedidoLabirinto pedido = (PedidoLabirinto)obj;
    if(this.nome != pedido.nome) return false;
    return true;
  }

  /**
  Reimplementa o metodo hashCode, calculando-o com base nos hash codes dos atributos. Realiza a multiplicacao de um numero inteiro por um numero primo e soma o numero do hash code dos atributos, retornando o resultado positivo. 
  @return Numero inteiro positivo correspondente ao hash code calculado.
  */
  @Override
  public int hashCode(){
    int ret=666;
    ret = 13*ret + (this.nome).hashCode();

    if (ret<0){
      ret=-ret;
    }
    return ret;
  }

   /**
  Construtor de copia do PedidoLabirinto. Utiliza um pedido modelo inserido para se obter o seu valore de nome e atribuir aos valores do chamante.
  @param modelo O pedido que sera copiado.
  @throws Exception quando o modelo inserido for null.
  */
  public PedidoLabirinto(PedidoLabirinto modelo)throws Exception{
    if (modelo == null){
      throw new Exception("Modelo inválido");
    }
    this.nome = modelo.nome;
  }

  /**
  Faz um clone do PedidoLabirinto.
  Produz e resulta a copia do pedido representada pela instancia a qual o metodo foi aplicado.
  @return A copia do pedido representada pela instancia a qual o metodo for aplicado.
  */
  public Object clone(){
    PedidoLabirinto ret = null;
    try{
      ret = new PedidoLabirinto(this);
    }catch (Exception erro){}
    return ret;
  }
}