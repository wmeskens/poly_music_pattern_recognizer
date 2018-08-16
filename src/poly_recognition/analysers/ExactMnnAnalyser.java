package poly_recognition.analysers;

import java.io.File;

import poly_recognition.patterns.ExactMnnPattern;
import poly_recognition.sequences.ExactMnnSequence;
import poly_recognition.sequences.ExactSequence;
import poly_recognition.sequences.Sequence;

public class ExactMnnAnalyser extends ExactAnalyser{
	
	public ExactMnnAnalyser(File file){
		super(file, new ExactMnnPattern());
	}
	
	@Override
	public ExactMnnSequence createNewSequence(int staff) {
		ExactMnnSequence exactSeq = new ExactMnnSequence((ExactMnnPattern)basePat,staff);
		return exactSeq;
	}
	
	@Override
	public ExactSequence getCurSeq(Sequence seq){
		return (ExactMnnSequence)seq;
	}
	
}
