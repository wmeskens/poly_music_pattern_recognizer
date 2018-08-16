package poly_recognition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import poly_recognition.analysers.Analyser;
import poly_recognition.analysers.ExactLengthAnalyser;
import poly_recognition.analysers.ExactMnnAnalyser;
import poly_recognition.analysers.ExactMpnAnalyser;
import poly_recognition.analysers.MnnTransAnalyser;
import poly_recognition.analysers.MpnTransAnalyser;
import poly_recognition.analysers.TransAnalyser;
import poly_recognition.filters.Filter;
import poly_recognition.patterns.MpnTransPattern;
import poly_recognition.patterns.Pattern;
import poly_recognition.patterns.TransPattern;
import poly_recognition.readers.InformationReader;

public class Main {

	private static InformationReader reader;
	private static String folderName = "C:/Users/Wout/OneDrive/thesis/results/";
	private static OutputStream out;
	private static File meta = new File("metadata2.txt");
	private static PrintWriter printer;
	
	public static void main(String[] args) {
		reader = new InformationReader(meta);
		List<File> fileList = reader.getFileList();
		for(File file:fileList){
			String name = file.getName();
			TransPattern basePat = new MpnTransPattern();
			Analyser analyser = new MpnTransAnalyser(file);
			analyser.analyse();
			String analyserString = analyser.toString();
			try {
				String analyserName = analyser.getClass().toString();
				String[] aName = analyserName.split("\\.");
				int length = aName.length;
				analyserName = aName[length-1];
				String fileName = folderName+analyserName+name;
				File outputFile = new File(fileName);
				if (!outputFile.exists()) {
					outputFile.createNewFile();
				}
				out = new FileOutputStream(outputFile);
				printer = new PrintWriter(out);
			} catch (IOException e) {
				e.printStackTrace();
			}
//			System.out.println(analyserString);
			Filter fil = new Filter(analyser);
			String filtered = fil.filterBasePat();
			System.out.println(filtered);
			printer.print(analyserString);
			printer.flush();
		}
	}
	
	public static void print(String txt) {
		printer.print(txt);
		printer.flush();
	}

}
