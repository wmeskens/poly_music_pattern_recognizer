package poly_recognition.patterns;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;

public class ExactLengthPattern extends ExactPattern{
	
	public ExactLengthPattern(ArrayList<PatternValue> notes, List<Occurrence>occs, ExactLengthPattern specific){
		super(notes,occs,specific);
	}
	
	public ExactLengthPattern() {
		super(new ArrayList<PatternValue>(),new ArrayList<Occurrence>(),null);
	}

	@Override
	protected boolean comparableNoteValue(Note currNote, Note newNote) {
		return newNote.sameLengthValue(currNote);
	}
	
	@Override
	protected ExactLengthPattern createPattern(ArrayList<PatternValue> newValues,
			ArrayList<Occurrence> newOccs) {
		return new ExactLengthPattern(newValues,newOccs,this);
	}
}
