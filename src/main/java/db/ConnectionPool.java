package db;

import db.db_manager.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
    //Number of connection to SQL (Maximum is 20, Default is 10)
    private static final int NUMBER_OF_CONNECTIONS = 10;
    private static ConnectionPool instance = null;
    private final Stack<Connection> connections = new Stack<>();

    private ConnectionPool() throws SQLException {
        System.out.println("A new connection pool instance was created...");
        openAllConnections();
    }

    private void openAllConnections() throws SQLException {
        for (int counter = 0; counter < NUMBER_OF_CONNECTIONS; counter++) {
            //Creates new connection
            Connection connection = DriverManager.getConnection(DBManager.URL, DBManager.SQL_USER, DBManager.SQL_PASS);
            //Pushes the new connection into the stack
            connections.push(connection);
        }
    }

    public void closeAllConnections() throws InterruptedException {
        synchronized (connections) {
            while (connections.size() < NUMBER_OF_CONNECTIONS) {
                connections.wait();
            }
            connections.removeAllElements();
        }
    }

    public static ConnectionPool getInstance() {
        //first we check that instance is null
        if (instance == null) {
            //Synchronized-critical code , makes sure that another thread will not pass here
            synchronized (ConnectionPool.class) {
                //Double-check, that no other thread has created an instance
                if (instance == null) {
                    try {
                        instance = new ConnectionPool();
                    } catch (SQLException err) {
                        System.out.println(err.getMessage());
                    }
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        synchronized (connections) {
            if (connections.isEmpty()) {
                //Wait until we will get a connection back
                connections.wait();
            }
            return connections.pop();
        }
    }

    public void returnConnection(Connection connection) {
        synchronized (connections) {
            connections.push(connection);
            //Notify that we got back a connection from the user...
            connections.notify();
        }
    }

}
