package poly_recognition.sequences;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.Time;
import poly_recognition.analysers.Analyser;
import poly_recognition.patterns.Pattern;

public class Sequence {
	
	protected Pattern curPat;
	protected Occurrence curOcc;
	
	public Sequence(Pattern curPat, Occurrence curOcc){
		this.curPat = curPat;
		this.curOcc = curOcc;
	}

	protected List<Sequence> extendPatValSequence(PatternValue newPatVal, Pattern basePat,
			ArrayList<ArrayList<Time>> memory, Analyser analyser) {
		List<Sequence> nextSequences = new ArrayList<Sequence>();
		Pattern curPat = getCurPat();
		int patPos = curOcc.size();
		if(curPat == basePat){
			basePatOperation(newPatVal, nextSequences, curPat);
		}
		else{
			int patSize = curPat.getSize();
			if(patSize>=patPos+1){
				//check if the current pattern has pattern values left.
				PatternValue curPatVal = curPat.getTotalValue(patPos);
				if(curPat.comparableValue(curPatVal, newPatVal)){
					//the next note is an extension of the current occurrence.
					patPos++;
					curOcc.addValue(newPatVal);
					nextSequences.add(this);
				}else{
					//the occurrence can't be extended with the current note. 
					//The notes until now are saved with the corresponding occurrences in the current pattern. 
					//The following notes of this pattern are saved in a new pattern.
					curPat.split(patPos);
					curPat.addOccurence(curOcc);
					ArrayList<Pattern> generals = curPat.getGeneralPats();
					for(Pattern pat:generals){
						PatternValue currPatVal = pat.getCurrentValue(0);
						int curStaff = currPatVal.getStaff();
						Sequence seq = analyser.createNewSequence(curStaff);
						nextSequences.addAll(seq.extendPatValSequence(currPatVal,basePat,memory,analyser));
					}
					
				}
			}else{
				nextSequences.addAll(findOutsidePatExtensions(newPatVal, memory, curPat, analyser, basePat));
			}	
		}
		return nextSequences;
	}
	
	protected List<Sequence> findOutsidePatExtensions(PatternValue patVal, ArrayList<ArrayList<Time>> memory, Pattern nextPat, Analyser analyser, Pattern basePat) {
		List<Sequence> nextSequences = new ArrayList<Sequence>();
		List<Pattern> genPats = nextPat.findExtendGenPats(patVal);
		if(genPats.size()>0){
			//check if the sequence can be extended by using an existing general pattern of the current pattern.
			curOcc.addValue(patVal);
			for(Pattern pat:genPats){
				Sequence seqCur = createSequence(pat,curOcc);
				nextSequences.add(seqCur);
				int curStaff = patVal.getStaff();
				Sequence seq = analyser.createNewSequence(curStaff);
				nextSequences.addAll(seq.extendPatValSequence(patVal,basePat,memory,analyser));
			}
		}else{
			// there is currently no extension of the pattern in the pattern tree that is an extension of the current pattern.
			// We try to find if the current note is also found in the memory as an extension in previous occurrences of the pattern.
			Pattern newPat = nextPat.findExtendPat(patVal, memory);
			if(newPat==null){
				// If no occurrence has the same note extension, the current occurrence is added to the list of occurrences of the current pattern.
				curPat.addOccurence(curOcc);
			}else if(curPat==newPat){
				// the current occurrence is extended with the note.
				curOcc.addValue(patVal);
				nextSequences.add(this);
			}else{
				curPat = newPat;
				curOcc.addValue(patVal);
				nextSequences.add(this);
			}
		}
		return nextSequences;
	}



	protected Sequence createSequence(Pattern pat, Occurrence curOcc) {
		return new Sequence(pat, curOcc);
	}

	protected void basePatOperation(PatternValue patVal, List<Sequence> nextSequences, Pattern curPat) {
		// if the current pattern is the base pattern.
		List<Pattern> extendGenPats = curPat.findExtendGenPats(patVal);
		if(extendGenPats==null){
			return;
		}
		boolean seqsContainVal = seqsContainVal(patVal,nextSequences);
		if(seqsContainVal){
			return;
		}
		if(extendGenPats.isEmpty()){
			//if the first note of the pattern can't be found in one of the general patterns of the base pattern.
			Pattern onePat = createNewPattern(curPat, patVal);
			curPat.addToGenerals(onePat);
		}else{
			curOcc.addValue(patVal);
			for(Pattern extendPat:extendGenPats){
				Sequence seqCur = createBaseSequence(extendPat);
				nextSequences.add(seqCur);
			}
		}
	}
	
	private boolean seqsContainVal(PatternValue patVal, List<Sequence> nextSequences) {
		for(Sequence seq:nextSequences){
			Occurrence seqOcc = seq.getCurOcc();
			if(seqOcc.getValueAt(0).equals(patVal)){
				return true;
			}
		}
		return false;
	}

	private Pattern createNewPattern(Pattern previousPat, PatternValue patVal) {
		ArrayList<PatternValue> patValues = new ArrayList<PatternValue>();
		patValues.add(patVal);
		ArrayList<Occurrence> occs = new ArrayList<Occurrence>();
		ArrayList<PatternValue> occValues = new ArrayList<PatternValue>();
		occValues.add(patVal);
		Occurrence exOcc = new Occurrence(occValues);
		occs.add(exOcc);
		Pattern genPat = createPattern(previousPat, patValues, occs);
		return genPat;
	}

	protected PatternValue createPatVal(Note previous, Note next) {
		return next;
	}
	
	protected Sequence createBaseSequence(Pattern extendPat) {
		Sequence seqCur = new Sequence(extendPat, curOcc);
		return seqCur;
	}

	public List<Sequence> extendTimeSequence(Time next, ArrayList<ArrayList<Time>> memory, Pattern basePat, Analyser analyser) {
		List<Sequence> extendSeqs = new ArrayList<Sequence>();
		Note previous = lastOccNote();
		if(previous == null){
			Time previousTime = next.getPrevious(memory);
			if(previousTime!=null){
				ArrayList<Note> prevNotes = previousTime.getNotes();
				ArrayList<Note> nextNotes = next.getNotes();
				for(Note prev:prevNotes){
					for(Note nex: nextNotes){
						PatternValue patVal = createPatVal(prev, nex);
						extendSeqs.addAll(extendPatValSequence(patVal,basePat,memory,analyser));
					}
				}
			}
		}
		else{
			float previousTime = previous.getTime();
			float previousLength = previous.getLength();
			float nextTime = next.getTime();
			if(previousTime+previousLength<=nextTime){
				ArrayList<Note> timeNotes = next.getNotes();
				for(Note timeNote: timeNotes){
					PatternValue patVal = createPatVal(previous,timeNote);
					extendSeqs.addAll(extendPatValSequence(patVal,basePat,memory,analyser));
				}
			}
			else{
				extendSeqs.add(this);	
			}
		}
		return extendSeqs;
	}

	private Note lastOccNote() {
		return curOcc.lastNote();
	}

	protected Pattern createPattern(Pattern previousPat, ArrayList<PatternValue> patValues, ArrayList<Occurrence> occs) {
		return new Pattern(new ArrayList<Pattern>(), previousPat, occs, patValues);
	}
	
	public Pattern getCurPat(){
		return getPattern(curPat); 
	}

	public Occurrence getCurOcc() {
		return curOcc;
	}

	public void setCurOcc(Occurrence curOcc) {
		this.curOcc = curOcc;
	}
	
	public void addOccurrence(PatternValue patVal){
		curOcc.addValue(patVal);
	}

	public boolean isBaseSeq(Pattern basePat) {
		return curOcc.size()==1 && curPat == basePat;
	}

	protected Pattern getPattern(Pattern extendPat) {
		return extendPat;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equal = false;
		if(obj.getClass().equals(TransSequence.class)){
			TransSequence seq = (TransSequence) obj;
			Occurrence seqOcc = seq.getCurOcc();
			boolean samePat = seq.getCurPat().equals(curPat);
			boolean sameOcc = seqOcc.equals(curOcc);
			if(samePat&&sameOcc){
				equal = true;
			}
		}
		return equal;
	}
	
	@Override
	public String toString() {
		String seqString = curPat.toString() +" "+ curOcc.toString();
		return seqString;
	}

}
