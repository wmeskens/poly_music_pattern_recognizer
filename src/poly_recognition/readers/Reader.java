package poly_recognition.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
	
	protected Scanner input;

	public Reader(File file){
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
