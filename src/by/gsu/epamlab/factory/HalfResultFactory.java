package by.gsu.epamlab.factory;

import by.gsu.epamlab.beans.HalfResult;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.reader.IResultDAO;
import by.gsu.epamlab.reader.implementation.ResultImplCsv;

import java.io.IOException;
import java.sql.Date;

import static by.gsu.epamlab.Constants.AUXILIARY_NUMB_FOR_HALF_MARK_TO_INTEGER_TYPE;

public class HalfResultFactory extends ResultFactory {
    @Override
    public Result getResultFromFactory(String name, String test, String date, String mark) {
        return new HalfResult(name, test, date, mark);
    }

    @Override
    public Result getResultFromFactory(String name, String test, Date date, int mark) {
        return new HalfResult(name, test, date, mark);
    }

    @Override
    public IResultDAO getResultDAO(String fileName) throws IOException {
        return new ResultImplCsv(this, fileName);
    }

    @Override
    public double getAverageMark(double mark) {
        return mark / AUXILIARY_NUMB_FOR_HALF_MARK_TO_INTEGER_TYPE;
    }
}
