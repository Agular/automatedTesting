import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ApiTests;
import tests.ClassTests;
import tests.LogicTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ApiTests.class,
        LogicTests.class,
        ClassTests.class
})

public class TestSuite {

}
