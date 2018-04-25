import by.gsu.epamlab.RunnerLogic;
import by.gsu.epamlab.factory.DecimalResultFactory;
import by.gsu.epamlab.factory.ResultFactory;

public class RunnerDecimal {
    public static void main(String[] args) {
        ResultFactory resultFactory = new DecimalResultFactory();
        RunnerLogic.execute(resultFactory, "src/results.xml");
    }
}
