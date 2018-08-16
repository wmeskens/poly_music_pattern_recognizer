package poly_recognition.readers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InformationReader extends Reader {

	private int nbFiles;
	private List<File> filesList;
	
	public InformationReader(File file) {
		super(file);
		filesList = new ArrayList<File>();
		readInformation();
	}

	private void readInformation() {
		nbFiles = input.nextInt();
		for(int i = 0; i<nbFiles; i++){
			String fileName = input.next();
			File file = new File(fileName);
			filesList.add(file);
		}
	}
	
	public List<File> getFileList(){
		return filesList;
	}
}
