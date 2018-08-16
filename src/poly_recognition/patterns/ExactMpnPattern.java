package poly_recognition.patterns;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;

public class ExactMpnPattern extends ExactPattern{
	
	public ExactMpnPattern(ArrayList<PatternValue> notes, List<Occurrence>occs, ExactMpnPattern specific){
		super(notes,occs,specific);
	}
	
	public ExactMpnPattern() {
		super(new ArrayList<PatternValue>(),new ArrayList<Occurrence>(),null);
	}

	@Override
	protected boolean comparableNoteValue(Note currNote, Note newNote) {
		return newNote.sameMpnValue(currNote);
	}
	
	@Override
	protected ExactMpnPattern createPattern(ArrayList<PatternValue> newValues,
			ArrayList<Occurrence> newOccs) {
		return new ExactMpnPattern(newValues,newOccs,this);
	}
}
