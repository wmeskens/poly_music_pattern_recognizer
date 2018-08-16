package poly_recognition.analysers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import poly_recognition.patterns.ExactPattern;
import poly_recognition.patterns.Pattern;
import poly_recognition.readers.MusicReader;
import poly_recognition.sequences.ExactSequence;
import poly_recognition.sequences.Sequence;

public class ExactAnalyser extends Analyser{
	
	public ExactAnalyser(File file,ExactPattern exPat){
		super(new MusicReader(file),new ArrayList<List<Sequence>>(),exPat);
	}

	@Override
	public ExactSequence createNewSequence(int staff) {
		ExactSequence seq = new ExactSequence((ExactPattern)basePat,staff);
		return seq;
	}

	@Override
	protected ExactSequence getCurSeq(Sequence seq){
		return (ExactSequence)seq;
	}
	
	@Override
	public String toString() {
		String curString = "-------------New patterns---------------"+"\r\n";
		return curString+toString(basePat, new ArrayList<Object>());
	}
	
	@SuppressWarnings("unchecked")
	protected String toString(Pattern curPat, ArrayList<Object> values) {
		String curString = "";
		if(curPat.getGeneralPats().isEmpty()){
			curString += "\r\n"+"pattern: ";
			curString+=values;
			curString+=curPat.getOccurences();
		}else{
			if(!curPat.getOccurences().isEmpty()){
				curString += "\r\n"+"pattern: ";
				curString+=values;
				curString+=curPat.getOccurences();
			}
			for(Pattern genPat:curPat.getGeneralPats()){
				ExactPattern exPat = (ExactPattern) genPat;
				ArrayList<Object> newValues = (ArrayList<Object>) values.clone();
				newValues.addAll(exPat.getValues());
				curString+=toString(exPat, newValues);
			}
		}
		return curString;
	}
	
	@Override
	protected boolean lastNoteSequenceNeeded(Boolean genBasePat,
			boolean firstEmpty, List<Sequence> nextSequences) {
		return nextSequences.isEmpty()&&!firstEmpty;
	}
	
}
