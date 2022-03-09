import base.Autotest;
import base.MyDataProvider;
import config.SystemPropertyProvider;
import connect.Response;
import json.NameInfo;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.*;

@Test
public class ApiTest extends Autotest {

    @Test (groups = {"names"}, dataProvider = "names", dataProviderClass = MyDataProvider.class)
    public void nameInfoTest(String name) {
        open(SystemPropertyProvider.getBaseUrl() + name);
        Response response = new Response(SystemPropertyProvider.getBaseUrl() + name);
        NameInfo nameInfo = response.getInfo();

        if (response.responseCode != 200) {
            fail("Ожидался ответ 200, а пришёл " + response.responseCode);
        }

        System.out.println("name = " + name.substring(6));
        System.out.println("country: ");
        for (int i = 0; i < nameInfo.country.size(); i++) {
            System.out.println("  country_id: " + nameInfo.country.get(i).country_id);
            System.out.println("  probability: " + nameInfo.country.get(i).probability);
        }
        System.out.println();
    }

    @Test(groups = {"errors"})
    public void NameErrorTest() {
        String name = "name=kate";
        open(SystemPropertyProvider.getBaseUrl() + name);
        Response response = new Response(SystemPropertyProvider.getBaseUrl() + name);
        NameInfo nameInfo = response.getInfo();

        if (response.responseCode != 404) {
            fail("Ожидался ответ 404, а пришёл " + response.responseCode);
        }

        assertNull(nameInfo);
    }

    @Test(groups = {"errors"})
    public void ApiKeyErrorTest() {
        String name = "?name=kate&apikey=1111";
        open(SystemPropertyProvider.getBaseUrl() + name);
        Response response = new Response(SystemPropertyProvider.getBaseUrl() + name);
        NameInfo nameInfo = response.getInfo();

        if (response.responseCode != 401) {
            fail("Ожидался ответ 401, а пришёл " + response.responseCode);
        }

        assertNull(nameInfo);
    }
}
