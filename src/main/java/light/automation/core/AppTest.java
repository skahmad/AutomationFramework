package light.automation.core;

import java.io.IOException;

public class AppTest extends App{
    public AppTest(){
        try {
            super.loadConfiguration();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
    public Browser getBrowser() {
        return browser;
    }
}
