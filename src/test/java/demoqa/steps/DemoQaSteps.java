package demoqa.steps;

import demoqa.DemoQa;
import io.cucumber.java8.En;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;

public class DemoQaSteps implements En {
	DemoQa app = new DemoQa();
	
	
//	@Given("open the app")
//	public void open_the_app() throws Exception {
//		app.onTestStart();
//	}
//
//	@Then("close the app")
//	public void close_the_app() throws Exception {
//		app.onTestEnd();
//	}

	public DemoQaSteps() {
		Given("open the app", ()->{
			app.onTestStart();
		});
		
		Then("close the app", ()-> {
			app.onTestEnd();
		});
		
	}
	
}
