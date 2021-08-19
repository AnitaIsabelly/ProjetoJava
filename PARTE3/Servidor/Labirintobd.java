package Servidor;

/**
A classe Labirintobd e responsavel por instanciar objetos que serao selecionados e mandados ao banco de dados. Ela possui atributos equivalentes as colunas da tabela do banco e os devidos metodos para instancia-los e verifica-los.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Labirintobd implements Cloneable{
    /**
    Armazena o nome do labirinto.
    */
    private String nome;
    /**
    Armazena o conteudo do labirinto.
    */
    private String conteudo;
    /**
    Armazena a data de criacao do labirinto.
    */
    private String dataCriacao;
    /**
    Armazena a data de ultima alteracao do labirinto.
    */
    private String dataAlteracao;
    /**
    Armazena o IP do cliente do labirinto.
    */
    private String ipCliente;
    
    /**
    Contrutor da classe que receba como parametro o nome, conteudo, data de criacao, data de alteracao e IP do cliente. O metodo verifica se os campos inseridos sao null, com excessao da data de ultima alteracao.
    @param nomeL Nome do labirinto.
    @param conteudoL Conteudo do labirinto.
    @param dataCriacaoL Data de criacao do labirinto.
    @param dataAlteracaoL Data de ultima alteracao do labirinto.
    @param ipClienteL IP do cliente.
    @throws Exception quando nome, data de criacao, conteudo ou ip forem null.
    */
    public Labirintobd (String nomeL, String conteudoL, String dataCriacaoL, String dataAlteracaoL, String ipClienteL) throws Exception{
        if(nomeL == null || conteudoL == null || dataCriacaoL == null || ipClienteL == null) throw new Exception ("Insira os dados corretamente");
        this.nome = nomeL;
        this.conteudo = conteudoL;
        this.dataCriacao = dataCriacaoL;
        this.dataAlteracao = dataAlteracaoL;
        this.ipCliente = ipClienteL;
    }

    /**
    Retorna o nome do labirinto.
    @return Nome do labirinto.
    */
    public String getNome(){
        return this.nome;
    }

    /**
    Retorna o conteudo do labirinto.
    @return Conteudo do labirinto.
    */
    public String getConteudo(){
        return this.conteudo;
    }

    /**
    Retorna a data de criacao do labirinto.
    @return Data de criacao do labirinto.
    */
    public String getDataCriacao(){
        return this.dataCriacao;
    }

    /**
    Retorna a data de ultima alteracao do labirinto.
    @return Data de ultima alteracao do labirinto.
    */
    public String getDataAlteracao(){
        return this.dataAlteracao;
    }

    /**
    Retorna o IP do cliente.
    @return IP do cliente.
    */
    public String getIpCliente(){
        return this.ipCliente;
    }
    
    /**
  	Constroi nova instancia String nome.
  	Atribui um valor para nome com a string passada por parametro.
		@param nomeL Novo nome do labirinto.
    */
    public void setNome(String nomeL){
        this.nome = nomeL;
    }

    /**
  	Constroi nova instancia String conteudo.
  	Atribui um valor para conteudo com a string passada por parametro.
		@param conteudoL Novo conteudo do labirinto.
    */
    public void setConteudo(String conteudoL){
        this.conteudo = conteudoL;
    }

    /**
    Constroi nova instancia String dataCriacao.
    Atribui um valor para a dataCriacao com a string passada por parametro.
    @param dataCriacaoL Nova data de criacao do labirinto.
    */
    public void setDataCriacao(String dataCriacaoL){
        this.dataCriacao = dataCriacaoL;
    }

    /**
    Constroi nova instancia String dataAlteracao.
    Atribui um valor para a dataAlteracao com a string passada por parametro.
    @param dataAlteracaoL Nova data de alteracao do labirinto.
    */
    public void setDataAlteracao(String dataAlteracaoL){
        this.dataAlteracao = dataAlteracaoL;
    }
    
    /**
    Constroi e valida nova instancia String ipCliente.
    Atribui um valor para nome com a string passada por parametro.
    @param ipCLiente novo ip do cliente
    */
    public void setIpCliente(String ipClienteL){
        this.ipCliente = ipClienteL;
    }
		
    /**
    Reimplementa o metodo toString, retornando o nome, conteudo, data de criacao e ultima atualizacao e o ip do cliente em uma mesma string.
    @return String contendo todos os atributos acima listados.
    */
    @Override
    public String toString(){
      String ret = "";
      ret += "Nome: " + this.nome + "\n";
      ret += "Conteudo: " + this.conteudo + "\n";
      ret += "Data de criacao: " + this.dataCriacao + "\n";
      ret += "Data de alteracao: " + this.dataAlteracao + "\n";
      ret += "Cliente: " + this.ipCliente;
      return ret;
    }

    /**
    Reimplementa o metodo equals, comparando tanto o endereco quanto o valor dos atributos. Para tal, compara-se o endereco do chamante com o atributo, depois verifica se o obj e null e por fim verifica se sao da mesma classe, para realizar a revelacao de tipo e comparar os atributos.
    @param obj Labirintobd que sera comparado com o chamante.
    @return Boolean que deve ter a seguinte interpretacao:
      <ol>
        <li>
        true se possuir os mesmos valores de nome, data criacao, data ultima atualizacao e conteudo ou mesmo enderecamento;
        </li>
        <li>
        false se possuirem valores ou classes diferentes ou quando obj for null.
        </li>
      </ol>
    */
    @Override
    public boolean equals(Object obj){
      if(obj == null)
        return false;
      if(obj == this)
        return true;
      if(!(obj instanceof Labirintobd))
        return false;
      
      Labirintobd lab = (Labirintobd)obj;
      if(!this.nome.equals(lab.nome))
        return false;
      if(!this.conteudo.equals(lab.conteudo))
        return false;
      if(!this.dataCriacao.equals(lab.dataCriacao))
        return false;
      if(!this.dataAlteracao.equals(lab.dataAlteracao))
        return false;
      if(this.ipCliente.equals(lab.ipCliente))
        return false;

      return true;
    }

    /**
    Reimplementa o metodo hashCode, calculando-o com base nos hash codes dos atributos. Realiza a multiplicacao de um numero inteiro por um numero primo e soma o numero do hash code dos atributos, retornando o resultado positivo. 
    @return Numero inteiro positivo correspondente ao hash code calculado.
    */
    @Override
    public int hashCode(){
      int ret = 24;
      ret = 5*ret + this.nome.hashCode();
      ret = 7*ret + this.conteudo.hashCode();
      ret = 11*ret + this.dataCriacao.hashCode();
      ret = 13*ret + this.dataAlteracao.hashCode();
      ret = 11*ret + this.ipCliente.hashCode();
      return ret;
    }

     /**
    Construtor de copia do Labirintobd. Utiliza um labirinto modelo inserido para se obter os seus valores de nome, dataCriacao, dataAlteracao, conteudo e ipCliente e atribuir aos valores do chamante.
    @param modelo O labirinto que sera copiado.
    @throws Exception quando o modelo inserido for null.
    */
    public Labirintobd(Labirintobd modelo) throws Exception{
      if(modelo == null)
        throw new Exception("Modelo ausente");
      this.nome = modelo.nome;
      this.conteudo = modelo.conteudo;
      this.dataCriacao = modelo.dataCriacao;
      this.dataAlteracao = modelo.dataAlteracao;
      this.ipCliente = modelo.ipCliente;
    }

    /**
    Faz um clone do labirinto.
    Produz e resulta a copia do labirinto representada pela instancia a qual o metodo foi aplicado.
    @return A copia do labirinto representada pela instancia a qual o metodo for aplicado.
    */
    public Object clone(){
      Labirintobd ret = null;
      try{
        ret = new Labirintobd(this);
      }catch(Exception erro){}
      return ret;
    }
}