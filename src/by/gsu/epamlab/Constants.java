package by.gsu.epamlab;

public class Constants {
    public static final String DELIMITER = ";";
    public static final String REMAINDER_OF_THE_HALF_MARK = "0.5";
    public static final int AUXILIARY_NUMB_FOR_HALF_MARK_TO_INTEGER_TYPE = 2;
    public static final int AUXILIARY_NUMB_FOR_DECIMAL_MARK_TO_INTEGER_TYPE = 10;
    public static final String DATABASE_FILE_NAME = "database";
    public static final String KEY_FOR_URL = "url";
    public static final String KEY_FOR_USER = "user";
    public static final String KEY_FOR_PASSWORD = "password";
    public static final String REQUEST_FOR_CURRENT_MONTH_DATES = "SELECT * FROM results.results WHERE MONTH(dat) = ? ORDER BY dat;";
    public static final String REQUEST_FOR_AVERAGE_MARKS = "SELECT logins.name, AVG(mark) AS Avgmark FROM results.results " +
            "INNER JOIN results.logins ON results.loginId = logins.idLogin " +
            "GROUP BY logins.name ORDER BY Avgmark DESC;";
    public static final String REQUEST_FOR_LOGIN_NAME = "SELECT name FROM results.logins WHERE idLogin = ?";
    public static final String REQUEST_FOR_TEST_NAME = "SELECT name FROM results.tests WHERE idTest = ?;";
    public static final String REQUEST_FOR_LOGIN_ID = "SELECT idLogin FROM results.logins WHERE name = ?;";
    public static final String REQUEST_FOR_TEST_ID = "SELECT idTest FROM results.tests WHERE name = ?;";
    public static final String REQUEST_FOR_ADDING_LOGIN = "INSERT INTO results.logins (name) VALUES (?);";
    public static final String REQUEST_FOR_ADDING_TEST = "INSERT INTO results.tests (name) VALUES (?);";
    public static final String REQUEST_FOR_ADDING_RESULTS = "INSERT INTO results.results (loginId, testId, dat, mark) VALUES (?, ?, ?, ?);";
    public static final int PARAMETER_INDEX_1 = 1;
    public static final int PARAMETER_INDEX_2 = 2;
    public static final int PARAMETER_INDEX_3 = 3;
    public static final int PARAMETER_INDEX_4 = 4;
    public static final int COLUMN_INDEX_1 = 1;
    public static final int COLUMN_INDEX_2 = 2;
    public static final int COLUMN_INDEX_3 = 3;
    public static final int COLUMN_INDEX_4 = 4;
    public static final String FORMATTER_PATTERN = "%s:%2.2f\n";
    public static final int LINE_0 = 0;
    public static final int LINE_1 = 1;
    public static final int LINE_2 = 2;
    public static final int LINE_3 = 3;
    public static final int INDEX_0 = 0;
    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
}