package light.automation.configuration;

import light.automation.core.App;

public class Configuration implements BrowserConfiguration,TestConfiguration {
    public static String configurationPath = "resources/configuration/";
    public static String dataPath = "resources/data/";

    public Configuration( App app ) {
        System.out.println("creating configuration");
        System.out.println("Browser config file : " + configurationPath + app.getAppName() + "/browser.properties");
        System.out.println("Test config file : " + configurationPath + app.getAppName() + "/test.properties");
        System.out.println("Browser config file : " + dataPath + app.getAppName() );
    }
}
