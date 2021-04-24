package light.automate.core;

import light.automate.utility.Path;

import java.io.*;
import java.util.Properties;

public class Configuration {
	private final Properties browser;
	private Properties test;
	private Properties report;
	private final App app;
	
	/**
	 * Initialize Browser Config Properties,
	 * Test Config properties instance
	 * and Report Config properties instance
	 * it needs an app instance as parameter
	 * without app configuration can not create
	 * @param app
	 * @throws IOException
	 */
	public Configuration(App app) throws IOException {
		this.app = app;
		browser = new Properties();
		browser.load(new InputStreamReader(new FileInputStream(new File(getBrowserConfigFile()))));
	}
	
	/**
	 * This method return browser configuration file path
	 * @return String
	 */
	public String getBrowserConfigFile() {
		return Path.getConfigPath(app) + "browser.properties";
	}
	
	/**
	 * this method returns test configuration file path of the app
	 * @return String
	 */
	public String getTestConfigFile() {
		return Path.getConfigPath(app) + "test.properties";
	}
	
	/**
	 * this method return browser properties instance
	 * @return Properties
	 */
	public Properties browser() {
		return browser;
	}
}
