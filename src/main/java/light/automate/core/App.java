package light.automate.core;

import light.automate.reports.Reporter;
import java.io.IOException;

public abstract class App {
    protected String rootUrl = "";
    protected Browser browser;
    protected Reporter reporter;
    protected Configuration configuration;

    /**
     * Constructors
     */
    public App() {
        
        // 1. init configuration first
        // todo handle exception, and stop test if exception found, clean everything
        try {
            configuration = new Configuration(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        browser = new Browser(this);
        
    }

    /**
     * Public Methods
     */
    public App setBrowser(Browser browser) {
        this.browser = browser;
        return this;
    }

    public App setReporter(Reporter reporter) {
        this.reporter = reporter;
        return this;
    }

    public App initialize() {
        return this;
    }

    public App start() throws Exception {
        if (rootUrl.length() == 0)
            throw new Exception("Please set the root url in OnStart!");
        
        browser
            .create()
            .open(this.rootUrl);
        
        return this;
    }

    public App close() {
        browser.close();
        return this;
    }
    
    /**
     * Set the base url of the Application
     * @param url
     * @return {App} object  of this class
     */
    public App setRootUrl(String url) {
        this.rootUrl = url;
        return this;
    }
    
    /**
     * wait for second in a service level code
     * @param second how much time want to wait
     */
    public void wait(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public abstract void onTestEnd() throws Exception;
    public abstract void onTestStart() throws Exception;
    public abstract void onTestClassStart() throws Exception;
    public abstract void onTestClassEnd() throws Exception;
}
