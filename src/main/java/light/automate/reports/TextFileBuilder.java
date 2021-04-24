package light.automate.reports;

public class TextFileBuilder {
	/*
	* Declare member variable
	*/
	
	
	/*
	* Constructors
	*/
	public TextFileBuilder() {
	
	}
	
	/*
	 * Public Methods
	 */
	public void setPath(String path) {}
	public void setFileName(String fileName) {}
	public void writeData() {}
	public void writeData(String data) {}
	public void appendData(String data) {}
	
	public TextFile create() {
		return new TextFile();
	}
	
	public class TextFile {
		/*
		 * Member variable
		 */
		protected String fileName;
		private String path;
		
		/*
		 * Constructors
		 */
		protected TextFile() {
			fileName = "";
			path = "";
		}
		
		/*
		 * Private Methods
		 */
		public boolean verify() {
			return false;
		}
		
		/*
		 * Public Methods
		 */
	}
}
