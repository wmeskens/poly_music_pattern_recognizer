package poly_recognition.sequences;

import java.util.ArrayList;

import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.patterns.ExactMnnPattern;
import poly_recognition.patterns.Pattern;

public class ExactMnnSequence extends ExactSequence {
	
	public ExactMnnSequence(ExactMnnPattern curPat, Occurrence curOcc){
		super(curPat, curOcc);
	}

	public ExactMnnSequence(ExactMnnPattern pat, int staff) {
		this(pat,new Occurrence(staff));
	}
	
	@Override
	protected ExactMnnPattern createPattern(Pattern previousPat, ArrayList<PatternValue> patValues, ArrayList<Occurrence> occs) {
		ExactMnnPattern prevPat = getPattern(previousPat);
		return new ExactMnnPattern(patValues,occs,prevPat);
	}
	
	@Override
	protected ExactMnnSequence createSequence(Pattern pat, Occurrence curOcc) {
		return new ExactMnnSequence((ExactMnnPattern)pat, curOcc);
	}
	
	@Override
	protected ExactMnnSequence createBaseSequence(Pattern extendPat) {
		ExactMnnSequence seqCur = new ExactMnnSequence((ExactMnnPattern)extendPat, curOcc);
		return seqCur;
	}
	
	@Override
	protected ExactMnnPattern getPattern(Pattern extendPat) {
		return (ExactMnnPattern) extendPat;
	}
	
}
