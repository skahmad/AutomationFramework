package demoqa;

import light.automate.core.App;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DemoQa extends App {
    @Override
    @AfterMethod
    public void onTestEnd() throws Exception {
        close();
    }

    @Override
    @BeforeMethod
    public void onTestStart() throws Exception {
        this
            .initialize()
            //.setRootUrl("https://demoqa.com")
	        .setRootUrl("https://demo.applitools.com/index.html")
            .start();
    }

    @Override
    public void onTestClassStart() throws Exception {

    }

    @Override
    public void onTestClassEnd() throws Exception {

    }
}
