package light.automation.utils;

import light.automation.configuration.Configuration;

public class ClassUtils {
    public static String getDataFilePath( Class clazz ) {

        return Configuration.configurationPath + "";
    }

    public static String getAppName( Class clazz ) {
        String fullName = clazz.getPackage().getName();
        return fullName.substring( 0, fullName.indexOf("."));
    }

    public static String getTestName( Class clazz ) {
        return clazz.getSimpleName();
    }
}
