package by.gsu.epamlab;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factory.ResultFactory;
import by.gsu.epamlab.jdbc.DBWorker;
import by.gsu.epamlab.reader.IResultDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Deque;
import java.util.Iterator;

public class RunnerLogic {
    public static void execute(ResultFactory resultFactory, String fileName)  {
        IResultDAO reader = null;
        int currentMonth = 5;  //month variable
//2
        try {
            reader = resultFactory.getResultDAO(fileName);
            while (reader.hasResult()){
                DBWorker.getInstance().addInResults(reader.nextResult());
            }
//3
            System.out.println("The mean value of marks on every student:");
            System.out.println(DBWorker.getInstance().averageMark(resultFactory));
//4
            try {
                System.out.println("Results for current month:");
                Deque<Result> results = DBWorker.getInstance().resultForCurrentMonth(currentMonth, resultFactory);
                for (Result result : results) {
                    System.out.println(result);
                }
//5
                System.out.println();
                if (!results.isEmpty()) {
                    System.out.println("Tests results in the latest day:");
                    Date lastDate = results.getLast().getDate();
                    Iterator<Result> iterator = results.descendingIterator();
                    while (iterator.hasNext()) {
                        Result cur = iterator.next();
                        if (cur.getDate().equals(lastDate)) {
                            System.out.println(cur);
                        } else {
                            break;
                        }
                    }
                }else {
                    System.out.println("Result is empty.");
                }
            }catch (SQLException e){
                System.err.println("Results loading problem:" + e);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File is not found.");
        } catch (SQLException e) {
            System.err.println("Connection's problems with DB.");
            throw new RuntimeException();
        } catch (IOException e) {
            System.err.println("Input or output operations is failed.");
        } finally {
                try {
                    if (reader != null) {
                        reader.closeReader();
                    }
                } catch (IOException e) {
                    System.err.println("Reader closing problem: " + e);
                }
                try {
                    DBWorker.getInstance().closeStatements();
                }catch (SQLException e){
                    System.err.println("Statements closing problem: " + e);
                }
            try {
                DBWorker.getInstance().closeConnection();
            } catch (SQLException e) {
                System.err.println("Connection closing problem: " + e);
            }
        }
    }
}
