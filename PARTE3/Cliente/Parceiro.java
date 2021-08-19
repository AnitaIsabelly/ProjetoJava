package Cliente;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Semaphore;

/**
A classe Parceiro estabelece uma conexao entre os programas cliente e servidor atraves de socket, possuindo um semaforo com apenas um recurso, que permite enviar, receber e visualizar um comunicado ou desfazer a conexao.

@author Anita Isabelly Gabionetta de Souza
@author Giulia Brocchi
@author Nicole Conti Bertin
@since  2021
*/
public class Parceiro
{

	/**
	Armazena uma conexão socket.
	*/
    private Socket conexao;

	/**
	Armazena um recebtor do tipo ObjectInputStream associado ao socket.
	*/	
    private ObjectInputStream  receptor;

	/**
	Armazena um transmissor do tipo ObjectOutputStream associado ao socket.
	*/	
    private ObjectOutputStream transmissor;
    
	/**
	Objeto proximoComunicado com o valor nulo do classe Comunicado.
	*/
    private Comunicado proximoComunicado=null;

  /**
  Objeto instanciado de mutEx que dispoe de 1 recurso.
  */
    private Semaphore mutEx = new Semaphore (1,true);

/**
Construtor da classe, que recebe como parametro o socket de conexao, o receptor, transmissor. Serve para fazer a comunicação de cliente - servidor.  recebe os parametros e faz validação, se não for nulo, os parametros são armazenados nos atributos.

  @param Socket conexao.
  @param ObjectInputStream receptor associado ao socket no qual recebe dados.
  @param ObjectOutputStream transmissor espia qual o proximo comunicado sem tirar o comunicado do parceiro.
  @throws Exception quando a conexao, receptor ou o transmissor forem null.
*/
    public Parceiro (Socket conexao, ObjectInputStream  receptor, ObjectOutputStream transmissor)throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (receptor==null)
            throw new Exception ("Receptor ausente");

        if (transmissor==null)
            throw new Exception ("Transmissor ausente");

        this.conexao = conexao;
        this.receptor = receptor;
        this.transmissor = transmissor;
    }

/**
Transmite um comunicado inserido no parametro para o parceiro. Lanca excessao caso haja erro de comunicacao.
@param x O comunicado a ser enviado.
@throws Exception quando ha erro de transmissao.
*/
    public void receba (Comunicado x) throws Exception
    {
        try
        {
            this.transmissor.writeObject (x);
            this.transmissor.flush();
        }
        catch (IOException erro)
        {
            throw new Exception ("Erro de transmissao");
        }
    }
/**
Espia o que foi enviado para o parceiro sem receber informacoes. Lanca uma excessao caso tenha erro de recepcao.
@throws Exception quando ocorrer erro de recepcao.
@return o proximo comunicado que sera espiado.
*/
    public Comunicado espie () throws Exception
    {
        try
        {
            this.mutEx.acquireUninterruptibly();
            if (this.proximoComunicado==null)
              this.proximoComunicado = (Comunicado)this.receptor.readObject();
            this.mutEx.release();
            return this.proximoComunicado;
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
    }
/**
Recebe um comunicado do parceiro. Instancia o proximoComunicado com o objeto lido e o retorna.
@return O comunicado recebido.
@throws Exception quando ha erro de recepcao.
*/
    public Comunicado envie () throws Exception
    {
        try
        {
            if (this.proximoComunicado==null)
              this.proximoComunicado = (Comunicado)this.receptor.readObject();
            Comunicado ret = this.proximoComunicado;
            this.proximoComunicado = null;
            return ret;
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
    }
/**
Realiza a desconexao da classe parceiro. Fecha as conexoes do transmissor, receptor e socket.
@throws Exception caso ocorra erro de desconexao.
*/
    public void adeus () throws Exception
    {
        try
        {
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de desconexao");
        }
    }
}
