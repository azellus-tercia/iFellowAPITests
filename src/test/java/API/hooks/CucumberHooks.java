package API.hooks;

import io.cucumber.java.Before;

public class CucumberHooks {
    boolean beforeAll = false;

    @Before
    public void setUp() {
        if (!beforeAll) {
            ApiHooks.setUp();
            beforeAll = true;
        }
    }
}
