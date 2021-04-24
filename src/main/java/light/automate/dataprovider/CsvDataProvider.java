package light.automate.dataprovider;

import org.testng.annotations.DataProvider;

public class CsvDataProvider {
	@DataProvider(name = "simple_map_data")
	public Object[][] simpleMapData() {
		return new Object[][] {
			{"Apple"},
			{"Banana"}
		};
	}
}
