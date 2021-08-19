package Cliente;

/**
Armazena os dados do labirinto que vao ser transmitidos entre cliente e servidor. Extende a classe Comunicado. Consegue armazenar o nome, data de criacao, data de ultima alteracao e conteudo do labirinto.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class LabirintoResultado extends Comunicado
{
  /**
  Armazena o nome do labirinto.
  */
  private String nome;
  /**
  Armazena a data de criacao do labirinto.
  */
  private String dataCriacao;
  /**
  Armazena a data de ultima atualizacao do labirinto.
  */
  private String dataUltimaAtualizacao;
  /**
  Armazena o conteudo do labirinto.
  */
  private String conteudo;

  /**
  Construtor da classe, que recebe como parametro o nome, data de criacao, data de ultima atualizacao e conteudo do labirinto. Verifica se os campos nome, data criacao e labirinto sao vazios, lancando uma excecao caso forem.
  @param nom Nome do labirinto.
  @param dtCri Data de criacao do labirinto.
  @param dtUltAtu Data de ultima atualizacao do labirinto.
  @param lab Conteudo do labirinto.
  @throws Exception quando nome, data criacao ou labirinto inseridos forem null.
  */
  public LabirintoResultado (String nom, String dtCri, String dtUltAtu, String lab) throws Exception{
    if(nom == null || dtCri == null || lab == null)
      throw new Exception ("LabirintoResultado invalido");
    this.nome = nom;
    this.dataCriacao = dtCri;
    this.dataUltimaAtualizacao = dtUltAtu;
    this.conteudo = lab;
  }

  /**
  Constroi nova instancia String nome.
  Atribui um valor para nome com a string passada por parametro.
	@param nome Novo nome do labirinto.
  */
  public void setNome(String nome)
  {
    this.nome = nome;
  }

  /**
  Constroi nova instancia String dataCriacao.
  Atribui um valor para a dataCriacao com a string passada por parametro.
	@param data Nova data de criacao do labirinto.
  */
  public void setDataCriacao(String data)
  {
    this.dataCriacao = data;
  }

  /**
  Constroi nova instancia String dataUltimaAtualizacao.
  Atribui um valor para dataUltimaAtualizacao com a string passada por parametro.
	@param data Nova data de ultima atualizacao do labirinto.
  */
  public void setDataAlteracao(String data)
  {
    this.dataUltimaAtualizacao = data;
  }

  /**
  Constroi nova instancia String conteudo.
  Atribui um valor para conteudo com a string passada por parametro.
	@param lab Novo conteudo do labirinto.
  */
  public void setConteudo(String lab)
  {
    this.conteudo = lab;
  }

  /**
  Retorna o conteudo do labirinto.
  @return Conteudo do labirinto.
  */
  public String getConteudo ()
  {
    return this.conteudo;
  }

  /**
  Retorna o nome do labirinto.
  @return Nome do labirinto.
  */
  public String getNome()
  {
    return this.nome;
  }

  /**
  Retorna a data de criacao do labirinto.
  @return Data de criacao do labirinto.
  */
  public String getDataCriacao()
  {
    return this.dataCriacao;
  }

  /**
  Retorna a data de ultima atualizacao do labirinto.
  @return Data de ultima atualizacao do labirinto.
  */
  public String getDataUltimaAtualizacao()
  {
    return this.dataUltimaAtualizacao;
  }

  /**
  Construtor de copia do LabirintoResultado. Utiliza um labirinto modelo inserido para se obter os seus valores de nome, dataCriacao, dataUltimaAtualizacao e conteudo e atribuir aos valores do chamante.
  @param modelo O labirinto que sera copiado.
  @throws Exception quando o modelo inserido for null.
  */
  public LabirintoResultado(LabirintoResultado modelo)throws Exception{
    if (modelo == null){
      throw new Exception("Modelo inválido");
    }
    this.nome = modelo.nome;
    this.dataCriacao = modelo.dataCriacao;
    this.dataUltimaAtualizacao = modelo.dataUltimaAtualizacao;
    this.conteudo = modelo.conteudo;
  }

  /**
  Faz um clone do labirinto.
  Produz e resulta a copia do labirinto representada pela instancia a qual o metodo foi aplicado.
  @return A copia do labirinto representada pela instancia a qual o metodo for aplicado.
  */
  public Object clone(){
    LabirintoResultado ret = null;
    try{
      ret = new LabirintoResultado(this);
    }catch (Exception erro){}
    return ret;
  }

  /**
  Reimplementa o metodo toString, retornando o nome, data de criacao e ultima atualizacao e conteudo em uma mesma string.
  @return String contendo todos os atributos do labirinto.
  */
  @Override
  public String toString()
  {
    return ("Nome: " + this.nome + "\tCriacao: " + this.dataCriacao + "\tUltima alteracao: " + this.dataUltimaAtualizacao + "\nConteudo : \n" + this.conteudo);
  }

  /**
  Reimplementa o metodo equals, comparando tanto o endereco quanto o valor dos atributos. Para tal, compara-se o endereco do chamante com o atributo, depois verifica se o obj e null e por fim verifica se sao da mesma classe, para realizar a revelacao de tipo e comparar os atributos.
  @param obj LabirintoResultado que sera comparado com o chamante.
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
    if(this == obj) return true;
    if(obj == null) return false;
    if(obj.getClass()!=LabirintoResultado.class) return false;
    LabirintoResultado lab = (LabirintoResultado)obj;
    if(this.nome != lab.nome || this.dataCriacao != lab.dataCriacao || this.dataUltimaAtualizacao != lab.dataUltimaAtualizacao || this.conteudo != lab.conteudo) return false;
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
    ret = 3*ret + (this.dataCriacao).hashCode();
    ret = 11*ret + (this.dataUltimaAtualizacao).hashCode();
    ret = 7*ret + (this.conteudo).hashCode();

    if (ret<0){
      ret=-ret;
    }
    return ret;
  }
}