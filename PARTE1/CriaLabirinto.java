import java.io.*;
import java.util.*;

/**
A classe CriaLabirinto le um arquivo texto e cria uma matriz com os caracteres inseridos, a qual e retornada. A classe identifica o numero de linhas inserido no arquivo texto e verifica outras possíveis inconsistencias antes de inserir na matriz.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class CriaLabirinto 
{

  /**
  Le o arquivo texto, verifica sua estrutura e constroi a matriz que sera retornada. Primeiramente, e verificado o numero de linhas inserido e numero de caracteres em uma linha para a declaracao da matriz. Depois, e feita a insercao desses caracteres nas posicoes correspondentes da matriz e verificando se o tamanho corresponde as das demais linhas.
  @throws Exception quando nao e encontrado o numero de linhas,
                    ou quando o tamanho das linhas e inconsistente, 
                    ou quando o arquivo nao e encontrado.
  @return a matriz criada a partir do arquivo texto.
  */
  public char[][] labirinto() throws Exception {
    BufferedReader in = null;
    char[][] matriz = null;
    try
    {
      in = new BufferedReader(new FileReader("teste.txt"));
      String str;
      str = in.readLine();
      int N = 0;
      try{
        N = Integer.parseInt(str);
      }catch(NumberFormatException erro2){
        throw new Exception ("\n\nNúmero de linhas ausente");
      }
      int M = 0;
      in.mark(0);
      str = in.readLine();
      M = str.length();
      in.reset();
      matriz = new char[N][M];
      str = in.readLine();
      for(int i=0; i<N; i++)
      {
        if(str.length() != M)
          throw new Exception ("\n\nTamanho de linhas inconsistente");
        for(int j=0; j<M; j++)
        {
          matriz[i][j] = str.charAt(j);
        }
        str = in.readLine();
      }
      in.close();
    }
    catch(FileNotFoundException erro1){
      throw new Exception("\n\nArquivo nao encontrado");
    }
    catch (IOException erro2){}
    return matriz;
  }
}
