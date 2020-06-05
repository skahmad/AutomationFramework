package light.automation.core;

import light.automation.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
    private Configuration configuration;
    private WebDriver driver;
    
    private int fluentWait = 5;
    
    private void setup() {
        switch ( configuration.getBrowserName () ) {
            case Chrome:
                System.setProperty("webdriver.chrome.driver",configuration.getDriver ());
                break;
            case Firefox:
                System.setProperty("webdriver.gecko.driver",configuration.getDriver ());
                break;
            default:
                System.setProperty("webdriver.chrome.driver", configuration.getDriver ());
        }
    }
    private void initDriver() {
        System.out.println ("### opening browser");
    
        switch ( configuration.getBrowserName () ) {
            case Chrome:
                driver = new ChromeDriver ();
                break;
            case Firefox:
                driver = new FirefoxDriver ();
                break;
            default:
                driver = new ChromeDriver ();
        }
    }
    private void setSize() {
        System.out.println ("### Setting Browser Window Size according to configuration");
        
        switch (configuration.getBrowserSize ()) {
            case Maximize: driver.manage ().window ().maximize (); break;
            case FullScreen: driver.manage ().window ().fullscreen (); break;
            default: driver.manage ().window ().maximize ();
        }
    }
    
    public Browser( App app ) {
        System.out.println("creating browser");
        app.configuration.getBrowserConfigFilePath ();
    }
    public Browser(Configuration configuration ){
        this.configuration = configuration;
        setup ();
    }
    
    public void open() {
        // creating new WebDriver according to the configuration
        initDriver ();
        
        System.out.println ("### opening url : " + configuration.rootUrl () );
        driver.navigate ().to ( configuration.rootUrl () );
        
        // setting browser size
        setSize ();
    }
    
    public void close() {
        System.out.println ("### closing browser");
        driver.close ();
    }
    
    public void wait( int second ) {
        System.out.println ("### waiting for : " + second + " second");
        try {
            Thread.sleep ( second * 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }
    
    public void setFluentWait( int second ) {
        fluentWait = second;
        System.out.println ("### Fluent Wait is : " + second + " second");
    }
    
    public WebDriver getNativeDriver() {
        return driver;
    }
}
