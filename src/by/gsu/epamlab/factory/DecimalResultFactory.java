package by.gsu.epamlab.factory;

import by.gsu.epamlab.beans.DecimalResult;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.reader.IResultDAO;
import by.gsu.epamlab.reader.implementation.ResultImplXml;

import java.io.IOException;
import java.sql.Date;

import static by.gsu.epamlab.Constants.AUXILIARY_NUMB_FOR_DECIMAL_MARK_TO_INTEGER_TYPE;

public class DecimalResultFactory extends ResultFactory {
    @Override
    public Result getResultFromFactory(String name, String test, String date, String mark) {
        return new DecimalResult(name, test, date, mark);
    }

    @Override
    public Result getResultFromFactory(String name, String test, Date date, int mark) {
        return new DecimalResult(name, test, date, mark);
    }

    @Override
    public IResultDAO getResultDAO(String fileName) throws IOException {
        return new ResultImplXml(this, fileName);
    }

    @Override
    public double getAverageMark(double mark) {
        return mark / AUXILIARY_NUMB_FOR_DECIMAL_MARK_TO_INTEGER_TYPE;
    }
}
