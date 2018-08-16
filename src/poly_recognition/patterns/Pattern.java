package poly_recognition.patterns;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.Time;

public class Pattern {

	protected ArrayList<Pattern> generals;
	protected Pattern specific;
	protected List<Occurrence> occs;
	protected ArrayList<PatternValue> patValues;
	
	public Pattern(ArrayList<Pattern> generals, Pattern specific, List<Occurrence> occs, ArrayList<PatternValue> patValues){
		this.generals = generals;
		this.specific = specific;
		this.occs = occs;
		this.patValues = patValues;
	}

	
	
	//Find all the general patterns of the given pattern that start with the next note.
	public List<Pattern> findExtendGenPats(PatternValue currentPatVal) {
		List<Pattern> genPats = getGeneralPats();
		List<Pattern> extendPats = new ArrayList<Pattern>();
		for(Pattern pat: genPats){
			PatternValue newPatVal = pat.getCurrentValue(0);
			if(comparableValue(currentPatVal, newPatVal)){
				for(Occurrence occ:pat.getOccurences()){
					PatternValue patVal = occ.getValueAt(0);
					if(patVal.equals(currentPatVal)){
						return null;
					}
				}
				extendPats.add(pat);
			}
		}
		return extendPats;
	}

	public boolean comparableValue(PatternValue currentPatVal,
			PatternValue newPatVal) {
		return newPatVal.sameValue(currentPatVal);
	}
	
	public Pattern findExtendPat(PatternValue patVal, ArrayList<ArrayList<Time>> memory){
		// The OccurrenceValues that are found to extend the occurrences of the pattern.
		ArrayList<PatternValue> extendOccVals = new ArrayList<PatternValue>();
		// The Occurrences that can be extended.
		ArrayList<Occurrence> extendOccs = new ArrayList<Occurrence>();
		for(Occurrence occ:occs){
			findMemoryExtension(memory,extendOccVals, extendOccs, patVal, occ);
		}
		boolean extended = extendOccs.size()>0;
		boolean AllOccsExtended = !(extendOccs.size()<occs.size());
		Pattern newPat=null;
		if(extended){
			if(!AllOccsExtended){
				//a general pattern needs to be added to the current pattern with the new occVals as occurrences.
				newPat = switchOccurrencesToGenPat(patVal, extendOccVals, extendOccs);
			}else{
				//The current pattern needs to be extended 
				//and all the occurrences need to be extended with the found occurrenceValues.
				if(this.generals.isEmpty()){
					patValues.add(patVal);
					int amount = extendOccs.size();
					for(int i=0; i<amount; i++){
						Occurrence currOcc = extendOccs.get(i);
						PatternValue currOccVal = extendOccVals.get(i);
						currOcc.addValue(currOccVal);
					}
					newPat = this;
				}else{
					newPat = switchOccurrencesToGenPat(patVal, extendOccVals, extendOccs);
				}
			}
		}
		return newPat;
	}
	
	private Pattern switchOccurrencesToGenPat(PatternValue patVal, ArrayList<PatternValue> extendOccVals, ArrayList<Occurrence> extendOccs) {
		Pattern genPat = createGenPat(patVal);
		for(int i =0; i<extendOccs.size();i++){
			Occurrence currOcc = extendOccs.get(i);
			currOcc.addValue(extendOccVals.get(i));
			genPat.addOccurence(currOcc);
			occs.remove(currOcc);
		}
		addGeneral(genPat);
		return genPat;
	}
	
	// Find if the given Occurrence occ's next note has the same value as the given occVal.
	// If the values are the same, the occ and occVal are both added to the occ and occVal lists.
	private void findMemoryExtension(ArrayList<ArrayList<Time>> memory, ArrayList<PatternValue> extendOccVals,
			ArrayList<Occurrence> extendOccs, PatternValue nextPatVal, Occurrence occ) {
		int staff = occ.getStaff();
		ArrayList<Time> staffMemory = memory.get(staff);
		List<PatternValue> patVals = nextPatternValues(occ, staffMemory);
		if (patVals!=null) {
			for(PatternValue patVal:patVals){
				if (comparableValue(nextPatVal, patVal)) {
					extendOccVals.add(patVal);
					extendOccs.add(occ);
				}
			}
		}
	}

	private Pattern createGenPat(PatternValue patVal) {
		ArrayList<PatternValue> extendIntervals = new ArrayList<PatternValue>();
		extendIntervals.add(patVal);
		ArrayList<Occurrence> extendOccus = new ArrayList<Occurrence>();
		Pattern genPat = createPattern(extendIntervals,extendOccus);
		return genPat;
	}
	
	public void split(int patPos){
		if(specific!=null){
			patPos-=specific.getSize();
		}
		ArrayList<PatternValue> newValues = new ArrayList<PatternValue>();
		for(int i = patPos; i<patValues.size(); i++){
			newValues.add(patValues.get(i));
		}
		removeValues(patPos);
		ArrayList<Occurrence> newOccs = new ArrayList<Occurrence>();
		newOccs.addAll(occs);
		Pattern genPat = createPattern(newValues,newOccs);
		occs.clear();
		for(Pattern curGenPat:generals){
			Pattern curGenExPat = curGenPat;
			curGenPat.setSpecific(genPat);
			genPat.addToGenerals(curGenExPat);
		}
		generals.clear();
		generals.add(genPat);
	}

	private void setSpecific(Pattern genPat) {
		this.specific = genPat;
		
	}



	protected Pattern createPattern(ArrayList<PatternValue> newValues,
			ArrayList<Occurrence> newOccs) {
		return new Pattern(new ArrayList<Pattern>(),this ,newOccs, newValues);
	}
	
	private List<PatternValue> nextPatternValues(Occurrence occ, ArrayList<Time> staffMemory){
		List<PatternValue> patVals = new ArrayList<PatternValue>();
		int size = occ.size();
		PatternValue lastVal = occ.getValueAt(size-1);
		Note lastNote = lastVal.getCurNote();
		int memId = lastNote.getTimeMemId();
		int memSize = staffMemory.size();
		if(memId+1>=memSize){
			return null;
		}
		Time nextTime = staffMemory.get(memId+1);
		ArrayList<Note> timeNotes = nextTime.getNotes();
		for(Note curNote: timeNotes){
			PatternValue curPatVal = createPatVal(lastNote,curNote);
			patVals.add(curPatVal);
		}
		return patVals;
	}
	
	public int getOccurrenceAmount(){
		int amount = 0;
		for(Pattern genPat:generals){
			amount+=genPat.getOccurrenceAmount();
		}
		amount+=occs.size();
		return amount;
	}

	protected PatternValue createPatVal(Note lastVal, Note nextNote) {
		PatternValue patVal = nextNote;
		return patVal;
	}
	
	public void addGeneral(Pattern genPat) {
		generals.add(genPat);		
	}
	
	public List<Occurrence> getOccurences(){
		return occs;
	}
	
	public void addOccurence(Occurrence occ){
		occs.add(occ);
	}
	
	public ArrayList<Pattern> getGeneralPats(){
		return generals;
	}

	public int getSize(){
		int size = 0;
		if(!(specific == null)){
			size+=specific.getSize();
		}
		size+=patValues.size();
		return size;
	}
		
	
	public PatternValue getTotalValue(int place){
		if(specific!=null){
			int specificSize = specific.getSize();
			if(place<specificSize){
				return specific.getTotalValue(place);
			}
			else{
				return getCurrentValue(place-specificSize);
			}
		}
		return null;
	}
	
	public PatternValue getCurrentValue(int place){
		if(place<patValues.size()){
			return patValues.get(place);
		}
		return null;
	}
	
	public ArrayList<PatternValue> getValues(){
		return patValues;
	}
	
	protected void removeValues(int pos){
		int size = patValues.size();
		for(int i = pos; i<size; i++){
			patValues.remove(pos);
		}
	}
	
	public void addToGenerals(Pattern pat){
		generals.add(pat);
	}
	
	public Pattern getSpecific(){
		return specific;
	}

	public int size() {
		return patValues.size();
	}
	
	@Override
	public String toString() {
		String current= "";
		if(specific!=null){
			current+=specific;
		}
		for(PatternValue patVal:patValues){
			current+=patVal;
		}
		return current;
	}



	public List<ArrayList<PatternValue>> getAllOccurrences(int patSize) {
		List<ArrayList<PatternValue>> allOccs = new ArrayList<ArrayList<PatternValue>>();
		for(Occurrence currOcc:occs){
			ArrayList<PatternValue> occVals = currOcc.getValues();
			ArrayList<PatternValue> rightOccVals = new ArrayList<PatternValue>();
			rightOccVals.addAll(occVals.subList(0, patSize-1));
			allOccs.add(rightOccVals);
		}
		for(Pattern pat: generals){
			allOccs.addAll(pat.getAllOccurrences(patSize));
		}
		return allOccs;
	}
}
