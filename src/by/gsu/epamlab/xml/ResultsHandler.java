package by.gsu.epamlab.xml;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factory.ResultFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import static by.gsu.epamlab.Constants.INDEX_0;
import static by.gsu.epamlab.Constants.INDEX_1;
import static by.gsu.epamlab.Constants.INDEX_2;

public class ResultsHandler extends DefaultHandler {

    private ResultFactory resultFactory;
    private Deque<Result> results = new LinkedList<>();
    private ResultEnum currentEnum ;
    private String login;

    private static enum ResultEnum{
        RESULTS, RESULT, STUDENT, LOGIN, TEST, TESTS;
    }

    public ResultsHandler(ResultFactory resultFactory) {
        this.resultFactory = resultFactory;
    }


    public Iterator<Result> getIterator(){
        return results.iterator();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST){
            results.add(resultFactory.getResultFromFactory(login, attributes.getValue(INDEX_0), attributes.getValue(INDEX_1),
                    attributes.getValue(INDEX_2)));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentEnum == ResultEnum.LOGIN){
            String str = new String(ch, start, length).trim();
            if (!str.isEmpty()){
                login = str;
            }
        }
    }
}
