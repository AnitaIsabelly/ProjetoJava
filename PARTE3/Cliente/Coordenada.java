package Cliente;

/**
A classe Coordenada representa as coordenadas do labirinto tendo como base X e Y, que representam respectivamente linhas e colunas.
As instancias desta classe permitem a obtencao das coordenadas X e Y e suas validacoes.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Coordenada implements Cloneable
{

  /**
  Mantem armazenado x e y da coordenada.
  */
  private byte x, y;

  /**
  Contrutor padrao atribui o valor 0 para a coordenada x e y.
  */
  public Coordenada(){
    this.x = 0;
    this.y = 0;
  }

  /**
  Constroi e valida novas instancias de coordenadas x e y.
  Atribui um valor para x e y com as novas instancias criadas e os valores inseridos, caso forem maiores que 0.
  @param x A coordenada x do labirinto.
  @param y A coordenada y do labirinto.
  @throws Exception quando as coordenadas x e y forem menores que 0.
  */
  public Coordenada(byte x, byte y) throws Exception{
    if(x<0 || y<0) throw new Exception ("Coordenadas inválidas");
    this.x = x;
    this.y = y;
  }

  /**
  Obtem a localizacao da coordenada X do labirinto.
  Resulta nas linhas do labirinto a qual este metodo for aplicado.
  @return A localizacao da coordenada X.
  */
  public byte getX()  {
    return this.x;
  }

 /**
  Obtem a localizacao da coordenada Y do labirinto.
  Resulta as colunas do labirinto a qual este metodo for aplicado.
  @return A localizacao da coordenada Y.
  */
  public byte getY(){
    return this.y;
  }

  /**
  Permite a modificacao do valor da coordenada X. O metodo verifica se o valor inserido esta no padrao antes de atribuir o novo valor da variavel.
  @param x O novo valor da coordenada x.
  @throws Exception quando o valor de x inserido for menor que 0.
  */
  public void setX(byte x) throws Exception{
    if(x<0) throw new Exception ("Coordenadas inválidas");
    this.x = x;
  }

  /**
  Permite a modificacao do valor da coordenada Y. O metodo verifica se o valor inserido esta no padrao antes de atribuir o novo valor da variavel.
  @param y O novo valor da coordenada y.
  @throws Exception quando o valor de y inserido for menor que 0.
  */
  public void setY(byte y) throws Exception{
    if(y<0) throw new Exception ("Coordenadas inválidas");
    this.y = y;
  }

  /**
  Construtor de copia da coordenada. Utiliza uma coordenada modelo inserida para se obter os seus valores de x e y e atribuir aos valores x e y do chamante.
  @param modelo A coordenada que sera copiada.
  @throws Exception quando o modelo inserido for null.
  */
  public Coordenada(Coordenada modelo)throws Exception{
    if (modelo == null){
      throw new Exception("Modelo inválido");
    }
    this.x = modelo.x;
    this.y = modelo.y;
  }

  /**
  Faz um clone da coordenada.
  Produz e resulta a copia da coordenada representada pela instancia a qual o metodo foi aplicado.
  @return A copia da coordenada representada pela instancia a qual o metodo for aplicado.
  */
  public Object clone(){
    Coordenada ret = null;
    try{
      ret = new Coordenada(this);
    }catch (Exception erro){}
    return ret;
  }

  /**
  Reimplementa o metodo toString, retornando a coordenada em forma de String. Utiliza o formato (x, y) para representacao da coordenada.
  @return Coordenadas na forma (x, y).
  */
  @Override
  public String toString(){
    return ("(" + this.x + "," + this.y + ")");
  }

  /**
  Reimplementa o metodo equals, comparando tanto o endereco quanto o valor dos atributos. Para tal, compara-se o endereco do chamante com o atributo, depois verifica se o obj e null e por fim verifica se sao da mesma classe, para realizar a revelacao de tipo e comparar os atributos.
  @param obj Coordenada que sera comparada com o chamante.
  @return Boolean que deve ter a seguinte interpretacao:
          <ol>
            <li>
            true se possuir os mesmos valores de x e y ou mesmo enderecamento;
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
    if(obj.getClass()!=Coordenada.class) return false;
    Coordenada cord = (Coordenada)obj;
    if(this.x != cord.x || this.y != cord.y) return false;
    return true;
  }

  /**
  Reimplementa o metodo hashCode, calculando-o com base nos hash codes dos atributos. Realiza a multiplicacao de um numero inteiro por um numero primo e soma o numero do hash code dos atributos, retornando o resultado positivo. 
  @return Numero inteiro positivo correspondente ao hash code calculado.
  */
  @Override
  public int hashCode(){
    int ret=666;
    ret = 13*ret + new Byte (this.x).hashCode();
    ret = 3*ret + new Byte (this.y).hashCode();

    if (ret<0){
      ret=-ret;
    }
    return ret;
  }
}