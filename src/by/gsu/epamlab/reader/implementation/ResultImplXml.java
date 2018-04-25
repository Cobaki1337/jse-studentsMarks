package by.gsu.epamlab.reader.implementation;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factory.ResultFactory;
import by.gsu.epamlab.reader.IResultDAO;
import by.gsu.epamlab.xml.ResultsSAXBuilder;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.Iterator;

public class ResultImplXml implements IResultDAO {

    private Iterator<Result> iterator;
    private ResultsSAXBuilder builder;

    public ResultImplXml(ResultFactory resultFactory, String fileName) throws IOException {
        try {
            builder = new ResultsSAXBuilder(resultFactory);
            builder.buildListResults(fileName);
            iterator = builder.getIterator();
        } catch (SAXException e) {
            System.err.println("Problem with parsing.");
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Result nextResult() {
        return iterator.next();
    }

    @Override
    public boolean hasResult() {
        return iterator.hasNext();
    }

    @Override
    public void closeReader() throws IOException {
        iterator = null;
        if (builder != null){
            builder = null;
        }
    }
}
