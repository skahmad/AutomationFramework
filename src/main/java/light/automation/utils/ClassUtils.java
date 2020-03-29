package light.automation.utils;

import light.automation.configuration.Configuration;

public class ClassUtils {
    public static String getDataFilePath( Class clazz ) {

        return Configuration.dataPath + getAppName( clazz ) + "/" + getTestClassName( clazz );
    }

    public static String getAppName( Class clazz ) {
        String fullName = clazz.getPackage().getName();
        return fullName.substring( 0, fullName.indexOf("."));
    }

    public static String getTestClassName( Class clazz ) {
        return clazz.getSimpleName();
    }


}
