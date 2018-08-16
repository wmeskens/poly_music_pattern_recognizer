package poly_recognition.sequences;

import java.util.ArrayList;

import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.patterns.MnnTransPattern;
import poly_recognition.patterns.Pattern;

public class MnnTransSequence extends TransSequence {
	
	public MnnTransSequence(MnnTransPattern curPat, Occurrence curOcc){
		super(curPat,curOcc);
	}

	public MnnTransSequence(MnnTransPattern pat,int staff) {
		this(pat, new Occurrence(staff));
	}
	
	@Override
	protected MnnTransPattern createPattern(Pattern previousPat, ArrayList<PatternValue> patValues, ArrayList<Occurrence> occs) {
		MnnTransPattern prevPat = getPattern(previousPat);
		return new MnnTransPattern(patValues, occs, prevPat);
	}
	
	@Override
	protected MnnTransSequence createSequence(Pattern pat, Occurrence curOcc) {
		return new MnnTransSequence((MnnTransPattern)pat, curOcc);
	}
	
	@Override
	protected MnnTransSequence createBaseSequence(Pattern extendPat) {
		return new MnnTransSequence((MnnTransPattern)extendPat, curOcc);
	}
	
	@Override
	protected MnnTransPattern getPattern(Pattern extendPat) {
		return (MnnTransPattern) extendPat;
	}
	
}
