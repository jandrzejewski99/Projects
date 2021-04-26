package projekt;


import java.sql.Connection;
import java.sql.SQLException;

public interface Database {


    void connect();
    void disconnect();

    Connection getConnection() throws SQLException;

}
