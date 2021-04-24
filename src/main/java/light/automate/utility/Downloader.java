package light.automate.utility;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
* String version = "83.0.4103.39";
* String fileName = "chromedriver_win32.zip";
* String exeFileName = "chromedriver.exe";
* String platform = "";
* String url = "https://chromedriver.storage.googleapis.com/";
* String downloadUrl = url + version + "/" + fileName;
* String contentType = "application/x-executable";
* File driverZipFile;
* String outputZipFile = "chromedriver_win32";
* String outputExeFilePath = "src/test/resources/libs/drivers";
* String latestUrl = "https://chromedriver.storage.googleapis.com/LATEST_RELEASE"
*/
public class Downloader {
	private String chromeDriverRootUrl = "https://chromedriver.storage.googleapis.com/";
	private String latestChromeVersion = "https://chromedriver.storage.googleapis.com/LATEST_RELEASE";
	private String firefoxDriverRootUrl = "";
	private String version = "latest";
	private String platform = "";
	private String browserName = "chrome";
	private String outputDir = "temp";
	private File downloadedFile;
	
	private String getFullDownloadUrl() {
		// form file name according to browser name and platform
		String fileName = "chromedriver_win32.zip";
		
		// form url according to browser version,name and platform
		return chromeDriverRootUrl + version + "/" + fileName;
	}
	
	
	public Downloader() {}
	
	public Downloader setDriverVersion(String version) {
		this.version = version;
		return this;
	}
	
	public Downloader setPlatform(String platform) {
		this.platform = platform;
		return this;
	}
	
	public Downloader setBrowserName(String browser_name) {
		this.browserName = browser_name;
		return this;
	}
	
	public Downloader download() throws Exception {
		Request request;
		OkHttpClient client = new OkHttpClient();
		Response response = null;
		
		// if version == latest then download latest version
		if (version.toLowerCase().equalsIgnoreCase("latest")){
			request = (new Request.Builder())
				.url(latestChromeVersion)
				.get()
				.addHeader("Content-Type", "application/json")
				.build();
			response = client
				.newCall(request)
				.execute();
			
			if (response.body() == null) {
				throw new Exception("Latest chrome driver url not reachable");
			}
			version = response.body().string();
		}
		
		request = (new Request.Builder())
			.url(getFullDownloadUrl())
			.get()
			.addHeader("Content-Type", "application/x-executable")
			.build();
		
		client = new OkHttpClient();
		response= client
			.newCall(request)
			.execute();
		
		if (!response.isSuccessful()) {
			throw new Exception("Unable to download the driver executable...");
		}
		
		assert response
			.body() != null;
		
		InputStream inputStream = response
			.body()
			.byteStream();
		
		// if output_dir is not set then crete file in temp dir
		downloadedFile = File.createTempFile(version, ".zip");
		downloadedFile.deleteOnExit();
		
		OutputStream outputStream = new FileOutputStream(downloadedFile);
		
		IOUtils.copy(inputStream, outputStream);
		
		outputStream.close();
		inputStream.close();
		response.close();
		
		return this;
	}
	
	public Downloader setOutputDir(String output_dir) {
		this.outputDir = output_dir;
		return this;
	}
	
	public File getDownloadedFile() {
		return downloadedFile;
	}
	
	public boolean delete() {
		return downloadedFile.delete();
	}
}
