package by.gsu.epamlab.reader.implementation;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factory.ResultFactory;
import by.gsu.epamlab.reader.IResultDAO;

import java.io.*;

import static by.gsu.epamlab.Constants.*;

public class ResultImplCsv implements IResultDAO {
    private BufferedReader reader;
    private ResultFactory resultFactory;

    public ResultImplCsv(ResultFactory resultFactory, String fileName) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(new File(fileName)));
        this.resultFactory = resultFactory;
    }

    @Override
    public Result nextResult() throws IOException {
        String[] line = reader.readLine().split(DELIMITER);
        return resultFactory.getResultFromFactory(line[LINE_0], line[LINE_1], line[LINE_2], line[LINE_3]);
    }

    @Override
    public boolean hasResult() throws IOException {
        return reader.ready();
    }

    @Override
    public void closeReader() throws IOException {
        if (reader != null){
            reader.close();
        }
    }
}
