package by.gsu.epamlab.beans;

import java.sql.Date;

import static by.gsu.epamlab.Constants.*;

public class DecimalResult extends Result {

    public DecimalResult(String name, String test, String date, String mark) {
        super(name, test, date, (int)(Double.valueOf(mark) * AUXILIARY_NUMB_FOR_DECIMAL_MARK_TO_INTEGER_TYPE));
    }

    public DecimalResult(String name, String test, Date date, int mark) {
        super(name, test, date, mark);
    }

    @Override
    protected String printMark() {
        return getMark()  /AUXILIARY_NUMB_FOR_DECIMAL_MARK_TO_INTEGER_TYPE + "." +
                getMark() % AUXILIARY_NUMB_FOR_DECIMAL_MARK_TO_INTEGER_TYPE;
    }
}
