package poly_recognition.analysers;

import java.io.File;

import poly_recognition.patterns.ExactLengthPattern;
import poly_recognition.sequences.ExactLengthSequence;
import poly_recognition.sequences.ExactSequence;
import poly_recognition.sequences.Sequence;

public class ExactLengthAnalyser extends ExactAnalyser{
	
	public ExactLengthAnalyser(File file){
		super(file, new ExactLengthPattern());
	}
	
	@Override
	public ExactLengthSequence createNewSequence(int staff) {
		ExactLengthSequence exactSeq = new ExactLengthSequence((ExactLengthPattern)basePat,staff);
		return exactSeq;
	}
	
	@Override
	public ExactSequence getCurSeq(Sequence seq){
		return (ExactLengthSequence)seq;
	}
	
}
