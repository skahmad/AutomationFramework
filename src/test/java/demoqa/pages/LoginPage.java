package demoqa.pages;

import demoqa.DemoQa;
import io.cucumber.java.en.Then;
import light.automate.core.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page<LoginPage> {
	@FindBy(id = "username")
	private WebElement usernameField;
	
	@FindBy(id = "password")
	private WebElement passwordField;
	
	@FindBy(id = "log-in")
	private WebElement loginButton;
	
	public LoginPage() {
		super(DemoQa.getBrowser());
	}
	
	@Then("write user name {string}")
	public LoginPage writeUserName(String username) {
		actions
			.moveToElement(usernameField)
			.click()
			.sendKeys(username)
			.build()
			.perform();
		
		return this;
	}
	
	@Then("write password {string}")
	public LoginPage write_password(String password) {
		actions
			.moveToElement(passwordField)
			.click()
			.sendKeys(password)
			.build()
			.perform();
		
		return this;
	}
	
	@Then("click on login button")
	public void click_on_login_button() {
		actions
			.moveToElement(loginButton)
			.click()
			.build()
			.perform();
	}
	
	@Then("validate login success {string}")
	public void validate_login_success(String string) {
		System.out.println("@@@ asserting value");
	}
	
}
