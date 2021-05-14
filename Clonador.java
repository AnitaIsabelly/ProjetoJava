package Produtoiii;
import java.lang.reflect.*;

/**
A classe Clonador realiza a invocacao do metodo clone com da instancia passada por parametro. O clone e, entao, retornado para a copia.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Clonador<X>
{

  /**
  Realiza a copia de um objeto a partir da invocacao do metodo clone. Primeiramente, e obtido a classe , parametros formais e reais e metodo que serao utilizados. Depois, o metodo e invocado utilizando a instancia passada por parametro e os parametros reais, ignorando os erros.
  @param x A instancia a qual sera feita o clone.
  @return A copia pela instancia a qual o metodo for aplicado.
  */
  public X clone(X x){
    Class<?> classe = x.getClass();
    Class<?>[] tpsParmsForms = null;
    Method metodo = null;
    try
    {
      metodo = classe.getMethod("clone", tpsParmsForms);
    } catch(NoSuchMethodException erro){}
    Object[] parmsReais = null;
    X ret = null;
    try
    {
      ret = (X)metodo.invoke(x, parmsReais);
    }
    catch(InvocationTargetException erro){}
    catch(IllegalAccessException erro2){}
    return ret;
  }
}
