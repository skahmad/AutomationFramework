package light.automate.dataprovider;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;

public class JsonDataProvider {
	@DataProvider(name = "json_map_data")
	private Object[] jsonMapData(Method method) throws IOException {
		String cName = method.getDeclaringClass().getCanonicalName();
		int in = cName.indexOf(".") +1;

		String dir_name = cName.substring(in).replace(".","/");
		String projectName = cName.substring(0, in-1);
		String dir = "src/test/resources/" + projectName + "/data/" + dir_name + "/";


		String json_file = dir + method.getName() + ".json";


		StringBuilder js_string = new StringBuilder();

		BufferedReader br = new BufferedReader(new FileReader(json_file));
		String line ;
		while((line = br.readLine())!=null){
			js_string.append(line);
		}
		br.close();

		String js = js_string.toString();
		JsonArray jo = new JsonParser().parse(js).getAsJsonArray();
		
		Object[][] data = new Object[jo.size()][];
		for (int i = 0; i < jo.size(); i++) {
			data[i] = new Object[]{jo.get(i)};
		}
		return data;
	}
}
