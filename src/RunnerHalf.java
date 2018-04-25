import by.gsu.epamlab.RunnerLogic;
import by.gsu.epamlab.factory.HalfResultFactory;
import by.gsu.epamlab.factory.ResultFactory;

public class RunnerHalf {
    public static void main(String[] args) {
        ResultFactory resultFactory = new HalfResultFactory();
        RunnerLogic.execute(resultFactory, "src/results3.csv");
    }
}
