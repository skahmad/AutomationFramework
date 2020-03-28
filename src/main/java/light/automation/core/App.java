package light.automation.core;

import light.automation.configuration.Configuration;
import light.automation.utils.ClassUtils;

public class App {
    Browser browser;
    Configuration configuration;


    public App() {
        String name = ClassUtils.getAppName( this.getClass() );
        System.out.println("Creating App : " + name);
    }
    public void loadConfiguration() {
        System.out.println("Loading AppConfiguration...");
        browser = new Browser( this );
        configuration = new Configuration( this );
    }


    public String getAppName() {
        return ClassUtils.getAppName(this.getClass());
    }
    public String getTestName() { return ClassUtils.getTestName(this.getClass()); }
}
