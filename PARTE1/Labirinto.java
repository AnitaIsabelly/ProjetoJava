/**
A classe labirinto faz a verificacao e localizacao de entradas e saidas do labirinto, e tambem de suas paredes. 
Realiza a progrssao e regressao de caminho do labirinto.
Verifica e empilha posicaes adjacentes da posicao atual do labirinto.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Labirinto
{

  /**
  Mantem armazenada a matriz.
  */
  private char matriz[][];
  
  /**
  Mantem armazenado o caminho percorrido da pilha coordenada.
  */
  private Pilha<Coordenada> caminho;

  /**
  Mantem armazenada a coordenada atual.
  */
  private Coordenada atual;

  /**
  Mantem armazenada a coordenada saida.
  */
  private Coordenada saida;

  /**
  Mantem armazenada o caminho adjacente da pilha coordenada.
  */
  private Pilha<Coordenada> adjacente;

  /**
  Mantem armazenada o as possibilidades de caminho da pilha coordenada.
  */
  private Pilha<Pilha<Coordenada>> possibilidades;
  
  /**
  Construtor da classe Labirinto, instanciando tambem os seus atributos. O metodo recebe a matriz do labirinto por parametro, instancia a variavel caminho, adjacente e possibilidades e chama o metodo para encontrar a entrada e saida do labirinto.
  @param labirinto a matriz que contem a estrutura do labirinto.
  @throws Exception quando a matriz passada pelo parametro for null.
  */
  public Labirinto(char labirinto[][]) throws Exception
  {
    if(labirinto==null) throw new Exception("\n\nMatriz ausente");
    this.matriz = labirinto;
    this.caminho = new Pilha<Coordenada> (40);
    this.encontrarEntradaSaida();
    this.adjacente = new Pilha<Coordenada> (3);
    this.possibilidades = new Pilha<Pilha<Coordenada>> (40);
  }

  /**
  Retorna o caminho percorrido no labirinto ate o momento. E feito um clone da variavel caminho para ser retornado.
  @return ret retorna o caminho percorrido.
  */
  public Pilha<Coordenada> getCaminho(){
    Pilha<Coordenada> ret;
    ret = (Pilha<Coordenada>)this.caminho.clone();
    return ret;
  }

  /**
  Verifica os caracteres na primeira e ultima linha e primeira e ultima coluna, a qual representam as paredes externas do labirinto.
  Verifica se existe um espaço em branco nas paredes do labirinto.
  Contabiliza os caracteres para saber se houve mais que uma entrada ou saida e guarda as coordenadas correspondentes a eles.
  @throws Exception se houver espaco nas paredes do labirinto (paredes faltando), 
                    ou se nao encontrar uma entrada,
                    ou se nao encontrar uma saida,
                    ou se tiver mais que uma entrada,
                    ou se tiver mais que uma saida.
  */
  private void encontrarEntradaSaida() throws Exception
  {
    int contadorE=0;
    int contadorS=0;
    int parede=0;
    //VERIFICA LINHA I = X
    try{
      for(int i=0; i<this.matriz.length; i++){
        if(this.matriz[i][0]=='E')
        {
          this.atual = new Coordenada ((byte)i, (byte)0);
          contadorE++;
        }
        else if(this.matriz[i][0]=='S')
        {
          this.saida = new Coordenada ((byte)i, (byte)0);
          contadorS++;
        }
        if (this.matriz[i][this.matriz[0].length-1]=='E')
        {
          this.atual = new Coordenada ((byte)i, (byte)(this.matriz[0].length-1));
          contadorE++;
        }
        else if (this.matriz[i][this.matriz[0].length-1]=='S')
        {
          this.saida = new Coordenada ((byte)i, (byte)(this.matriz[0].length-1));
          contadorS++;
        }
        if(this.matriz[i][0]==' ' || this.matriz[i][this.matriz[0].length-1]==' ')
          parede=1;
      }
      //VERIFICA COLUNA J = Y
      for(int j=0; j<this.matriz[0].length; j++){
        if(this.matriz[0][j]=='E')
        {
          this.atual = new Coordenada ((byte)0, (byte)j);
          contadorE++;
        }
        else if(this.matriz[0][j]=='S')
        {
          this.saida = new Coordenada ((byte)0, (byte)j);
          contadorS++;
        }
        if(this.matriz[this.matriz.length-1][j]=='E')
        {
          this.atual = new Coordenada ((byte)(this.matriz.length-1), (byte)j);
          contadorE++;
        }
        else if(this.matriz[this.matriz.length-1][j]=='S')
        {
          this.saida = new Coordenada ((byte)(this.matriz.length-1), (byte)j);
          contadorS++;
        }
        if(this.matriz[0][j]==' ' || this.matriz[this.matriz.length-1][j]==' ')
          parede=1;
      }
    }
    catch(Exception erro){}
    if(parede==1)
      throw new Exception("\n\nLabirinto com paredes faltando");
    if(this.atual==null)
      throw new Exception("\n\nEntrada inválida");
    if(this.saida==null)
      throw new Exception("\n\nSaída inválida");
    if(contadorE>1)
      throw new Exception("\n\nMultiplas entradas");
    if(contadorS>1)
      throw new Exception("\n\nMultiplas saidas");
  }

  /**
  Metodo que realiza a verificacao de espacos em branco ou o caractere S adjacentes da posicao atual (acima, abaixo, a direita e a esquerda) fazendo o empilhamento das posicoes.
  */
  public void encontrarAdj()
  {
    try{
    Coordenada cima;
    Coordenada baixo;
    Coordenada esquerda;
    Coordenada direita;

    //cima
    if(this.atual.getX()-1>=0)
      if(this.matriz[this.atual.getX()-1][this.atual.getY()] == 'S' || this.matriz[this.atual.getX()-1][this.atual.getY()] == ' '){
        cima = new Coordenada((byte)(this.atual.getX()-1), this.atual.getY());
        this.adjacente.guardeUmItem(cima);
      }

    //baixo
    if(this.atual.getX()+1<this.matriz.length)
      if(this.matriz[this.atual.getX()+1][this.atual.getY()] == 'S' || this.matriz[this.atual.getX()+1][this.atual.getY()] == ' ')
      {
        baixo = new Coordenada((byte)(this.atual.getX()+1), this.atual.getY());
        this.adjacente.guardeUmItem(baixo);
      }
    
    //direita
    if(this.atual.getY()+1<this.matriz[0].length)
      if(this.matriz[this.atual.getX()][this.atual.getY()+1] == 'S' || this.matriz[this.atual.getX()][this.atual.getY()+1] == ' ')
      {
        direita = new Coordenada(this.atual.getX(), (byte)(this.atual.getY()+1));
        this.adjacente.guardeUmItem(direita);
      }

    //esquerda
    if(this.atual.getY()-1>=0)
      if(this.matriz[this.atual.getX()][this.atual.getY()-1] == 'S' || this.matriz[this.atual.getX()][this.atual.getY()-1] == ' '){
        esquerda = new Coordenada(this.atual.getX(), (byte)(this.atual.getY()-1));
        this.adjacente.guardeUmItem(esquerda);
      }
    }
    catch(Exception erro){}
  }

  /**
  Metodo que realiza o caminho progessivo do labirinto com pelo menos um caminho adjacente e no maximo tres. O caminho andado substitui os espacos em branco por asteriscos, e assim guarda o caminho percorrido em uma pilha. Caso nao tenha pelo menos um caminho adjacente, a posicao atual do labirinto tera que ser regredida. Quando o caminho atual for igual ao caractere S que representa a saida, este metodo termina.
  @throws Exception quando nao ha mais caminhos adjacentes na coordenada atual.
  */
  public void andadinha() throws Exception
  {
    do{
      try{
        if(this.adjacente.isVazia())
          this.encontrarAdj();
        
        this.atual = this.adjacente.recupereUmItem();
        if(this.atual.equals(this.saida))
          break;
        this.matriz[this.atual.getX()][this.atual.getY()] = '*';
        this.adjacente.removaUmItem();
        this.caminho.guardeUmItem(this.atual);

        Pilha<Coordenada> teste = new Pilha<Coordenada> (3);
        teste = (Pilha<Coordenada>)this.adjacente.clone();
        this.possibilidades.guardeUmItem(teste);
        
        while(!this.adjacente.isVazia())
          this.adjacente.removaUmItem();
        
      }
      catch(Exception regressiva)
      {
        this.voltadinha();
      }
    } while(!this.atual.equals(this.saida)); 
  }

  /**
  Realiza o caminho de regressao no labirinto quando não se encontram mais caminhos adjacentes. O caminho andado e regredido e os asteriscos sao substituidos novamente por espaços em branco, ate que se encontre alguma possibilidade de caminho alternativo ao ja caminhado. Caso nao houver, informa-se que o labirinto nao possui solucao.
  @throws Exception quando nao sao encontrados caminhos alternativos (labirinto sem solução).
  */
  private void voltadinha() throws Exception
  {
    try{
      do{
        this.matriz[this.atual.getX()][this.atual.getY()] = ' ';
        this.caminho.removaUmItem();

        this.atual = this.caminho.recupereUmItem();
        
        this.adjacente = this.possibilidades.recupereUmItem();
        this.possibilidades.removaUmItem();
        
      }while(this.adjacente.isVazia());
      this.andadinha(); 
    }catch(Exception erro){
      throw new Exception("\n\nLabirinto sem solução!");
    }
  }

  /**
  Reimplementa o metodo toString, retornando o labirinto em seu formato original. Insere os caracteres da matriz a String a ser retornada e pula linha ao final da mesma.
  @return o labirinto em forma de String.
  */
  @Override
  public String toString()
  {
    String ret = "";
    for(int i=0; i<this.matriz.length; i++) {
      for(int j=0; j<this.matriz[0].length; j++){
        ret += matriz[i][j];
      }
      ret += "\n";
    }
    return ret;
  }
  /**
  Verifica a igualdade entre dois labirintos.
  Verifica se o Object fornecido como parametro representa um labirinto igual aquele representado pela instancia a qual este metodo for aplicado, resultando true em caso afirmativo ou false, caso contrario.
  @param obj o objeto a ser comparado com a instancia a qual esse metodo for aplicado
  @return boolean que deve ter a seguinte interpretacao:
        <ol>
          <li>
          true, caso o Object fornecido ao metodo e a instancia chamante do metodo representam labirintos iguais;
          </li>
          <li>
          false, caso o Object fornecido ao metodo e a instancia chamante do metodo representam labirintos diferentes.
          </li>
        </ol>
  */
  @Override
  public boolean equals(Object obj)
  {
    if(this==obj) 
      return true;

    if(obj==null) 
      return false;

    if(this.getClass()!=obj.getClass()) 
      return false;

    Labirinto lab = (Labirinto) obj;

    for(int i=0; i<this.matriz.length; i++)
      for(int j=0; j<this.matriz[0].length; j++)
        if(this.matriz[i][j]!=lab.matriz[i][j])
          return false;

    return true;
  }

  /**
  Reimplementa o metodo hashCode, calculando-o com base nos hash codes dos atributos. Realiza a multiplicacao de um número inteiro por um numero primo e soma o numero do hash code dos atributos, retornando o resultado positivo. 
  @return numero inteiro positivo correspondente ao hash code calculado.
  */
  @Override
  public int hashCode()
  {
    int ret=666, i, j;
    
    for(i=0; i<this.matriz.length; i++) {
      for(j=0; j<this.matriz[0].length; j++){
          ret = 13*ret + new Character (this.matriz[i][j]).hashCode();
      }
    }
    ret = 13*ret + this.caminho.hashCode();
    ret = 13*ret + this.atual.hashCode();
    ret = 13*ret + this.saida.hashCode();
    ret = 13*ret + this.adjacente.hashCode();
    ret = 13*ret + this.possibilidades.hashCode();
    if (ret<0){
      ret=-ret;
    }
    return ret;
  }
}