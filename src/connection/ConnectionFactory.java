package connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static final Properties props = new Properties();

	static {
		try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties")) {

			props.load(input);

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"),
					props.getProperty("db.password"));
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}