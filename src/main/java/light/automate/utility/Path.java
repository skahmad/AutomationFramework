package light.automate.utility;

import light.automate.core.App;

public class Path {
	public final static String resourcePath = "src/test/resources/";
	public final static String driverPath = "src/test/resources/common/drivers/";
	
	public static String getConfigPath(App app) {
		String appName = app.getClass().getSimpleName();
		String packageName = app.getClass().getCanonicalName();
		packageName = packageName.substring(0, packageName.indexOf("."));
		return resourcePath + packageName + "/config/";
	}
	public static String getDataPath(App app) {
		String appName = app.getClass().getPackage().getName();
		return resourcePath + appName + "/data/";
	}
}
