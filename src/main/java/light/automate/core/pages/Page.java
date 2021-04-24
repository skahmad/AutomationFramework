package light.automate.core.pages;

import light.automate.core.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class represent a page or group html elements
 */
public abstract class Page<T> {
    protected Browser browser;
    protected Actions actions;
    
    public Page(Browser browser){
        this.browser = browser;
        actions = browser.actions();//new Actions(browser.driver());
        PageFactory.initElements(browser.driver(),this);
    }
    
    public T wait(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (T)this;
    }

    public T wait(WebElement w) {
        WebDriverWait webDriverWait = new WebDriverWait(browser.driver(),5);
        webDriverWait.until(ExpectedConditions.visibilityOf(w));
        return (T)this;
    }
    
    public T scroll(WebElement element) {
        browser.js().executeScript("arguments[0].scrollIntoView()", element);
        return (T)this;
    }
}
