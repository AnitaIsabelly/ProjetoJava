package Servidor;
import java.io.*;

/**
Permite que o usuario digite no terminal, recebendo diversos tipos de dados.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since 2021
*/
public class Teclado
{
  /**
  Lê o que foi escrito no terminal.
  */
  private static BufferedReader teclado = new BufferedReader(new InputStreamReader (System.in));

  /**
  Lê caracteres do tipo String.
  @return ret uma String valida.
  */
  public static String getUmString ()
  {
    String ret=null;

    try
    {
      ret = teclado.readLine();
    }
    catch (IOException erro)
    {}

    return ret;
  }

  /**
  Lê caracteres do tipo byte, validando-os.
  @thows Exception indica que o Byte e invalido.
  @return ret um Byte valido.
  */
  public static byte getUmByte () throws Exception
  {
    byte ret=(byte)0;

    try
    {
      ret = Byte.parseByte (teclado.readLine ());
    }
    catch (IOException erro)
    {}
    catch (NumberFormatException erro)
    {
      throw new Exception ("Byte invalido!");
    }

    return ret;
  }
 
  /**
  Lê caracteres do tipo short, validando-os.
  @thows Exception indica que o Short e invalido.
  @return ret um Short valido.
  */
  public static short getUmShort () throws Exception
  {
    short ret=(short)0;

    try
    {
      ret = Short.parseShort(teclado.readLine());
    }
    catch (IOException erro)
    {}
    catch (NumberFormatException erro)
    {
      throw new Exception ("Short invalido!");
    }

    return ret;
  }

  /**
  Lê caracteres do tipo Int, validando-os.
  @thows Exception indica que o Int e invalido.
  @return ret um Int valido.
  */
  public static int getUmInt () throws Exception
  {
    int ret=0;

    try
    {
      ret = Integer.parseInt(teclado.readLine());
    }
    catch (IOException erro)
    {}
    catch (NumberFormatException erro)
    {
      throw new Exception ("Int invalido!");
    }

    return ret;
  }

  /**
  Lê caracteres do tipo Long, validando-os.
  @thows Exception indica que o Long e invalido.
  @return ret um Long valido.
  */
  public static long getUmLong () throws Exception
  {
    long ret=0L;

    try
    {
      ret = Long.parseLong (teclado.readLine ());
    }
    catch (IOException erro)
    {}
    catch (NumberFormatException erro)
    {
      throw new Exception ("Long invalido!");
    }

    return ret;
  }

  /**
  Lê caracteres do tipo Float, validando-os.
  @thows Exception indica que o Float e invalido.
  @return ret um Float valido.
  */
  public static float getUmFloat () throws Exception
  {
    float ret=0.0F;

    try
    {
      ret = Float.parseFloat (teclado.readLine ());
    }
    catch (IOException erro)
    {}
    catch (NumberFormatException erro)
    {
      throw new Exception ("Float invalido!");
    }

    return ret;
  }

  /**
  Lê caracteres do tipo Double, validando-os.
  @thows Exception indica que o Double e invalido.
  @return ret um Double valido.
  */
  public static double getUmDouble () throws Exception
  {
    double ret=0.0;

    try
    {
      ret = Double.parseDouble (teclado.readLine ());
    }
    catch (IOException erro)
    {}
    catch (NumberFormatException erro)
    {
      throw new Exception ("Double invalido!");
    }

    return ret;
  }

  /**
  Lê caracteres do tipo char, validando-os.
  @thows Exception indica que o Char e invalido.
  @return ret um Char valido.
  */
  public static char getUmChar () throws Exception
  {
    char ret=' ';

    try
    {
      String str = teclado.readLine ();

      if (str==null)
        throw new Exception ("Char invalido!");

      if (str.length() != 1)
        throw new Exception ("Char invalido!");

      ret = str.charAt(0);
    }
    catch (IOException erro)
    {}

    return ret;
  }

  /**
  Lê caracteres do tipo boolean, validando-os.
  @thows Exception indica que o Boolean e invalido.
  @return ret um Boolean valido.
  */
  public static boolean getUmBoolean () throws Exception
  {
    boolean ret=false;

    try
    {
      String str = teclado.readLine ();

      if (str==null)
        throw new Exception ("Boolean invalido!");

      if (!str.equals("true") && !str.equals("false"))
        throw new Exception ("Boolean invalido!");

      ret = Boolean.parseBoolean (str);
    }
    catch (IOException erro)
    {}

    return ret;
  }
}
