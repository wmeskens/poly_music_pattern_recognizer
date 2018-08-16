package poly_recognition.sequences;

import java.util.ArrayList;

import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.patterns.MpnTransPattern;
import poly_recognition.patterns.Pattern;

public class MpnTransSequence extends TransSequence {
	
	
	public MpnTransSequence(MpnTransPattern curPat, Occurrence curOcc){
		super(curPat,curOcc);
	}

	public MpnTransSequence(MpnTransPattern pat,int staff) {
		super(pat, new Occurrence(staff));
	}
	
	@Override
	protected MpnTransPattern createPattern(Pattern previousPat, ArrayList<PatternValue> patValues, ArrayList<Occurrence> occs) {
		MpnTransPattern prevPat = getPattern(previousPat);
		return new MpnTransPattern(patValues, occs, prevPat);
	}
	
	@Override
	protected MpnTransSequence createSequence(Pattern pat, Occurrence curOcc) {
		return new MpnTransSequence((MpnTransPattern)pat, curOcc);
	}
	
	@Override
	protected MpnTransSequence createBaseSequence(Pattern extendPat) {
		return new MpnTransSequence((MpnTransPattern)extendPat, curOcc);
	}
	
	@Override
	protected MpnTransPattern getPattern(Pattern extendPat) {
		return (MpnTransPattern) extendPat;
	}
	
}
