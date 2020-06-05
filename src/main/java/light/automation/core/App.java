package light.automation.core;

import light.automation.configuration.Configuration;
import light.automation.utils.ClassUtils;

import java.io.IOException;

public class App {
    protected Browser browser;
    Configuration configuration;

    public App() {
        String name = ClassUtils.getAppName( this.getClass() );
        System.out.println("Creating App : " + name);
    }
    
    public void loadConfiguration() throws IOException {
        System.out.println("Loading AppConfiguration...");
        
        configuration = new Configuration( this );
        browser = new Browser( configuration );
    }
    
    public String getAppName() {
        return ClassUtils.getAppName(this.getClass());
    }
    public String getTestClassName() { return ClassUtils.getTestClassName(this.getClass()); }
}
