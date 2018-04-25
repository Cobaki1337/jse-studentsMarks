package by.gsu.epamlab.xml;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factory.ResultFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Iterator;

public class ResultsSAXBuilder {
    private Iterator<Result> iterator;
    private ResultsHandler handler;
    private XMLReader reader;

    public ResultsSAXBuilder(ResultFactory resultFactory) throws SAXException {
        handler = new ResultsHandler(resultFactory);
        reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(handler);
    }

    public Iterator<Result> getIterator() {
        return iterator;
    }

    public void buildListResults(String fileName) throws IOException, SAXException {
        reader.parse(fileName);
        iterator = handler.getIterator();
    }
}
