package Servidor;

/**
A classe Pedido De Salvamento contem o nome, conteudo e IP do labirinto a ser salvo pelo cliente, que entao sera recebido pelo servidor.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class PedidoDeSalvamento extends Comunicado
{

	/**
  Armazena o nome do tipo String.
  */
  private String nome;

	/**
  Armazena o conteudo do tipo String.
  */
  private String conteudo;

	/**
  Armazena o ip do tipo String.
  */
  private String ip;
    
	/**
	Construtor da classe, que recebe como parametro o nome, conteudo e IP do tipo string.

  @param nome Nome do labirinto a ser salvo.
  @param conteudo Conteudo do labirinto a ser salvo.
  @param ip IP do cliente que salvara o labirinto.
	*/
    public PedidoDeSalvamento(String nome, String conteudo, String ip){
      this.nome = nome;
      this.conteudo = conteudo;
      this.ip = ip;
    }
    
    /**
    Retorna o nome do labirinto.
    @return Nome do labirinto.
    */
    public String getNome (){
      return this.nome;
    }
    
    /**
    Retorna o conteudo do labirinto.
    @return Conteudo do labirinto.
    */
    public String getConteudo (){
      return this.conteudo;
    }

    /**
    Retorna o IP do cliente.
    @return IP do cliente.
    */
    public String getIp(){
      return this.ip;
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
    Constroi nova instancia String conteudo.
    Atribui um valor para conteudo com a string passada por parametro.
    @param conteudo Novo conteudo do labirinto.
    */
    public void setConteudo (String conteudo){
      this.conteudo = conteudo;
    }

    /**
    Constroi nova instancia String ip.
    Atribui um valor para ip com a string passada por parametro.
    @param ip Novo IP do cliente.
    */
    public void setIp(String ip){
      this.ip = ip;
    }
    
  /**
  Reimplementa o metodo toString, retornando o nome, conteudo e IP em uma mesma string.
  @return String contendo todos os atributos do pedido.
  */
  public String toString (){
    return (""+this.nome+"\n"+this.conteudo+"\n"+this.ip);
  }

  /**
  Reimplementa o metodo equals, comparando tanto o endereco quanto o valor dos atributos. Para tal, compara-se o endereco do chamante com o atributo, depois verifica se o obj e null e por fim verifica se sao da mesma classe, para realizar a revelacao de tipo e comparar os atributos.
  @param obj PedidoDeSalvamento que sera comparado com o chamante.
  @return Boolean que deve ter a seguinte interpretacao:
          <ol>
            <li>
            true se possuir os mesmos valores de nome, conteudo e ip ou mesmo enderecamento;
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
    if(obj.getClass()!=PedidoDeSalvamento.class) return false;
    PedidoDeSalvamento pedido = (PedidoDeSalvamento)obj;
    if(this.nome != pedido.nome || this.ip != pedido.ip || this.conteudo != pedido.conteudo) return false;
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
    ret = 7*ret + (this.conteudo).hashCode();
    ret = 3*ret + (this.ip).hashCode();

    if (ret<0){
      ret=-ret;
    }
    return ret;
  }

  /**
  Construtor de copia do PedidoDeSalvamento. Utiliza um pedido modelo inserido para se obter os seus valores de nome, conteudo e IP e atribuir aos valores do chamante.
  @param modelo O pedido que sera copiado.
  @throws Exception quando o modelo inserido for null.
  */
  public PedidoDeSalvamento(PedidoDeSalvamento modelo)throws Exception{
    if (modelo == null){
      throw new Exception("Modelo inválido");
    }
    this.nome = modelo.nome;
    this.conteudo = modelo.conteudo;
    this.ip = modelo.ip;
  }

  /**
  Faz um clone do PedidoDeSalvamento.
  Produz e resulta a copia do pedido representada pela instancia a qual o metodo foi aplicado.
  @return A copia do pedido representada pela instancia a qual o metodo for aplicado.
  */
  public Object clone(){
    PedidoDeSalvamento ret = null;
    try{
      ret = new PedidoDeSalvamento(this);
    }catch (Exception erro){}
    return ret;
  }
}
