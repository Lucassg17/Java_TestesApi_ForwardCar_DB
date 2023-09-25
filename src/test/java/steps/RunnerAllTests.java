package steps;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ={
                "src/test/resources/features/forwardCar.feature"
                //"src/test/resources/features/forwardCar2.feature"
        },
        tags = "@Teste002"
)
public class RunnerAllTests {
}
