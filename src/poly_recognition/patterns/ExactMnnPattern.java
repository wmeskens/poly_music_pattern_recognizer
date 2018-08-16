package poly_recognition.patterns;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;

public class ExactMnnPattern extends ExactPattern{
	
	public ExactMnnPattern(ArrayList<PatternValue> notes, List<Occurrence>occs, ExactMnnPattern specific){
		super(notes,occs,specific);
	}
	
	public ExactMnnPattern() {
		super(new ArrayList<PatternValue>(),new ArrayList<Occurrence>(),null);
	}

	@Override
	protected boolean comparableNoteValue(Note currNote, Note newNote) {
		return newNote.sameMnnValue(currNote);
	}
	
	@Override
	protected ExactMnnPattern createPattern(ArrayList<PatternValue> newValues,
			ArrayList<Occurrence> newOccs) {
		return new ExactMnnPattern(newValues,newOccs,this);
	}
	
	
}
