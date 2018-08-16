package poly_recognition.patterns;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;

public class ExactPattern extends Pattern{
	
	public ExactPattern(ArrayList<PatternValue> notes, List<Occurrence>occs, ExactPattern specific){
		super(new ArrayList<Pattern>(), specific, occs, notes);
	}
	
	public ExactPattern() {
		super(new ArrayList<Pattern>(),null,new ArrayList<Occurrence>(),new ArrayList<PatternValue>());
	}
		
	@Override
	public Note getCurrentValue(int place){
		return (Note)super.getCurrentValue(place);
	}
	
	@Override
	public Note getTotalValue(int place){
		return (Note)super.getTotalValue(place);
	}
	
	@Override
	protected ExactPattern createPattern(ArrayList<PatternValue> newNotes,
			ArrayList<Occurrence> newOccs) {
		return new ExactPattern(newNotes,newOccs,this);
	}

	@Override
	public String toString() {
		String curString = "notes: ";
		for(PatternValue patVal: patValues){
			curString+= (Note) patVal+" ";
		}
		if(occs.size()>0){
			for(Occurrence curOcc : occs){
				curString+=curOcc;
			}
		}
		return curString;
	}

	protected boolean comparableNoteValue(Note currNote,
			Note newNote) {
		return newNote.sameValue(currNote);
	}
	
	@Override
	public boolean comparableValue(PatternValue currentPatVal, PatternValue newPatVal) {
		Note currDiffVal = (Note)currentPatVal;
		Note newDiffVal = (Note)newPatVal;
		return comparableNoteValue(currDiffVal, newDiffVal);
	}
}
