package poly_recognition.patterns;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.DifferenceValue;

public class TransPattern extends Pattern{
	
	public TransPattern(List<Occurrence>occs, TransPattern specific, ArrayList<PatternValue> patValues){
		super(new ArrayList<Pattern>(),specific,occs,patValues);
	}
	
	public TransPattern() {
		super(new ArrayList<Pattern>(),null,new ArrayList<Occurrence>(),new ArrayList<PatternValue>());
	}
	
	@Override
	public boolean comparableValue(PatternValue currentPatVal, PatternValue newPatVal) {
		DifferenceValue currDiffVal = (DifferenceValue)currentPatVal;
		DifferenceValue newDiffVal = (DifferenceValue)newPatVal;
		return comparableDiffValue(currDiffVal, newDiffVal);
	}
	
	protected boolean comparableDiffValue(DifferenceValue currDiffVal,
			DifferenceValue newDiffVal) {
		return newDiffVal.sameValue(currDiffVal);
	}

	@Override
	public DifferenceValue getCurrentValue(int place){
		return (DifferenceValue)super.getCurrentValue(place);
	}
	
	@Override
	public DifferenceValue getTotalValue(int place){
		return (DifferenceValue)super.getTotalValue(place);
	}
	
	@Override
	protected PatternValue createPatVal(Note lastNote, Note nextNote) {
		PatternValue patVal = lastNote.difference(nextNote);
		return patVal;
	}
	
	@Override
	protected TransPattern createPattern(ArrayList<PatternValue> newValues,
			ArrayList<Occurrence> newOccs) {
		return new TransPattern(newOccs,this,newValues);
	}
	
}
