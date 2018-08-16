package poly_recognition.sequences;

import java.util.ArrayList;

import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.patterns.ExactPattern;
import poly_recognition.patterns.Pattern;

public class ExactSequence extends Sequence {
	
	public ExactSequence(ExactPattern curPat, Occurrence curOcc){
		super(curPat, curOcc);
	}

	public ExactSequence(ExactPattern pat, int staff) {
		this(pat, new Occurrence(staff));
	}

	@Override
	public boolean equals(Object obj) {
		boolean equal = false;
		if(obj.getClass().equals(ExactSequence.class)){
			ExactSequence seq = (ExactSequence) obj;
			boolean samePat = seq.getCurPat().equals(curPat);
			boolean sameOcc = seq.getCurOcc().equals(curOcc);
			if(samePat&&sameOcc){
				equal = true;
			}
		}
		return equal;
	}

	@Override
	public String toString() {
		String cur = "";
		cur+=curPat.getValues();
		cur+=" occ: ";
		cur+=curOcc;
		cur+=" pos: ";
		return cur;
	}
	
	@Override
	protected Pattern createPattern(Pattern previousPat, ArrayList<PatternValue> patValues, ArrayList<Occurrence> occs) {
		ExactPattern prevPat = getPattern(previousPat);
		return new ExactPattern(patValues,occs,prevPat);
	}
	
	@Override
	protected ExactSequence createSequence(Pattern pat, Occurrence curOcc) {
		return new ExactSequence((ExactPattern)pat, curOcc);
	}
	
	@Override
	protected ExactSequence createBaseSequence(Pattern extendPat) {
		ExactSequence seqCur = new ExactSequence((ExactPattern)extendPat, curOcc);
		return seqCur;
	}
	
	@Override
	protected ExactPattern getPattern(Pattern extendPat) {
		return (ExactPattern) extendPat;
	}
	
}
