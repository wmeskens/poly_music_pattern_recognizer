package poly_recognition.patterns;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.DifferenceValue;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;

public class MpnTransPattern extends TransPattern{
	
	public MpnTransPattern(ArrayList<PatternValue> intervals, List<Occurrence>occs, MpnTransPattern specific){
		super(occs,specific,intervals);
	}
	
	public MpnTransPattern() {
		super(new ArrayList<Occurrence>(),null,new ArrayList<PatternValue>());
	}
	
	@Override
	protected boolean comparableDiffValue(DifferenceValue currDiffVal, DifferenceValue newDiffVal) {
		return newDiffVal.sameMpnValue(currDiffVal) && newDiffVal.sameLengthValue(currDiffVal);
	}
	
	@Override
	protected MpnTransPattern createPattern(ArrayList<PatternValue> newValues,
			ArrayList<Occurrence> newOccs) {
		return new MpnTransPattern(newValues,newOccs,this);
	}
	
}
