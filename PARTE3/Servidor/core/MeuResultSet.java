package Servidor.core;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;

public class MeuResultSet implements ResultSet
{
  protected Statement comando;
  protected ResultSet resultado;
		
    protected MeuResultSet (Statement c, ResultSet r)
    {
        this.comando   = c;
        this.resultado = r;
    }

    public boolean absolute (int row) throws SQLException
    {
        return this.resultado.absolute (row);
    }

    public void afterLast () throws SQLException
    {
        this.resultado.afterLast ();
    }

    public void beforeFirst() throws SQLException
    {
        this.resultado.beforeFirst();
    }

    public void cancelRowUpdates () throws SQLException
    {
        this.resultado.cancelRowUpdates ();
    }

    public void clearWarnings () throws SQLException
    {
        this.resultado.clearWarnings ();
    }

    public void close () throws SQLException
    {
        this.resultado.close ();
        this.comando  .close ();
    }

    public void deleteRow () throws SQLException
    {
        this.resultado.deleteRow ();
    }

    public int findColumn (String columnLabel) throws SQLException
    {
        return this.resultado.findColumn (columnLabel);
    }

    public boolean first () throws SQLException
    {
        return this.resultado.first ();
    }

    public Array getArray (int columnIndex) throws SQLException
    {
        return this.resultado.getArray (columnIndex);
    }

    public Array getArray (String columnLabel) throws SQLException
    {
        return this.resultado.getArray (columnLabel);
    }

    public InputStream getAsciiStream (int columnIndex) throws SQLException
    {
        return this.resultado.getAsciiStream (columnIndex);
    }

    public InputStream getAsciiStream (String columnLabel) throws SQLException
    {
        return this.resultado.getAsciiStream (columnLabel);
    }

    public BigDecimal getBigDecimal (int columnIndex) throws SQLException
    {
        return this.resultado.getBigDecimal (columnIndex);
    }

    public BigDecimal getBigDecimal (int columnIndex, int scale) throws SQLException
    {
        return this.resultado.getBigDecimal (columnIndex, scale);
    }

    public BigDecimal getBigDecimal (String columnLabel) throws SQLException
    {
        return this.resultado.getBigDecimal (columnLabel);
    }

    public BigDecimal getBigDecimal (String columnLabel, int scale) throws SQLException
    {
        return this.resultado.getBigDecimal (columnLabel, scale);
    }

    public InputStream getBinaryStream (int columnIndex) throws SQLException
    {
        return this.resultado.getBinaryStream (columnIndex);
    }

    public InputStream getBinaryStream (String columnLabel) throws SQLException
    {
        return this.resultado.getBinaryStream (columnLabel);
    }

    public Blob getBlob (int columnIndex) throws SQLException
    {
        return this.resultado.getBlob (columnIndex);
    }

    public Blob getBlob (String columnLabel) throws SQLException
    {
        return this.resultado.getBlob (columnLabel);
    }

    public boolean getBoolean (int columnIndex) throws SQLException
    {
        return this.resultado.getBoolean (columnIndex);
    }

    public boolean getBoolean (String columnLabel) throws SQLException
    {
        return this.resultado.getBoolean (columnLabel);
    }

    public byte getByte (int columnIndex) throws SQLException
    {
        return this.resultado.getByte (columnIndex);
    }

    public byte getByte (String columnLabel) throws SQLException
    {
        return this.resultado.getByte (columnLabel);
    }

    public byte[] getBytes (int columnIndex) throws SQLException
    {
        return this.resultado.getBytes (columnIndex);
    }

    public byte[] getBytes (String columnLabel) throws SQLException
    {
        return this.resultado.getBytes (columnLabel);
    }

    public Reader getCharacterStream (int columnIndex) throws SQLException
    {
        return this.resultado.getCharacterStream (columnIndex);
    }

    public Reader getCharacterStream (String columnLabel) throws SQLException
    {
        return this.resultado.getCharacterStream (columnLabel);
    }

    public Clob getClob (int columnIndex) throws SQLException
    {
        return this.resultado.getClob (columnIndex);
    }

    public Clob getClob (String columnLabel) throws SQLException
    {
        return this.resultado.getClob (columnLabel);
    }

    public int getConcurrency () throws SQLException
    {
        return this.resultado.getConcurrency ();
    }

    public String getCursorName () throws SQLException
    {
        return this.resultado.getCursorName ();
    }

    public Date getDate (int columnIndex) throws SQLException
    {
        return this.resultado.getDate (columnIndex);
    }

    public Date getDate (int columnIndex, Calendar cal) throws SQLException
    {
        return this.resultado.getDate (columnIndex, cal);
    }

    public Date getDate (String columnLabel) throws SQLException
    {
        return this.resultado.getDate (columnLabel);
    }

    public Date getDate (String columnLabel, Calendar cal) throws SQLException
    {
        return this.resultado.getDate (columnLabel, cal);
    }

    public double getDouble (int columnIndex) throws SQLException
    {
        return this.resultado.getDouble (columnIndex);
    }

    public double getDouble (String columnLabel) throws SQLException
    {
        return this.resultado.getDouble (columnLabel);
    }

    public int getFetchDirection () throws SQLException
    {
        return this.resultado.getFetchDirection ();
    }

    public int getFetchSize () throws SQLException
    {
        return this.resultado.getFetchSize ();
    }

    public float getFloat (int columnIndex) throws SQLException
    {
        return this.resultado.getFloat (columnIndex);
    }

    public float getFloat (String columnLabel) throws SQLException
    {
        return this.resultado.getFloat (columnLabel);
    }

    public int getHoldability () throws SQLException
    {
        return this.resultado.getHoldability ();
    }

    public int getInt (int columnIndex) throws SQLException
    {
        return this.resultado.getInt (columnIndex);
    }

    public int getInt (String columnLabel) throws SQLException
    {
        return this.resultado.getInt (columnLabel);
    }

    public long getLong (int columnIndex) throws SQLException
    {
        return this.resultado.getLong (columnIndex);
    }

    public long getLong (String columnLabel) throws SQLException
    {
        return this.resultado.getLong (columnLabel);
    }

    public ResultSetMetaData getMetaData () throws SQLException
    {
        return this.resultado.getMetaData ();
    }

    public Reader getNCharacterStream (int columnIndex) throws SQLException
    {
        return this.resultado.getNCharacterStream (columnIndex);
    }

    public Reader getNCharacterStream (String columnLabel) throws SQLException
    {
        return this.resultado.getNCharacterStream (columnLabel);
    }

    public NClob getNClob (int columnIndex) throws SQLException
    {
        return this.resultado.getNClob (columnIndex);
    }

    public NClob getNClob (String columnLabel) throws SQLException
    {
        return this.resultado.getNClob (columnLabel);
    }

    public String getNString (int columnIndex) throws SQLException
    {
        return this.resultado.getNString (columnIndex);
    }

    public String getNString (String columnLabel) throws SQLException
    {
        return this.resultado.getNString (columnLabel);
    }

    public Object getObject (int columnIndex) throws SQLException
    {
        return this.resultado.getObject (columnIndex);
    }

    public <T> T getObject (int columnIndex, Class<T> type) throws SQLException
    {
        return this.resultado.getObject (columnIndex, type);
    }

    public Object getObject (int columnIndex, Map<String,Class<?>> map) throws SQLException
    {
        return this.resultado.getObject (columnIndex, map);
    }

    public Object getObject (String columnLabel) throws SQLException
    {
        return this.resultado.getObject (columnLabel);
    }

    public <T> T getObject (String columnLabel, Class<T> type) throws SQLException
     {
        return this.resultado.getObject (columnLabel, type);
    }

    public Object getObject (String columnLabel, Map<String,Class<?>> map) throws SQLException
    {
        return this.resultado.getObject (columnLabel, map);
    }

    public Ref getRef (int columnIndex) throws SQLException
    {
        return this.resultado.getRef (columnIndex);
    }

    public Ref getRef (String columnLabel) throws SQLException
    {
        return this.resultado.getRef (columnLabel);
    }

    public int getRow () throws SQLException
    {
        return this.resultado.getRow ();
    }

    public RowId getRowId (int columnIndex) throws SQLException
    {
        return this.resultado.getRowId (columnIndex);
    }

    public RowId getRowId (String columnLabel) throws SQLException
    {
        return this.resultado.getRowId (columnLabel);
    }

    public short getShort (int columnIndex) throws SQLException
    {
        return this.resultado.getShort (columnIndex);
    }

    public short getShort (String columnLabel) throws SQLException
    {
        return this.resultado.getShort (columnLabel);
    }

    public SQLXML getSQLXML (int columnIndex) throws SQLException
    {
        return this.resultado.getSQLXML (columnIndex);
    }

    public SQLXML getSQLXML (String columnLabel) throws SQLException
    {
        return this.resultado.getSQLXML (columnLabel);
    }

    public Statement getStatement () throws SQLException
    {
        return this.resultado.getStatement ();
    }

    public String getString (int columnIndex) throws SQLException
    {
        return this.resultado.getString (columnIndex);
    }

    public String getString (String columnLabel) throws SQLException
    {
        return this.resultado.getString (columnLabel);
    }

    public Time getTime (int columnIndex) throws SQLException
    {
        return this.resultado.getTime (columnIndex);
    }

    public Time getTime (int columnIndex, Calendar cal) throws SQLException
    {
        return this.resultado.getTime (columnIndex, cal);
    }

    public Time getTime (String columnLabel) throws SQLException
    {
        return this.resultado.getTime (columnLabel);
    }

    public Time getTime (String columnLabel, Calendar cal) throws SQLException
    {
        return this.resultado.getTime (columnLabel, cal);
    }

    public Timestamp getTimestamp (int columnIndex) throws SQLException
    {
        return this.resultado.getTimestamp (columnIndex);
    }

    public Timestamp getTimestamp (int columnIndex, Calendar cal) throws SQLException
    {
        return this.resultado.getTimestamp (columnIndex, cal);
    }

    public Timestamp getTimestamp (String columnLabel) throws SQLException
    {
        return this.resultado.getTimestamp (columnLabel);
    }

    public Timestamp getTimestamp (String columnLabel, Calendar cal) throws SQLException
    {
        return this.resultado.getTimestamp (columnLabel, cal);
    }

    public int getType () throws SQLException
    {
        return this.resultado.getType ();
    }

    public InputStream getUnicodeStream (int columnIndex) throws SQLException
    {
        return this.resultado.getUnicodeStream (columnIndex);
    }

    public InputStream getUnicodeStream (String columnLabel) throws SQLException
    {
        return this.resultado.getUnicodeStream (columnLabel);
    }

    public URL getURL (int columnIndex) throws SQLException
    {
        return this.resultado.getURL (columnIndex);
    }

    public URL getURL (String columnLabel) throws SQLException
    {
        return this.resultado.getURL (columnLabel);
    }

    public SQLWarning getWarnings () throws SQLException
    {
        return this.resultado.getWarnings ();
    }

    public void insertRow () throws SQLException
    {
        this.resultado.insertRow ();
    }

    public boolean isAfterLast () throws SQLException
    {
        return this.resultado.isAfterLast ();
    }

    public boolean isBeforeFirst () throws SQLException
    {
        return this.resultado.isBeforeFirst ();
    }

    public boolean isClosed () throws SQLException
    {
        return this.resultado.isClosed ();
    }

    public boolean isFirst () throws SQLException
    {
        return this.resultado.isFirst ();
    }

    public boolean isLast () throws SQLException
    {
        return this.resultado.isLast ();
    }

    public boolean last () throws SQLException
    {
        return this.resultado.last ();
    }

    public void moveToCurrentRow () throws SQLException
    {
        this.resultado.moveToCurrentRow ();
    }

    public void moveToInsertRow () throws SQLException
    {
        this.resultado.moveToInsertRow ();
    }

    public boolean next () throws SQLException
    {
        return this.resultado.next ();
    }

    public boolean previous () throws SQLException
    {
        return this.resultado.previous ();
    }

    public void refreshRow () throws SQLException
    {
        this.resultado.refreshRow ();
    }

    public boolean relative (int rows) throws SQLException
    {
        return this.resultado.relative (rows);
    }

    public boolean rowDeleted () throws SQLException
    {
        return this.resultado.rowDeleted ();
    }

    public boolean rowInserted () throws SQLException
    {
        return this.resultado.rowInserted ();
    }

    public boolean rowUpdated () throws SQLException
    {
        return this.resultado.rowUpdated ();
    }

    public void setFetchDirection (int direction) throws SQLException
    {
        this.resultado.setFetchDirection (direction);
    }

    public void setFetchSize (int rows) throws SQLException
    {
        this.resultado.setFetchSize (rows);
    }

    public void updateArray (int columnIndex, Array x) throws SQLException
    {
        this.resultado.updateArray (columnIndex, x);
    }

    public void updateArray (String columnLabel, Array x) throws SQLException
    {
        this.resultado.updateArray (columnLabel, x);
    }

    public void updateAsciiStream (int columnIndex, InputStream x) throws SQLException
    {
        this.resultado.updateAsciiStream (columnIndex, x);
    }

    public void updateAsciiStream (int columnIndex, InputStream x, int length) throws SQLException
    {
        this.resultado.updateAsciiStream (columnIndex, x, length);
    }

    public void updateAsciiStream (int columnIndex, InputStream x, long length) throws SQLException
    {
        this.resultado.updateAsciiStream (columnIndex, x, length);
    }

    public void updateAsciiStream (String columnLabel, InputStream x) throws SQLException
    {
        this.resultado.updateAsciiStream (columnLabel, x);
    }

    public void updateAsciiStream (String columnLabel, InputStream x, int length) throws SQLException
    {
        this.resultado.updateAsciiStream (columnLabel, x, length);
    }

    public void updateAsciiStream (String columnLabel, InputStream x, long length) throws SQLException
    {
        this.resultado.updateAsciiStream (columnLabel, x, length);
    }

    public void updateBigDecimal (int columnIndex, BigDecimal x) throws SQLException
    {
        this.resultado.updateBigDecimal (columnIndex, x);
    }

    public void updateBigDecimal (String columnLabel, BigDecimal x) throws SQLException
    {
        this.resultado.updateBigDecimal (columnLabel, x);
    }

    public void updateBinaryStream (int columnIndex, InputStream x) throws SQLException
    {
        this.resultado.updateBinaryStream (columnIndex, x);
    }

    public void updateBinaryStream (int columnIndex, InputStream x, int length) throws SQLException
    {
        this.resultado.updateBinaryStream (columnIndex, x, length);
    }

    public void updateBinaryStream (int columnIndex, InputStream x, long length) throws SQLException
    {
        this.resultado.updateBinaryStream (columnIndex, x, length);
    }

    public void updateBinaryStream (String columnLabel, InputStream x) throws SQLException
    {
        this.resultado.updateBinaryStream (columnLabel, x);
    }

    public void updateBinaryStream (String columnLabel, InputStream x, int length) throws SQLException
    {
        this.resultado.updateBinaryStream (columnLabel, x, length);
    }

    public void updateBinaryStream (String columnLabel, InputStream x, long length) throws SQLException
    {
        this.resultado.updateBinaryStream (columnLabel, x, length);
    }

    public void updateBlob (int columnIndex, Blob x) throws SQLException
    {
        this.resultado.updateBlob (columnIndex, x);
    }

    public void updateBlob (int columnIndex, InputStream inputStream) throws SQLException
    {
        this.resultado.updateBlob (columnIndex, inputStream);
    }

    public void updateBlob (int columnIndex, InputStream inputStream, long length) throws SQLException
    {
        this.resultado.updateBlob (columnIndex, inputStream, length);
    }

    public void updateBlob (String columnLabel, Blob x) throws SQLException
    {
        this.resultado.updateBlob (columnLabel, x);
    }

    public void updateBlob (String columnLabel, InputStream inputStream) throws SQLException
    {
        this.resultado.updateBlob (columnLabel, inputStream);
    }

    public void updateBlob (String columnLabel, InputStream inputStream, long length) throws SQLException
    {
        this.resultado.updateBlob (columnLabel, inputStream, length);
    }

    public void updateBoolean (int columnIndex, boolean x) throws SQLException
    {
        this.resultado.updateBoolean (columnIndex, x);
    }

    public void updateBoolean (String columnLabel, boolean x) throws SQLException
    {
        this.resultado.updateBoolean (columnLabel, x);
    }

    public void updateByte (int columnIndex, byte x) throws SQLException
    {
        this.resultado.updateByte (columnIndex, x);
    }

    public void updateByte (String columnLabel, byte x) throws SQLException
    {
        this.resultado.updateByte (columnLabel, x);
    }

    public void updateBytes (int columnIndex, byte[] x) throws SQLException
    {
        this.resultado.updateBytes (columnIndex, x);
    }

    public void updateBytes (String columnLabel, byte[] x) throws SQLException
    {
        this.resultado.updateBytes (columnLabel, x);
    }

    public void updateCharacterStream (int columnIndex, Reader x) throws SQLException
    {
        this.resultado.updateCharacterStream (columnIndex, x);
    }

    public void updateCharacterStream (int columnIndex, Reader x, int length) throws SQLException
    {
        this.resultado.updateCharacterStream (columnIndex, x, length);
    }

    public void updateCharacterStream (int columnIndex, Reader x, long length) throws SQLException
    {
        this.resultado.updateCharacterStream (columnIndex, x, length);
    }

    public void updateCharacterStream (String columnLabel, Reader reader) throws SQLException
    {
        this.resultado.updateCharacterStream (columnLabel, reader);
    }

    public void updateCharacterStream (String columnLabel, Reader reader, int length) throws SQLException
    {
        this.resultado.updateCharacterStream (columnLabel, reader, length);
    }

    public void updateCharacterStream (String columnLabel, Reader reader, long length) throws SQLException
    {
        this.resultado.updateCharacterStream (columnLabel, reader, length);
    }

    public void updateClob (int columnIndex, Clob x) throws SQLException
    {
        this.resultado.updateClob (columnIndex, x);
    }

    public void updateClob (int columnIndex, Reader reader) throws SQLException
    {
        this.resultado.updateClob (columnIndex, reader);
    }

    public void updateClob (int columnIndex, Reader reader, long length) throws SQLException
    {
        this.resultado.updateClob (columnIndex, reader, length);
    }

    public void updateClob (String columnLabel, Clob x) throws SQLException
    {
        this.resultado.updateClob (columnLabel, x);
    }

    public void updateClob (String columnLabel, Reader reader) throws SQLException
    {
        this.resultado.updateClob (columnLabel, reader);
    }

    public void updateClob (String columnLabel, Reader reader, long length) throws SQLException
    {
        this.resultado.updateClob (columnLabel, reader, length);
    }

    public void updateDate (int columnIndex, Date x) throws SQLException
    {
        this.resultado.updateDate (columnIndex, x);
    }

    public void updateDate (String columnLabel, Date x) throws SQLException
    {
        this.resultado.updateDate (columnLabel, x);
    }

    public void updateDouble (int columnIndex, double x) throws SQLException
    {
        this.resultado.updateDouble (columnIndex, x);
    }

    public void updateDouble (String columnLabel, double x) throws SQLException
    {
        this.resultado.updateDouble (columnLabel, x);
    }

    public void updateFloat (int columnIndex, float x) throws SQLException
    {
        this.resultado.updateFloat (columnIndex, x);
    }

    public void updateFloat (String columnLabel, float x) throws SQLException
    {
        this.resultado.updateFloat (columnLabel, x);
    }

    public void updateInt (int columnIndex, int x) throws SQLException
    {
        this.resultado.updateInt (columnIndex, x);
    }

    public void updateInt (String columnLabel, int x) throws SQLException
    {
        this.resultado.updateInt (columnLabel, x);
    }

    public void updateLong (int columnIndex, long x) throws SQLException
    {
        this.resultado.updateLong (columnIndex, x);
    }

    public void updateLong (String columnLabel, long x) throws SQLException
    {
        this.resultado.updateLong (columnLabel, x);
    }

    public void updateNCharacterStream (int columnIndex, Reader x) throws SQLException
    {
        this.resultado.updateNCharacterStream (columnIndex, x);
    }

    public void updateNCharacterStream (int columnIndex, Reader x, long length) throws SQLException
    {
        this.resultado.updateNCharacterStream (columnIndex, x, length);
    }

    public void updateNCharacterStream (String columnLabel, Reader reader) throws SQLException
    {
        this.resultado.updateNCharacterStream (columnLabel, reader);
    }

    public void updateNCharacterStream (String columnLabel, Reader reader, long length) throws SQLException
    {
        this.resultado.updateNCharacterStream (columnLabel, reader, length);
    }

    public void updateNClob (int columnIndex, NClob nClob) throws SQLException
    {
        this.resultado.updateNClob (columnIndex, nClob);
    }

    public void updateNClob (int columnIndex, Reader reader) throws SQLException
    {
        this.resultado.updateNClob (columnIndex, reader);
    }

    public void updateNClob (int columnIndex, Reader reader, long length) throws SQLException
    {
        this.resultado.updateNClob (columnIndex, reader, length);
    }

    public void updateNClob (String columnLabel, NClob nClob) throws SQLException
    {
        this.resultado.updateNClob (columnLabel, nClob);
    }

    public void updateNClob (String columnLabel, Reader reader) throws SQLException
    {
        this.resultado.updateNClob (columnLabel, reader);
    }

    public void updateNClob (String columnLabel, Reader reader, long length) throws SQLException
    {
        this.resultado.updateNClob (columnLabel, reader, length);
    }

    public void updateNString (int columnIndex, String nString) throws SQLException
    {
        this.resultado.updateNString (columnIndex, nString);
    }

    public void updateNString (String columnLabel, String nString) throws SQLException
    {
        this.resultado.updateNString (columnLabel, nString);
    }

    public void updateNull (int columnIndex) throws SQLException
    {
        this.resultado.updateNull (columnIndex);
    }

    public void updateNull (String columnLabel) throws SQLException
    {
        this.resultado.updateNull (columnLabel);
    }

    public void updateObject (int columnIndex, Object x) throws SQLException
    {
        this.resultado.updateObject (columnIndex, x);
    }

    public void updateObject (int columnIndex, Object x, int scaleOrLength) throws SQLException
    {
        this.resultado.updateObject (columnIndex, x, scaleOrLength);
    }

    public void updateObject (String columnLabel, Object x) throws SQLException
    {
        this.resultado.updateObject (columnLabel, x);
    }

    public void updateObject (String columnLabel, Object x, int scaleOrLength) throws SQLException
    {
        this.resultado.updateObject (columnLabel, x, scaleOrLength);
    }

    public void updateRef (int columnIndex, Ref x) throws SQLException
    {
        this.resultado.updateRef (columnIndex, x);
    }

    public void updateRef (String columnLabel, Ref x) throws SQLException
    {
        this.resultado.updateRef (columnLabel, x);
    }

    public void updateRow () throws SQLException
    {
        this.resultado.updateRow ();
    }

    public void updateRowId (int columnIndex, RowId x) throws SQLException
    {
        this.resultado.updateRowId (columnIndex, x);
    }

    public void updateRowId (String columnLabel, RowId x) throws SQLException
    {
        this.resultado.updateRowId (columnLabel, x);
    }

    public void updateShort (int columnIndex, short x) throws SQLException
    {
        this.resultado.updateShort (columnIndex, x);
    }

    public void updateShort (String columnLabel, short x) throws SQLException
    {
        this.resultado.updateShort (columnLabel, x);
    }

    public void updateSQLXML (int columnIndex, SQLXML xmlObject) throws SQLException
    {
        this.resultado.updateSQLXML (columnIndex, xmlObject);
    }

    public void updateSQLXML (String columnLabel, SQLXML xmlObject) throws SQLException
    {
        this.resultado.updateSQLXML (columnLabel, xmlObject);
    }

    public void updateString (int columnIndex, String x) throws SQLException
    {
        this.resultado.updateString (columnIndex, x);
    }

    public void updateString (String columnLabel, String x) throws SQLException
    {
        this.resultado.updateString (columnLabel, x);
    }

    public void updateTime (int columnIndex, Time x) throws SQLException
    {
        this.resultado.updateTime (columnIndex, x);
    }

    public void updateTime (String columnLabel, Time x) throws SQLException
    {
        this.resultado.updateTime (columnLabel, x);
    }

    public void updateTimestamp (int columnIndex, Timestamp x) throws SQLException
    {
        this.resultado.updateTimestamp (columnIndex, x);
    }

    public void updateTimestamp (String columnLabel, Timestamp x) throws SQLException
    {
        this.resultado.updateTimestamp (columnLabel, x);
    }

    public boolean wasNull () throws SQLException
    {
        return this.resultado.wasNull ();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException
    {
        return this.resultado.isWrapperFor (iface);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException
    {
        return this.resultado.unwrap (iface);
    }

    @Override
    public int hashCode ()
    {
        int ret = super.hashCode();

        ret = 13*ret + (this.comando  ==null?0:this.comando  .hashCode());
        ret = 13*ret + (this.resultado==null?0:this.resultado.hashCode());

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
			  ResultSet rs = (ResultSet)obj;
        if (!super.equals(rs))
			    return false;
        MeuResultSet mrs = (MeuResultSet)obj;

        if (!this.comando.equals(mrs.comando))
          return false;

        if (!this.resultado.equals(mrs.resultado))
          return false;

        return true;
      }
      return false;
    }

    @Override
    public String toString ()
    {
		String ret = "Herdado: "+super.toString()+"\n";

		ret += "Comando...: "+this.comando;

		return ret;
    }
}