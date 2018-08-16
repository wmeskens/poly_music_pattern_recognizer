package poly_recognition.filters;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.analysers.Analyser;
import poly_recognition.patterns.Pattern;

public class Filter {
	private final int scoreThreshold = 10;
	private final int occMultiplier = 2;
	private final int sizeMultiplier = 1;
	
	
	public Analyser analyser;
	
	public Filter(Analyser analyser){
		this.analyser = analyser;
	}

	public String filterBasePat(){
		Pattern basePat = analyser.getBasePat();
		return filter(basePat);
	}
	
	private String filter(Pattern pat){
		String current = "";
		int patSize = pat.getSize();
		List<ArrayList<PatternValue>> allOccurrences = new ArrayList<ArrayList<PatternValue>>();
		if(patSize>1){
			allOccurrences = pat.getAllOccurrences(patSize);
		}
		int occsSize = allOccurrences.size();
		int score = getScore(patSize, occsSize); 
		if(score>scoreThreshold){
			current+="\r\n"+ "Pattern: "+patSize+" "+occsSize+"\r\n"+pat+"\r\n"+ "occurrences";
			int occAmount = allOccurrences.size();
			for(int i = 0; i<occAmount; i++){
				current += "\r\n"+"occ: "+allOccurrences.get(i);
			}
		}
		for(Pattern gen: pat.getGeneralPats()){
			current+=filter(gen);
		}
		return current;
	}

	private int getScore(int patSize, int occsSize) {
		if(patSize<5||occsSize<2){
			return 0;
		}
		int score = patSize*sizeMultiplier + occsSize*occMultiplier;
		return score;
	}
}
