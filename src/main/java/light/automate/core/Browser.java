package light.automate.core;

import light.automate.utility.Downloader;
import light.automate.utility.Zipper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Browser {
    protected WebDriver driver;
    protected Actions actions;
    protected JavascriptExecutor js;
    protected BrowserConfiguration configuration;
    private String currentTab;
    private int tabCounter = 1;
    private Stack<String> tabs;
    private String name;
    
    /**
     * Private Methods
     */
    private void createInstance() throws SessionNotCreatedException{
        if (driver != null) {
            return;
        }
        
        switch (name.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", configuration.driverFullPath());
                driver = new ChromeDriver();
                break;
                
            case "firefox":
                System.setProperty("webdriver.gecko.driver", configuration.driverFullPath());
                driver = new FirefoxDriver();
                break;
        }
    }
    
    /**
     * Constructors
     */
    public Browser(App app) {
        // read config from file
        configuration = new BrowserConfiguration(app.configuration.browser());
        name = configuration.name();
        
        //tabs = new HashMap<String,String>();
        tabs = new Stack<>();
    }

    //  PUBLIC METHODS START
    
    /**
     * This method return the instance of the webdriver
     * @return WebDriver
     */
    public WebDriver driver() {
        return driver;
    }
    
    /**
     * This method return the instance of the actions
     * @return Actions
     */
    public Actions actions() {
        return actions;
    }
    
    /**
     * This method return the instance of the java script executor
     * @return JavaScriptExecutor
     */
    public JavascriptExecutor js() {
        return js;
    }

    /**
     * create new web driver instance
     * and initialize actions, java script executor
     * @return Browser
     * working fine
     */
    public Browser create() throws Exception {
        try {
            createInstance();
        }catch (SessionNotCreatedException e) {
            downloadDriver();
            createInstance();
        }
        currentTab = driver.getWindowHandle();
        tabs.clear();
        tabs.add(currentTab);

        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        
        // setting all configuration from properties file
        //1. set size
        //setSize();

        // set default wait time for the element
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return this;
    }
    
    /**
     * Set Browser name to open browser according to name
     * @param browser_name
     * @return Browser
     */
    public Browser setName(String browser_name) {
        this.name = browser_name;
        return this;
    }

    /**
     * Open the url in the browser
     * @param url
     * @return Browser
     * working fine
     */
    public Browser open(String url) {
        driver.navigate().to(url);
        setSize();
        return this;
    }
    
    /**
     * Set Browser Size Maximize
     * it returns a browser instance
     * @return Browser
     */
    public Browser maximize() {
        driver.manage().window().maximize();
        return this;
    }
    
    /**
     * Set Browser Size Full Screen
     * it returns a browser instance
     * @return Browser
     */
    public Browser fullScreen() {
        driver.manage().window().fullscreen();
        return this;
    }
    
    /**
     * Close all Browser Window
     * it returns an Instance of the Browser
     * @return Browser
     */
    public Browser close() {
        tabs.forEach((window)-> {
            driver.switchTo().window(window);
            driver.close();
        });
        driver = null;
        js = null;
        actions = null;
        return this;
    }

    // TAB METHODS
    
    /**
     * Create new Tab
     * @return Browser
     * working file
     */
    public Browser newTab() {
        createAndStoreTab();
        return this;
    }
    
    /**
     * Create new tab and switch to the tab
     * and open the url
     * @param url
     * @return Browser
     * working fine
     */
    public Browser newTab(String url) {
        createAndStoreTab();
        driver.switchTo().window(currentTab);
        open(url);
        return this;
    }

    /**
     * Switch Tab to the particular position
     * @param position
     * @return Browser
     * working fine
     */
    public Browser switchTab(int position) {
        currentTab = tabs.get(position);
        driver.switchTo().window(currentTab);
        return this;
    }
    
    /**
     * Switch Tab to the next tab of the current tab
     * @return Browser
     * working fine
     */
    public Browser switchNextTab() {
        int position = tabs.indexOf(currentTab);
        if (position != tabs.size()) {
            currentTab = tabs.get(position+1);
            driver.switchTo().window(currentTab);
        }
        return this;
    }
    
    /**
     * Switch Tab to the previous tab of the current tab
     * @return Browser
     * working fine
     */
    public Browser switchPrevTab() {
        int position = tabs.indexOf(currentTab);
        if (position != 0) {
            currentTab = tabs.get(position-1);
            driver.switchTo().window(currentTab);
        }
        return this;
    }
    
    
    // todo test method for download chrome driver
    public void downloadDriver() throws Exception {
        File driverFile = new File(configuration.driverFullPath());
    
        // close all chrome driver instance
        if (isProcessRunning(configuration.name()))
            killProcess(configuration.name());
        
        Downloader downloader = new Downloader();
        downloader
            .setDriverVersion("83.0.4103.39")
            .setBrowserName("chrome")
            .download();
        
        driverFile.delete();
        
        //extract from zip
        Zipper zipper = new Zipper(downloader.getDownloadedFile(), configuration.driverPath());
        zipper.unzip();
    }
    
    
    // PRIVATE METHODS STARTS
    
    /**
     * Create a new Tab and Store the tab id in the stack
     * it returns the newly created tab id
     * @return String
     */
    private String createAndStoreTab() {
        Set<String> before = driver().getWindowHandles();
        js.executeScript("window.open()", 0);
        Set<String> after = driver.getWindowHandles();
        after.removeAll(before);
        String[] array = after.toArray(new String[0]);
        currentTab = array[0];
        tabs.add(currentTab);
        
        return currentTab;
    }
    
    /**
     * Checking chrome driver process is running or not
     * if running then returns true else returns false
     * @param service_name
     * @return boolean
     */
    private boolean isProcessRunning(String service_name) {
        String taskList = "tasklist";
        Process p = null;
        String line;
        BufferedReader reader;
        boolean found = false;
        try {
            p = Runtime
                .getRuntime()
                .exec(taskList);
            
            reader = new BufferedReader(new InputStreamReader(
                p.getInputStream()));
            
            while ((line = reader.readLine()) != null) {
                if (line.contains(service_name)) {
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;
    }
    
    /**
     * Kill process
     * @param service_name
     */
    private void killProcess(String service_name) {
        String killCommand = "taskkill /f /im ";
        try {
            Runtime.getRuntime().exec( killCommand + service_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete existing driver file from dir
     * if file deleted successfully then returns true else false
     * @param filePath
     * @return boolean
     */
    private boolean deleteFile(String filePath) {
        File driverFile = new File(filePath);
        return driverFile.delete();
    }
    
    /**
     * Get Browser Size from Config File and Set Browser Size
     */
    private void setSize() {
        String size = configuration.size();
        switch (size.toLowerCase()) {
            case "maximize":
                driver.manage().window().maximize();
                break;
                
            case "full_screen":
                driver.manage().window().fullscreen();
                break;
        }
    }
    
    /**
     * Refresh the current loaded web page
     */
    public void refresh() {
        driver.navigate().refresh();
    }
}
