package light.automate.reports;

public class Reporter {
	public static TextFileBuilder textFileBuilder() {
		TextFileBuilder.TextFile t = new TextFileBuilder().create();
		return new TextFileBuilder();
	}
}
