package org.corporation.bitb.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IPostgresqlConnection implements AccessDataBase {

	private final String url = "jdbc:postgresql://localhost:5432/control";

    private final String sql = "select 1";
    private Connection connection;

     public IPostgresqlConnection() {
    	 establishConnection();
	}
    @Override
    public Connection getConnection() {
        // TODO Auto-generated method stub
        return connection;
    }
    
    @Override
    public void establishConnection() {
        try {
            //DriverManager.registerDriver(new org.postgresql.Driver());
        	Class.forName("org.postgresql.Driver");
        	System.out.println("url: "+url);
        	connection = DriverManager.getConnection(url, "postgres", "");
            //connection = DriverManager.getConnection(url);
            testConnection();
        } catch (Exception e) {
            System.out.println("Error [" + getClass() + "] - (" + e.getMessage() + ")");
            e.printStackTrace();
        }

    }

    @Override
    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println("Error [" + getClass() + "] - (" + e.getMessage() + ")");
        }

    }

    @SuppressWarnings("finally")
	@Override
    public boolean testConnection() {
        boolean _return = false;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Successful Connection [" + rs.getObject(1) + "]");
                _return = true;
            }
        } catch (SQLException e) {
            System.out.println("Error [" + getClass() + "] - (" + e.getMessage() + ")");
            e.printStackTrace();
        } finally {
            return _return;
        }
    }

}
