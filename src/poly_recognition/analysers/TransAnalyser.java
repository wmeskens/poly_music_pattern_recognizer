package poly_recognition.analysers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import poly_recognition.patterns.Pattern;
import poly_recognition.patterns.TransPattern;
import poly_recognition.readers.MusicReader;
import poly_recognition.sequences.Sequence;
import poly_recognition.sequences.TransSequence;

public class TransAnalyser extends Analyser{
	
	public TransAnalyser(File file,TransPattern transPat){
		super(new MusicReader(file),new ArrayList<List<Sequence>>(),transPat);
	}

	@Override
	protected TransSequence getCurSeq(Sequence seq) {
		return (TransSequence) seq;
	}

	@Override
	public TransSequence createNewSequence(int staff) {
		TransSequence trSeq = new TransSequence((TransPattern)basePat,staff);
		return trSeq;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected String toString(Pattern curPat, ArrayList<Object> values) {
		String curString = "";
		if(curPat.getGeneralPats().isEmpty()){
			curString += "\r\n"+"pattern: ";
			curString+=curPat.toString();
			curString+=curPat.getOccurences();
		}else{
			if(!curPat.getOccurences().isEmpty()){
				curString += "\r\n"+"pattern: ";
				curString+=values;
				curString+=curPat.getOccurences();
			}
			for(Pattern genPat:curPat.getGeneralPats()){
				TransPattern trPat = (TransPattern) genPat;
				ArrayList<Object> newValues = (ArrayList<Object>) values.clone();
				newValues.addAll(trPat.getValues());
				curString+=toString(trPat, newValues);
			}
		}
		return curString;
	}
	
	protected boolean lastNoteSequenceNeeded(Boolean genBasePat,
			boolean firstEmpty, List<Sequence> nextSequences) {
		return nextSequences.isEmpty()&&!genBasePat;
	}
	
}
