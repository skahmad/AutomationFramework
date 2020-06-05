package light.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Page<T> {
	protected WebDriver driver;
	
	public T load() {
		PageFactory.initElements ( driver, (T)this );
		return (T)this;
	}
	
	protected Page waitForElement(WebElement e){
		return this;
	}
	public T wait( int time ) {
		try {
			Thread.sleep ( time * 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}
		return (T) this;
	}
}
