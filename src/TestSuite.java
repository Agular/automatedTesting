import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ApiTests;
import tests.LogicTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ApiTests.class,
        LogicTests.class
})

public class TestSuite {

}
