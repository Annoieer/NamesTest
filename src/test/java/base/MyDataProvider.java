package base;

import org.testng.annotations.DataProvider;

public class MyDataProvider {

    @DataProvider(name="names")
    public static Object[][] dataProviderMethod() {
        return new Object[][] { { "?name=mikhail" }, { "?name=liza" }, { "?name=kate" } };
    }
}
