package database;

import entities.User;

import java.sql.*;

public class DatabaseService {

    private static final String CONNECTION_STRING =     "jdbc:mysql://mysql.agh.edu.pl/";
    private static final String TABLE_NAME_QUESTIONS =  "questions";
    private static final String TABLE_NAME_USERS =      "users";

    // db columns
    private static final String COLUMN_USER =           "username";
    private static final String COLUMN_PASSWORD =       "pass";

    private static Connection connection = null;
    private static Statement statement =   null;
    private static ResultSet resultSet =   null;

    private static String dbUser = "dwrobel";
    private static String dbPassword = "baza";

    private static DatabaseService databaseService = null;

    // singleton design pattern
    private DatabaseService() {

    }

    public static  synchronized  DatabaseService getInstance() {
        if(databaseService == null){
            databaseService = new DatabaseService();
        }
        return databaseService;
    }

    public static void connect() {

        try {
            // registering jdbc driver
            // REDUNDANT ! Not necessary since JDBC 4.0 or above.
            // Class.forName("com.mysql.jdbc.Driver").newInstance();

            // getting connection
            String connectionURL = CONNECTION_STRING + dbUser;
            connection = DriverManager.getConnection(connectionURL, dbUser, dbPassword);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Queries DB for all records in table
     * @return ArrayList of Objects related to records in DB
     */
    public static User checkUser(String user, String pass){

        connect();

        // create query for the user
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_NAME_USERS);
        sb.append(" WHERE username = '");
        sb.append(user);
        sb.append("' AND ");
        sb.append("password = '");
        sb.append(pass);
        sb.append("';");

        System.out.println(sb.toString());

        User userDB = null;

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sb.toString());

            if(!resultSet.next()){
                System.out.println("Nie ma takiego usera");

            }
            else{
                System.out.println("Istnieje taki user");
                userDB = new User();
                userDB.setUsername(resultSet.getString(COLUMN_USER));
            }
        }
        catch(SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        close();

        return userDB;
    }


    /**
     * Closes connection, statement and resultSet
     */
    public static void close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            resultSet = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            statement = null;
        }
        if( connection != null ) {
            try{
                connection.close();
            } catch( SQLException ex ){
                ex.printStackTrace();
            }
            connection = null;
        }
    }






}
