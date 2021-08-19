package Servidor.core;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;

public class MeuPreparedStatement extends MeuStatement implements PreparedStatement
{
    protected PreparedStatement comando=null;

    public MeuPreparedStatement (String drv, String strCon, String usr, String senha) throws ClassNotFoundException, SQLException
    {
      super (drv, strCon, usr, senha);
    }

    public void prepareStatement (String sql) throws SQLException
    {
        this.comando = conexao.prepareStatement (sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public void commit () throws SQLException
    {
        this.conexao.commit ();
    }

    public void rollback () throws SQLException
    {
        this.conexao.rollback ();
    }

    public void addBatch () throws SQLException
    {
        this.comando.addBatch ();
    }

    public void clearParameters () throws SQLException
    {
        this.comando.clearParameters ();
    }

    public void close () throws SQLException
    {
        for (ResultSet r:this.resultados)
            r.close ();

        for (Statement s:this.comandos)
            s.close ();

        if (this.comando!=null)
            this .comando.close ();

        super.comando.close ();
        super.conexao.close ();
    }

    public boolean execute () throws SQLException
    {
        return this.comando.execute ();
    }

    public ResultSet executeQuery () throws SQLException
    {
        ResultSet resultado = this.comando.executeQuery ();

        MeuResultSet retorno = new MeuResultSet (this.comando, resultado);

        this.resultados.add (retorno);
        this.comandos  .add (this.comando);

        this.comando = null;

        return retorno;
    }

    public int executeUpdate () throws SQLException
    {
        return this.comando.executeUpdate ();
    }

    public ResultSetMetaData getMetaData () throws SQLException
    {
        return this.comando.getMetaData ();
    }

    public ParameterMetaData getParameterMetaData () throws SQLException
    {
        return this.comando.getParameterMetaData ();
    }

    public void setArray (int parameterIndex, Array x) throws SQLException
    {
        this.comando.setArray (parameterIndex, x);
    }

    public void setAsciiStream (int parameterIndex, InputStream x) throws SQLException
    {
        this.comando.setAsciiStream (parameterIndex, x);
    }

    public void setAsciiStream (int parameterIndex, InputStream x, int length) throws SQLException
    {
        this.comando.setAsciiStream (parameterIndex, x, length);
    }

    public void setAsciiStream (int parameterIndex, InputStream x, long length) throws SQLException
    {
        this.comando.setAsciiStream (parameterIndex, x, length);
    }

    public void setBigDecimal (int parameterIndex, BigDecimal x) throws SQLException
    {
        this.comando.setBigDecimal (parameterIndex, x);
    }

    public void setBinaryStream (int parameterIndex, InputStream x) throws SQLException
    {
        this.comando.setBinaryStream (parameterIndex, x);
    }

    public void setBinaryStream (int parameterIndex, InputStream x, int length) throws SQLException
    {
        this.comando.setBinaryStream (parameterIndex, x, length);
    }

    public void setBinaryStream (int parameterIndex, InputStream x, long length) throws SQLException
    {
        this.comando.setBinaryStream (parameterIndex, x, length);
    }

    public void setBlob (int parameterIndex, Blob x) throws SQLException
    {
        this.comando.setBlob (parameterIndex, x);
    }

    public void setBlob (int parameterIndex, InputStream inputStream) throws SQLException
    {
        this.comando.setBlob (parameterIndex, inputStream);
    }

    public void setBlob (int parameterIndex, InputStream inputStream, long length) throws SQLException
    {
        this.comando.setBlob (parameterIndex, inputStream, length);
    }

    public void setBoolean (int parameterIndex, boolean x) throws SQLException
    {
        this.comando.setBoolean (parameterIndex, x);
    }

    public void setByte (int parameterIndex, byte x) throws SQLException
    {
        this.comando.setByte (parameterIndex, x);
    }

    public void setBytes (int parameterIndex, byte[] x) throws SQLException
    {
        this.comando.setBytes (parameterIndex, x);
    }

    public void setCharacterStream (int parameterIndex, Reader reader) throws SQLException
    {
        this.comando.setCharacterStream (parameterIndex, reader);
    }

    public void setCharacterStream (int parameterIndex, Reader reader, int length) throws SQLException
    {
        this.comando.setCharacterStream (parameterIndex, reader, length);
    }

    public void setCharacterStream (int parameterIndex, Reader reader, long length) throws SQLException
    {
        this.comando.setCharacterStream (parameterIndex, reader, length);
    }

    public void setClob (int parameterIndex, Clob x) throws SQLException
    {
        this.comando.setClob (parameterIndex, x);
    }

    public void setClob (int parameterIndex, Reader reader) throws SQLException
    {
        this.comando.setClob (parameterIndex, reader);
    }

    public void setClob (int parameterIndex, Reader reader, long length) throws SQLException
    {
        this.comando.setClob (parameterIndex, reader, length);
    }

    public void setDate (int parameterIndex, Date x) throws SQLException
    {
        this.comando.setDate (parameterIndex, x);
    }

    public void setDate (int parameterIndex, Date x, Calendar cal) throws SQLException
    {
        this.comando.setDate (parameterIndex, x, cal);
    }

    public void setDouble (int parameterIndex, double x) throws SQLException
    {
        this.comando.setDouble (parameterIndex, x);
    }

    public void setFloat (int parameterIndex, float x) throws SQLException
    {
        this.comando.setFloat (parameterIndex, x);
    }

    public void setInt (int parameterIndex, int x) throws SQLException
    {
        this.comando.setInt (parameterIndex, x);
    }

    public void setLong (int parameterIndex, long x) throws SQLException
    {
        this.comando.setLong (parameterIndex, x);
    }

    public void setNCharacterStream (int parameterIndex, Reader value) throws SQLException
    {
        this.comando.setNCharacterStream (parameterIndex, value);
    }

    public void setNCharacterStream (int parameterIndex, Reader value, long length) throws SQLException
    {
        this.comando.setNCharacterStream (parameterIndex, value, length);
    }

    public void setNClob (int parameterIndex, NClob value) throws SQLException
    {
        this.comando.setNClob (parameterIndex, value);
    }

    public void setNClob (int parameterIndex, Reader reader) throws SQLException
    {
        this.comando.setNClob (parameterIndex, reader);
    }

    public void setNClob (int parameterIndex, Reader reader, long length) throws SQLException
    {
        this.comando.setNClob (parameterIndex, reader, length);
    }

    public void setNString (int parameterIndex, String value) throws SQLException
    {
        this.comando.setNString (parameterIndex, value);
    }

    public void setNull (int parameterIndex, int sqlType) throws SQLException
    {
        this.comando.setNull (parameterIndex, sqlType);
    }

    public void setNull (int parameterIndex, int sqlType, String typeName) throws SQLException
    {
        this.comando.setNull (parameterIndex, sqlType, typeName);
    }

    public void setObject (int parameterIndex, Object x) throws SQLException
    {
        this.comando.setObject (parameterIndex, x);
    }

    public void setObject (int parameterIndex, Object x, int targetSqlType) throws SQLException
    {
        this.comando.setObject (parameterIndex, x, targetSqlType);
    }

    public void setObject (int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException
    {
        this.comando.setObject (parameterIndex, x, targetSqlType, scaleOrLength);
    }

    public void setRef (int parameterIndex, Ref x) throws SQLException
    {
        this.comando.setRef (parameterIndex, x);
    }

    public void setRowId (int parameterIndex, RowId x) throws SQLException
    {
        this.comando.setRowId (parameterIndex, x);
    }

    public void setShort (int parameterIndex, short x) throws SQLException
    {
        this.comando.setShort (parameterIndex, x);
    }

    public void setSQLXML (int parameterIndex, SQLXML xmlObject) throws SQLException
    {
        this.comando.setSQLXML (parameterIndex, xmlObject);
    }

    public void setString (int parameterIndex, String x) throws SQLException
    {
        this.comando.setString (parameterIndex, x);
    }

    public void setTime (int parameterIndex, Time x) throws SQLException
    {
        this.comando.setTime (parameterIndex, x);
    }

    public void setTime (int parameterIndex, Time x, Calendar cal) throws SQLException
    {
        this.comando.setTime (parameterIndex, x, cal);
    }

    public void setTimestamp (int parameterIndex, Timestamp x) throws SQLException
    {
        this.comando.setTimestamp (parameterIndex, x);
    }

    public void setTimestamp (int parameterIndex, Timestamp x, Calendar cal) throws SQLException
    {
        this.comando.setTimestamp (parameterIndex, x, cal);
    }

    public void setUnicodeStream (int parameterIndex, InputStream x, int length) throws SQLException
    {
        this.comando.setUnicodeStream (parameterIndex, x, length);
    }

    public void setURL (int parameterIndex, URL x) throws SQLException
    {
        this.comando.setURL (parameterIndex, x);
    }

    @Override
    public int hashCode ()
    {
        int ret = super.hashCode();

        ret = 13*ret + (this.comando==null?0:this.comando.hashCode());

        return ret;
    }

    @Override
    public boolean equals (Object obj)
    {
      if (obj==null)
        return false;

      if (this==obj)
        return true;

      if (this.getClass()==obj.getClass())
      {
			  PreparedStatement ps = (PreparedStatement)obj;
			  if (!super.equals(ps))
			    return false;
        MeuPreparedStatement mps = (MeuPreparedStatement)obj;
        if ((this.comando==null && mps.comando!=null) || (this.comando!=null && mps.comando==null))
          return false;

        if (this.comando!=null && mps.comando!=null && this.comando.equals(mps.comando))
          return false;

        return true;
      }
      return false;
    }

    @Override
    public String toString ()
    {
      String ret = "Herdado: "+super.toString()+"\n";

      ret += "Comando: "+this.comando;

      return ret;
    }
}