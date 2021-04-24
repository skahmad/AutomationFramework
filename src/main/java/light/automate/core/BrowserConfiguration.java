package light.automate.core;

import java.util.Properties;

public class BrowserConfiguration {
	protected Properties browserConfig;
	
	public BrowserConfiguration(Properties properties) {
		this.browserConfig = properties;
	}
	
	/**
	 * Read Browser Name from Configuration file and returns
	 * if no property found in properties file then it return default "chrome"
	 * @return String
	 */
	public String name() {
		return browserConfig.getProperty("name","chrome");
	}
	
	/**
	 * Read Driver File Name and returns
	 * if not found then default return "chromedriver.exe"
	 * @return String
	 */
	public String driverName() {
		return browserConfig.getProperty("driver", "chromedriver.exe");
	}
	
	/**
	 * This method returns the path of the driver from browser configuration file
	 * @return String
	 */
	public String driverPath() {
		return browserConfig.getProperty("driver_path", "src/test/resources/common/drivers/");
	}
	
	/**
	 * This method return the full path of the driver include the driver name
	 * @return String
	 */
	public String driverFullPath() {
		return browserConfig.getProperty("driver_path", "src/test/resources/common/drivers/") + driverName();
	}
	
	/**
	 * Read Browser size form config file and returns
	 * @return String
	 */
	public String size() {
		return browserConfig.getProperty("size", "maximize");
	}
}
