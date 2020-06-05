package light.automation.configuration;

import light.automation.core.App;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration implements BrowserConfiguration,TestConfiguration {
    public static String configurationPath = "resources/configuration/";
    public static String dataPath = "resources/data/";
    public static String configPath = "resources/";
    
    App app = null;
    private Properties browserProperties;

    public Configuration( App app ) throws IOException {
        System.out.println ( "creating configuration" );
        System.out.println ( "Browser config file : " + configurationPath + app.getAppName () + "/browser.properties" );
        System.out.println ( "Test config file : " + configurationPath + app.getAppName () + "/test.properties" );
        System.out.println ( "Browser config file : " + dataPath + app.getAppName () );
        
        loadBrowserConfiguration ();
    }
    private void loadBrowserConfiguration() throws IOException {
        browserProperties = new Properties (  );
        String fileName = configurationPath + app.getAppName () + "/browser.properties";
        InputStream in = new FileInputStream ( fileName );
        browserProperties.load ( in );
    }
    
    // implement browser config method
    @Override
    public String getBrowserConfigFilePath() {
        return configurationPath + app.getAppName() + "/browser.properties";
    }
    
    @Override
    public BrowserSize getBrowserSize() {
        String size  = browserProperties.getProperty ( "size", "maximize" );
        BrowserSize browserSize;
        switch ( size.toLowerCase () ) {
            case "maximize" : browserSize = BrowserSize.Maximize; break;
            case "minimize" : browserSize = BrowserSize.Minimize; break;
            case "normal" : browserSize = BrowserSize.Normal; break;
            case "full" : browserSize = BrowserSize.FullScreen; break;
            case "custom" : browserSize = BrowserSize.Custom; break;
            default: browserSize = BrowserSize.Maximize;
        }
        return browserSize;
    }
    
    @Override
    public BrowserName getBrowserName() {
        String name  = browserProperties.getProperty ( "name", "chrome" );
        BrowserName browserName;
        switch ( name.toLowerCase () ) {
            case "chrome" : browserName = BrowserName.Chrome; break;
            case "firefox" : browserName = BrowserName.Firefox; break;
            case "opera" : browserName = BrowserName.Opera; break;
            case "safari" : browserName = BrowserName.Safari; break;
            case "ie" : browserName = BrowserName.Ie; break;
            default: browserName = BrowserName.Chrome;
        }
        return browserName;
    }
    
    @Override
    public String getDriver() {
        return browserProperties.getProperty ( "driver",configPath + "driver/chromedriver.exe" );
    }
    
    @Override
    public String rootUrl() {
        return browserProperties.get ( "url" ).toString ();
    }
    
}
