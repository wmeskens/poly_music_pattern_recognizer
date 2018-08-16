package poly_recognition.analysers;

import java.io.File;

import poly_recognition.patterns.MnnTransPattern;
import poly_recognition.sequences.MnnTransSequence;
import poly_recognition.sequences.Sequence;

public class MnnTransAnalyser extends TransAnalyser{
	
	public MnnTransAnalyser(File file){
		super(file, new MnnTransPattern());
	}
	
	@Override
	public MnnTransSequence createNewSequence(int staff) {
		MnnTransSequence transSeq = new MnnTransSequence((MnnTransPattern)basePat,staff);
		return transSeq;
	}
	
	@Override
	public MnnTransSequence getCurSeq(Sequence seq){
		return (MnnTransSequence)seq;
	}
}
