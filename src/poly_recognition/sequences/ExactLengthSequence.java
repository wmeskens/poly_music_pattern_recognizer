package poly_recognition.sequences;

import java.util.ArrayList;

import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.patterns.ExactLengthPattern;
import poly_recognition.patterns.Pattern;

public class ExactLengthSequence extends ExactSequence {
	
	public ExactLengthSequence(ExactLengthPattern curPat, Occurrence curOcc){
		super(curPat, curOcc);
	}

	public ExactLengthSequence(ExactLengthPattern pat, int staff) {
		this(pat,new Occurrence(staff));
	}
	
	@Override
	protected ExactLengthPattern createPattern(Pattern previousPat, ArrayList<PatternValue> patValues, ArrayList<Occurrence> occs) {
		ExactLengthPattern prevPat = getPattern(previousPat);
		return new ExactLengthPattern(patValues,occs,prevPat);
	}
	
	@Override
	protected ExactLengthSequence createBaseSequence(Pattern extendPat) {
		ExactLengthPattern curPat = getPattern(extendPat);
		ExactLengthSequence seqCur = new ExactLengthSequence(curPat, curOcc);
		return seqCur;
	}
	
	@Override
	protected ExactLengthPattern getPattern(Pattern extendPat) {
		return (ExactLengthPattern) extendPat;
	}
	
}
