package light.automate.utility;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zipper {
	private File inputFile;
	private String outputDir;
	private int bufferSize = 4096;
	
	public Zipper(String input_file_path, String output_dir) {
		inputFile = new File(input_file_path);
		outputDir = output_dir;
	}
	public Zipper(File input_file, String output_dir) {
		inputFile = input_file;
		outputDir = output_dir;
	}
	
	public File getInputFile() {
		return inputFile;
	}
	public Zipper unzip() throws IOException {
		
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(inputFile));
		ZipEntry entry = zipInputStream.getNextEntry();
		while (entry != null) {
			String filePath = outputDir + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
				byte[] bytesIn = new byte[bufferSize];
				int read = 0;
				while ((read = zipInputStream.read(bytesIn)) != -1) {
					bos.write(bytesIn, 0, read);
				}
				bos.close();
			} else {
				File dir = new File(filePath);
				dir.mkdir();
			}
			zipInputStream.closeEntry();
			entry = zipInputStream.getNextEntry();
		}
		zipInputStream.close();
		
		return this;
	}
	public Zipper setInputFile(File file_name) {
		inputFile = file_name;
		return this;
	}
	public Zipper setInputFile(String file_name) {
		inputFile = new File(file_name);
		return this;
	}
	public Zipper changeOutputDir(String output_dir) {
		outputDir = output_dir;
		return this;
	}
	public String getOutputDir() {
		return outputDir;
	}
	public Zipper setBufferSize(int buffer_size) {
		bufferSize = buffer_size;
		return this;
	}
	public int getBufferSize() {
		return bufferSize;
	}
}
