package Servidor;
import Servidor.core.*;

/**
A classe BDSQL Server estabele a conexao do banco de dados com o servidor.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class BDSQLServer
{
	/**
	Estabelece a conexao do banco de dados com o Servidor. Define o comando como nulo e verifica se foi possivel realizar a conexao. Caso contrario, uma excessao e lancada e o programa e abortado.
	*/
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;
    	try
        {
            comando = new MeuPreparedStatement (
                      "oracle.jdbc.driver.OracleDriver",
                      "jdbc:oracle:thin:@localhost:1521:xe",
                      "system", "1234");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0);
        }
        COMANDO = comando;
    }
}
