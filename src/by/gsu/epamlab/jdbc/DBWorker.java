package by.gsu.epamlab.jdbc;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factory.ResultFactory;

import java.sql.*;
import java.util.*;

import static by.gsu.epamlab.Constants.*;

public class DBWorker {
    private static DBWorker INSTANCE;

    private DBWorker() throws SQLException {
        connection = ConnectorDB.getConnection();
    }

    public static DBWorker getInstance() throws SQLException {
        if (INSTANCE == null){
            INSTANCE = new DBWorker();
        }
        return INSTANCE;
    }

    private Connection connection;
    private PreparedStatement addLogin;
    private PreparedStatement checkLogin;
    private PreparedStatement addTest;
    private PreparedStatement checkTest;
    private PreparedStatement addResults;

    public Deque<Result> resultForCurrentMonth(int month , ResultFactory resultFactory) throws SQLException {
        Deque<Result> students = new LinkedList<>();
        try (PreparedStatement currentMonth = connection.prepareStatement(REQUEST_FOR_CURRENT_MONTH_DATES);
             PreparedStatement logins = connection.prepareStatement(REQUEST_FOR_LOGIN_NAME);
             PreparedStatement tests = connection.prepareStatement(REQUEST_FOR_TEST_NAME)) {
            currentMonth.setInt(PARAMETER_INDEX_1, month);
            ResultSet results = currentMonth.executeQuery();
            while (results.next()){
                logins.setInt(PARAMETER_INDEX_1, results.getInt(COLUMN_INDEX_1));
                tests.setInt(PARAMETER_INDEX_1, results.getInt(COLUMN_INDEX_2));
                ResultSet login = logins.executeQuery();
                login.next();
                ResultSet test = tests.executeQuery();
                test.next();
                students.add(resultFactory.getResultFromFactory(login.getString(COLUMN_INDEX_1), test.getString(COLUMN_INDEX_1),
                        results.getDate(COLUMN_INDEX_3), results.getInt(COLUMN_INDEX_4)));
            }
        }
        return students;
    }

    public String averageMark(ResultFactory resultFactory) throws SQLException {
        Formatter formatter = new Formatter();
        try(PreparedStatement average = connection.prepareStatement(REQUEST_FOR_AVERAGE_MARKS)) {
            ResultSet averageMark = average.executeQuery();
            while (averageMark.next()) {
                String name = averageMark.getString(COLUMN_INDEX_1);
                double avgMark = resultFactory.getAverageMark(averageMark.getDouble(COLUMN_INDEX_2));
                formatter.format(FORMATTER_PATTERN,
                        name, avgMark);
            }
        }
        return formatter.toString();
    }

    private int getId(String name, PreparedStatement checkStatement, PreparedStatement insertStatement) throws SQLException {
        int id;
        checkStatement.setString(PARAMETER_INDEX_1, name);
        ResultSet checkResult = checkStatement.executeQuery();
        if (!checkResult.next()){
            insertStatement.setString(PARAMETER_INDEX_1, name);
            insertStatement.executeUpdate();
            ResultSet recheckResult = checkStatement.executeQuery();
            recheckResult.next();
            id = recheckResult.getInt(PARAMETER_INDEX_1);
        } else {
            id = checkResult.getInt(PARAMETER_INDEX_1);
        }
        return id;
    }

    public void addInResults(Result result) throws SQLException {
        if (checkLogin == null){
            checkLogin = connection.prepareStatement(REQUEST_FOR_LOGIN_ID);
        }
        if (addLogin == null){
            addLogin = connection.prepareStatement(REQUEST_FOR_ADDING_LOGIN);
        }
        if (checkTest == null){
            checkTest = connection.prepareStatement(REQUEST_FOR_TEST_ID);
        }
        if (addTest == null){
            addTest = connection.prepareStatement(REQUEST_FOR_ADDING_TEST);
        }
        if (addResults == null){
            addResults = connection.prepareStatement(REQUEST_FOR_ADDING_RESULTS);
        }
        addResults.setInt(PARAMETER_INDEX_1, getId(result.getName(), checkLogin, addLogin));
        addResults.setInt(PARAMETER_INDEX_2, getId(result.getTest(), checkTest, addTest));
        addResults.setDate(PARAMETER_INDEX_3, result.getDate());
        addResults.setInt(PARAMETER_INDEX_4, result.getMark());
        addResults.executeUpdate();
    }

    public void closeStatements() throws SQLException {
        if (addLogin != null){
            addLogin.close();
        }
        if (checkLogin != null){
            checkLogin.close();
        }
        if (addTest != null){
            addTest.close();
        }
        if (checkTest != null){
            checkTest.close();
        }
        if (addResults != null){
            addResults.close();
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null){
            connection.close();
        }
    }
}
