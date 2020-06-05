package light.automation.configuration;

public interface BrowserConfiguration {
	static enum BrowserSize {
		Maximize,
		Minimize,
		Normal,
		Custom,
		FullScreen
	};
	
	static enum BrowserName {
		Chrome,
		Firefox,
		Opera,
		Safari,
		Ie
	};
	
	String getBrowserConfigFilePath();
	BrowserSize getBrowserSize() ;
	BrowserName getBrowserName() ;
	String getDriver() ;
	
	String rootUrl();
}
