package poly_recognition.sequences;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.DifferenceValue;
import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.Time;
import poly_recognition.patterns.Pattern;
import poly_recognition.patterns.TransPattern;

public class TransSequence extends Sequence {
	
	public TransSequence(TransPattern curPat, Occurrence curOcc){
		super(curPat,curOcc);
	}

	public TransSequence(TransPattern pat,int staff) {
		this(pat, new Occurrence(staff));
	}

	@Override
	protected TransPattern getPattern(Pattern extendPat) {
		return (TransPattern) extendPat;
	}
	
	@Override
	protected TransPattern createPattern(Pattern previousPat, ArrayList<PatternValue> patValues, ArrayList<Occurrence> occs) {
		TransPattern trPat = getPattern(previousPat);
		return new TransPattern(occs,trPat,patValues);
	}

	@Override
	protected TransSequence createBaseSequence(Pattern extendPat) {
		TransPattern trPat = getPattern(extendPat);
		TransSequence seqCur = new TransSequence(trPat, curOcc);
		return seqCur;
	}
	
	@Override
	protected TransSequence createSequence(Pattern pat, Occurrence curOcc) {
		return new TransSequence((TransPattern)pat, curOcc);
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
	protected DifferenceValue createPatVal(Note previous, Note next){	
		DifferenceValue diffVal = new DifferenceValue(previous, next);
		return diffVal;
	}
	
	@Override
	public boolean isBaseSeq(Pattern basePat) {
		boolean genBasePat = false;
			if(curPat==basePat && curOcc.size()<=1) genBasePat = true;
		return genBasePat;
	}
	
	
	
	
	
}
