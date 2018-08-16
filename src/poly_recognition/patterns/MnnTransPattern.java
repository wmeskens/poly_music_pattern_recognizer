package poly_recognition.patterns;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.DifferenceValue;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;

public class MnnTransPattern extends TransPattern{
	
	public MnnTransPattern(ArrayList<PatternValue> intervals, List<Occurrence>occs, MnnTransPattern specific){
		super(occs,specific,intervals);
	}
	
	public MnnTransPattern() {
		super(new ArrayList<Occurrence>(),null,new ArrayList<PatternValue>());
	}
	
	@Override
	protected boolean comparableDiffValue(DifferenceValue currDiffVal, DifferenceValue newDiffVal) {
		return newDiffVal.sameMnnValue(currDiffVal) && newDiffVal.sameLengthValue(currDiffVal);
	}
	
	@Override
	protected MnnTransPattern createPattern(ArrayList<PatternValue> newValues,
			ArrayList<Occurrence> newOccs) {
		return new MnnTransPattern(newValues,newOccs,this);
	}
	
}
