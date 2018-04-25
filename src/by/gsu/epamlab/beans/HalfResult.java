package by.gsu.epamlab.beans;

import java.sql.Date;

import static by.gsu.epamlab.Constants.*;

public class HalfResult extends Result {

    public HalfResult(String name, String test, String date, String mark) {
        super(name, test, date, (int)(Double.valueOf(mark) * AUXILIARY_NUMB_FOR_HALF_MARK_TO_INTEGER_TYPE));
    }

    public HalfResult(String name, String test, Date date, int mark) {
        super(name, test, date, mark);
    }

    @Override
    protected String printMark() {
        if ((getMark() % AUXILIARY_NUMB_FOR_HALF_MARK_TO_INTEGER_TYPE) == 0){
            return String.valueOf(getMark() / AUXILIARY_NUMB_FOR_HALF_MARK_TO_INTEGER_TYPE);
        }else {
            return getMark() / AUXILIARY_NUMB_FOR_HALF_MARK_TO_INTEGER_TYPE + REMAINDER_OF_THE_HALF_MARK;
        }
    }
}
