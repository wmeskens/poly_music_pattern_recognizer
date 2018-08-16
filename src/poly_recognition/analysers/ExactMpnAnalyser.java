package poly_recognition.analysers;

import java.io.File;

import poly_recognition.patterns.ExactMpnPattern;
import poly_recognition.sequences.ExactMpnSequence;
import poly_recognition.sequences.ExactSequence;
import poly_recognition.sequences.Sequence;

public class ExactMpnAnalyser extends ExactAnalyser{
	
	public ExactMpnAnalyser(File file){
		super(file, new ExactMpnPattern());
	}
	
	@Override
	public ExactMpnSequence createNewSequence(int staff) {
		ExactMpnSequence exactSeq = new ExactMpnSequence((ExactMpnPattern)basePat,staff);
		return exactSeq;
	}
	
	@Override
	public ExactSequence getCurSeq(Sequence seq){
		return (ExactMpnSequence)seq;
	}
	
}
