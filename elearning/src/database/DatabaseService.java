package database;

import entities.Quizz;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    private static final String CONNECTION_STRING =         "jdbc:mysql://mysql.agh.edu.pl/";


    // --------------------------------------------------------------------- //
    // --------- DB TABLES ------------------------------------------------- //
    // db table = users
    private static final String TABLE_NAME_USERS =          "users";
    private static final String COLUMN_USER =               "username";
    private static final String COLUMN_PASSWORD =           "pass";

    // db table = quizzes
    private static final String TABLE_NAME_QUIZZES =        "quizzes";
    private static final String COLUMN_QUIZ_ID =            "quizId";
    private static final String FOREIGN_COLUMN_USERNAME =   "username";
    private static final String COLUMN_QUIZ_NAME =          "quizName";

    // db table = questions
    private static final String TABLE_NAME_QUESTIONS =      "questions";
    private static final String COLUMN_QUESTION_ID =        "questionId";
    private static final String FOREIGN_COLUMN_QUIZ_ID =    "quizId";
    private static final String COLUMN_QUESTION =           "question";

    // db table = answers
    private static final String TABLE_NAME_ANSWERS=         "answers";
    private static final String COLUMN_ANSWER_ID =          "answerId";
    private static final String FOREIGN_COLUMN_QUESTION_ID ="questionId";
    private static final String COLUMN_ANSWER =             "answer";
    private static final String COLUMN_CORRECT =            "correct";
    // --------------------------------------------------------------------- //
    // --------------------------------------------------------------------- //


    private static Connection connection = null;
    private static Statement statement =   null;
    private static ResultSet resultSet =   null;

    private static String dbUser =      "dwrobel";
    private static String dbPassword =  "baza";

    private static DatabaseService databaseService = null;

    /**
     * singleton design pattern
     */
    private DatabaseService() {}
    public static synchronized  DatabaseService getInstance() {
        if(databaseService == null){
            databaseService = new DatabaseService();
        }
        return databaseService;
    }

    /**
     * connects to DB
     */
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


    /**
     * authenticates username and password
     * @return User objet containing pass and un or null if not authenticated
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

    public static ArrayList<Quizz> getUsersQuizzes( String un ){

        connect();

        // create query for the user
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_NAME_QUIZZES);
        sb.append(" WHERE username = '");
        sb.append(un);
        sb.append("';");

        ArrayList<Quizz> usersQuizzes = new ArrayList<>();

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sb.toString());
            while( resultSet.next() ){

                Quizz quizz = new Quizz();
                int id = Integer.parseInt(resultSet.getString(COLUMN_QUIZ_ID));
                quizz.setQuizId(id);
                quizz.setUsername(resultSet.getString(FOREIGN_COLUMN_USERNAME));
                quizz.setQuizName(resultSet.getString(COLUMN_QUIZ_NAME));

                usersQuizzes.add(quizz);
            }
        }
        catch(SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        close();
        return usersQuizzes;

    }










}
