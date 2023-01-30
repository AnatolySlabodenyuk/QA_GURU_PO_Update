package guru.qa.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
    @Test
    @Tag("one_property")
    void simpleProperty4Test() {
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName);
        // gradle clean one_property_test
        // firefox

        // gradle clean one_property_test -Dbrowser=safari
        // safari
    }
}
