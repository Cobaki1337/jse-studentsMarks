package by.gsu.epamlab.factory;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.reader.IResultDAO;
import by.gsu.epamlab.reader.implementation.ResultImplCsv;

import java.io.IOException;
import java.sql.Date;

public class ResultFactory {
    public Result getResultFromFactory(String name, String test, String date, String mark){
        return new Result(name, test, date, mark);
    }

    public Result getResultFromFactory(String name, String test, Date date, int mark){
        return new Result(name, test, date, mark);
    }

    public IResultDAO getResultDAO(String fileName) throws IOException {
        return new ResultImplCsv(this, fileName);
    }

    public double getAverageMark(double mark){
        return mark;
    }
}
