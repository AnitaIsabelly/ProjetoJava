/**
A classe Pilha representa a insercao e retirada de elementos (onde o ultimo elemento a ser inserido, sera o primeiro a ser retirado).
As instancias desta classe seguem a implementacao LIFO, ou seja, o elemento a ser retirado sera o ultimo a ser inserido.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Pilha <X> implements Cloneable
{

  /**
  Armazena o Object elemento.
  */
  private Object[] elemento;

  /**
  Armazena o tamanho inicial inteiro da pilha.
  */
  private int tamanhoInicial;

  /**
  Armazena o ultimo elemento inteiro da pilha, inicialmente valendo null.
  */
  private int ultimo=-1;
  
  /**
  Contrutor padrao que atribui o valor para o tamanho inicial e instancia o array elemento com 10 posições.
  */
  public Pilha()
  {
    this.elemento = new Object[10];
    this.tamanhoInicial = 10;
  }
  
  /**
  Construtor que recebe o tamanho da Pilha como parametro para ser instanciado. Primeiro verifica-se se o tamanho inserido e valido e depois o array e tamanho inicial sao instanciados.
  @param tamanho O tamanho que tera a pilha.
  @throws Exception quando o tamanho recebido por parametro for menor que 0.
  */
  public Pilha(int tamanho) throws Exception
  {
    if (tamanho<0)
      throw new Exception ("Tamanho inválido");
    this.elemento = new Object[tamanho];
    this.tamanhoInicial = tamanho;
  }
  
  /**
  Retorna a quantidade de elementos presentes na pilha. Utiliza o indice do ultimo elemente somado a 1 para se obter a quantidade de elementos.
  @return A quantidade de elementos presentes na pilha.
  */
  public int getQuantidade()
  {
    return this.ultimo+1;
  }
  
  /**
  Altera a capacidade da pilha de acordo com o fator passado por parametro. E criado um novo vetor com a capacidade desejada e os elementos sao passados um a um. Por final, a pilha e substituida pelo novo vetor.
  @param fator Numero inteiro pelo qual a capacidade anterior sera multiplicada.
  */
  private void redimensioneSe(float fator)
  {
    Object[] novo = new Object[Math.round(this.elemento.length*fator)];
    for(int i=0; i<=this.ultimo; i++)
      novo[i] = this.elemento[i];
    this.elemento = novo;
  } 
  
  /**
  Retorna o ultimo elemento inserido da pilha. Caso a pilha esteja vazia, e lancado uma excecao, caso contrario, e retornado uma copia do ultimo elemento caso ele seja clonavel ou o proprio elemento se nao for possivel clona-lo.
  @throws Exception caso a pilha esteja vazia.
  @return O ultimo elemento da pilha, podendo ocorrer de duas maneiras:
          <ol>
            <li>
            retorna clone do ultimo elemento caso ele seja clonavel;
            </li>
            <li>
            retorna o proprio elemento caso ele não seja clonavel.
            </li>
          </ol>
  */
  public X recupereUmItem() throws Exception
  {
    if(this.ultimo==-1)
      throw new Exception ("Pilha vazia");
    if(this.elemento[this.ultimo] instanceof Cloneable)
    {
      Clonador<X> clonador = new Clonador<X>();
      return clonador.clone((X)this.elemento[this.ultimo]);
    }
    else
      return (X)this.elemento[this.ultimo];
  }

  /**
  Recebe um elemento para ser empilhado, redimensionando a pilha caso a mesma esteja cheia. Caso o elemento passado por parametro seja null, e lancado uma excecao. Caso o elemento que sera guardado seja clonavel, e feito sua copia, caso contrario, o proprio elemento e guardado.
  @param x O elemento que sera inserido.
  @throws Exception quando o valor de x for null.
  */
  public void guardeUmItem(X x) throws Exception
  {
   if(x==null)
      throw new Exception ("Valor ausente");
    if(this.ultimo+1 == this.elemento.length)
      this.redimensioneSe(2.0F);
    this.ultimo++;
    if(x instanceof Cloneable)
    {
      Clonador<X> clonador = new Clonador<X>();
      this.elemento[this.ultimo] = clonador.clone(x);
    }
    else
      this.elemento[this.ultimo] = x;
  }

/**
Retira um elemento empilhado, redimensionando a pilha caso o tamanho seja de 25% da capacidade total com os desempilhamentos. Caso o elemento passado por parametro seja null, é lançado uma excecao.
@throws Exception quando não houver elemento a ser retirado.
*/
  public void removaUmItem() throws Exception
  {
    if(this.ultimo==-1)
      throw new Exception("Pilha vazia");
    this.elemento[this.ultimo]=null;
    this.ultimo--;
    if(this.elemento.length>this.tamanhoInicial && this.ultimo+1<=Math.round(this.elemento.length*0.25F))
      this.redimensioneSe (0.5F);
  }

  /**
  Verifica se a pilha esta cheia, retorando se o tamanho atual e o mesmo que o determinado.
  @return Boolean que segue a seguinte interpretacao:
          <ol>
            <li>
            true se o tamanho do array for o mesmo que o numero de elementos;
            </li>
            <li>
            false se o tamanho do array for diferente que o numero de elementos.
            </li>
          </ol>
  */
  public boolean isCheia()
  {
    return this.elemento.length==this.ultimo+1;
  }

  /**
  Verifica se a pilha esta vazia, retornando se a ultima posicao e igual a -1.
  @return Boolean que segue a seguinte interpretacao:
          <ol>
            <li>
            true se a ultima posição e igual a -1;
            </li>
            <li>
            false se o a ultima posicao e diferente de -1.
            </li>
          </ol>
  */  
  public boolean isVazia()
  {
    return this.ultimo==-1;
  }

  /**
  Construtor de copia da Pilha. Utiliza uma pilha modelo para se obter seus elementos, o tamanho inicial e indice do ultimo elemento para atribuir ao chamante.
  @param modelo O elemento para ser a copia.
  @throws Exception quando o modelo for nulo.
  */   
  public Pilha(Pilha<X> modelo) throws Exception
  {
    if(modelo==null) throw new Exception("Modelo inválido");
    this.tamanhoInicial = modelo.tamanhoInicial;
    this.ultimo = modelo.ultimo;
    this.elemento=new Object [modelo.elemento.length];
    for(int i=0; i<=modelo.ultimo; i++)
      this.elemento[i] = modelo.elemento[i];  
  }

  /**
  Faz um clone da pilha. Produz e resulta a copia da pilha representada pela instancia a qual o metodo foi aplicado.
  @return A copia da pilha representada pela instancia a qual o metodo for aplicado.
  */   
  public Object clone()
  {
    Pilha<X> ret = null;
    try
    {
      ret = new Pilha<X>(this);
    }
    catch(Exception erro){}
    return ret;
  }
  
  /**
  Reimplementa o metodo toString, retornando a quantidade de elementos e o ultimo que foi inserido. 
  @return A quantidade de elementos e seu ultimo elemento.
  */
  @Override
  public String toString()
  {
    String ret;
    if(this.ultimo==0)
      ret = "1 elemento";
    else
      ret = this.ultimo+1 + " elementos";
    if(this.ultimo!=-1)
      ret += ", sendo o último " + this.elemento[this.ultimo];
    return ret;
  }

  /**
  Verifica a igualdade entre duas pilhas.
  Verifica se o Object fornecido como parametro representa uma pilha igual aquela representada pela instancia a qual este metodo for aplicado, resultando true em caso afirmativo ou false, caso contrario.
  @param obj O objeto a ser comparado com a instancia a qual esse metodo for aplicado.
  @return Boolean que deve ter a seguinte interpretacao:
    <ol>
      <li>
        true, caso o Object fornecido ao metodo e a instancia chamante do metodo representam pilhas iguais;
      </li>
      <li>
        false, caso o Object fornecido ao metodo e a instancia chamante do metodo representam pilhas diferentes.
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

    Pilha<X> pil = (Pilha<X>) obj;

    if(this.ultimo!=pil.ultimo) 
      return false;

    if(this.tamanhoInicial!=pil.tamanhoInicial) 
      return false;

    for(int i=0; i<=this.ultimo; i++)
      if(!this.elemento[i].equals(pil.elemento[i]))
        return false;

    return true;
  }
  
  /**
  Reimplementa o metodo hashCode, calculando-o com base nos hash codes dos atributos. Realiza a multiplicacao de um numero inteiro por um numero primo e soma o numero do hash code dos atributos, retornando o resultado positivo. 
  @return Numero inteiro positivo correspondente ao hash code calculado.
  */
  @Override
  public int hashCode()
  {
    int ret = 123;
    ret = 7*ret + new Integer (this.ultimo).hashCode();
    ret = 7*ret + new Integer (this.tamanhoInicial).hashCode();
    for(int i=0; i<=this.ultimo; i++)
      ret = 7*ret + this.elemento[i].hashCode();
    if(ret<0)
      ret=-ret;
    return ret;
  }
}