package by.gsu.epamlab.reader;

import by.gsu.epamlab.beans.Result;

import java.io.IOException;

public interface IResultDAO {
    Result nextResult() throws IOException;
    boolean hasResult() throws IOException;
    void closeReader() throws IOException;
}
