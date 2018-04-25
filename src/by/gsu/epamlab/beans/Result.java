package by.gsu.epamlab.beans;

import java.sql.Date;

import static by.gsu.epamlab.Constants.DELIMITER;

public class Result {
    private String name;
    private String test;
    private Date date;
    private int mark;

    public Result(){
        super();
    }

    public Result(String name, String test, Date date, int mark) {
        this.name = name;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result (String name, String test, String date, String mark){
        this(name, test, Date.valueOf(date), Integer.valueOf(mark));
    }

    public Result (String name, String test, String date, int mark){
        this(name, test, Date.valueOf(date), mark);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    protected String printMark(){
        return String.valueOf(mark);
    }

    @Override
    public String toString() {
        return name + DELIMITER + test + DELIMITER + date + DELIMITER + printMark();
    }
}
