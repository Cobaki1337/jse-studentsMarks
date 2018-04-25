import by.gsu.epamlab.RunnerLogic;
import by.gsu.epamlab.factory.ResultFactory;

public class RunnerInt {
    public static void main(String[] args) {
        ResultFactory resultFactory = new ResultFactory();
        RunnerLogic.execute(resultFactory, "src/results1.csv");
    }
}
