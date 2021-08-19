package Cliente;
import java.net.*;

/**
A classe Tratadora De Comunicado De Desligamento e uma thread responsavel por verificar se algum ComunicadoDeDesligamento foi transmitido, abortando o programa caso ocorra.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class TratadoraDeComunicadoDeDesligamento extends Thread
{
    /**
    Armazena a comunicacao com o servidor.
    */
    private Parceiro servidor;

    /**
    Construtor da classe, que recebe como parametro a comunicacao com o servidor. Verifica se o Parceiro passado e null, caso seja, lanca uma excessao.
    @param servidor A conexao com o servidor.
    @throws Exception quando o Parceiro passado for null.
    */
    public TratadoraDeComunicadoDeDesligamento (Parceiro servidor) throws Exception
    {
        if (servidor==null)
            throw new Exception ("Porta invalida");

        this.servidor = servidor;
    }

    /**
    Metodo que sera rodado na thread, que fica constantemente verificando o comunicado 
		enviado pelo servidor. Caso o comunicado enviado seja do tipo ComunicadoDeDesligamento, 
		o cliente e avisado e o programa e abortado.
    */
    public void run ()
    {
      for(;;)
      {
        try
        {
          if (this.servidor.espie() instanceof ComunicadoDeDesligamento)
          {
            System.out.println ("\nO servidor vai ser desligado agora;");
            System.err.println ("volte mais tarde!\n");
            System.exit(0);
          }
        }
        catch (Exception erro)
        {}
      }
    }
}
