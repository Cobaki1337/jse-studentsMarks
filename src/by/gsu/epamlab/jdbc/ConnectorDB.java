package by.gsu.epamlab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static by.gsu.epamlab.Constants.*;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException{
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_FILE_NAME);
        String url = resourceBundle.getString(KEY_FOR_URL);
        String user = resourceBundle.getString(KEY_FOR_USER);
        String password = resourceBundle.getString(KEY_FOR_PASSWORD);
        return DriverManager.getConnection(url, user, password);
    }
}
