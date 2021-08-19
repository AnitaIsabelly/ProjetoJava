package Cliente;
import java.io.*;

/**
 A classe Comunicado e a raiz das outras classes, sendo ela uma classe oca que possui de heranca a interface Serializable e Cloneable, na qual permite com que outras classes possam gerar objetos que transmitam por sockets e gravar arquivos em banco de dados.
 
 @author Anita Isabelly Gabionetta de Souza
 @author Giulia Brocchi
 @author Nicole Conti Bertin
 @since 2021
 */
public class Comunicado implements Serializable, Cloneable {}