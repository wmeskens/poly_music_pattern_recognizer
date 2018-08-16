package poly_recognition.sequences;

import java.util.ArrayList;

import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.patterns.ExactMpnPattern;
import poly_recognition.patterns.Pattern;

public class ExactMpnSequence extends ExactSequence {
	
	public ExactMpnSequence(ExactMpnPattern curPat, Occurrence curOcc){
		super(curPat, curOcc);
	}

	public ExactMpnSequence(ExactMpnPattern pat, int staff) {
		super(pat,new Occurrence(staff));
	}
	
	@Override
	protected ExactMpnPattern createPattern(Pattern previousPat, ArrayList<PatternValue> patValues, ArrayList<Occurrence> occs) {
		ExactMpnPattern prevPat = getPattern(previousPat);
		return new ExactMpnPattern(patValues,occs,prevPat);
	}
	
	@Override
	protected ExactMpnSequence createSequence(Pattern pat, Occurrence curOcc) {
		return new ExactMpnSequence((ExactMpnPattern)pat, curOcc);
	}
	
	@Override
	protected ExactMpnSequence createBaseSequence(Pattern extendPat) {
		ExactMpnSequence seqCur = new ExactMpnSequence((ExactMpnPattern)extendPat, curOcc);
		return seqCur;
	}
	
	@Override
	protected ExactMpnPattern getPattern(Pattern extendPat) {
		return (ExactMpnPattern) extendPat;
	}
	
}
