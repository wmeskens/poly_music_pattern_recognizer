package poly_recognition;

import java.util.ArrayList;

import poly_recognition.patterns.Pattern;

public class Occurrence {
	private ArrayList<PatternValue> values;
	private int staff;
	
	
	public Occurrence(int staff) {
		values = new ArrayList<PatternValue>();
		this.staff = staff;
	}

	public Occurrence(ArrayList<PatternValue> vals) {
		this.values = vals;
		this.staff = vals.get(0).getStaff();
	}
	
	public Occurrence(PatternValue val){
		values = new ArrayList<PatternValue>();
		values.add(val);
		this.staff = val.getStaff();
	}

	public ArrayList<PatternValue> getValues(){
		return values;
	}
	
	public void addValue(PatternValue val){
		values.add(val);
	}
	
	public int size(){
		return values.size();
	}
	
	public PatternValue getValueAt(int pos){
		return values.get(pos);
	}
	
	public void removeVals(int pos){
		for(int i = pos; i<values.size(); i++){
			values.remove(pos);
		}
	}
	
	public ArrayList<Note> nextNotes(ArrayList<Time> memory){
		int size = values.size();
		PatternValue lastVal = values.get(size-1);
		Note curNote = lastVal.getCurNote();
		int memId = curNote.getTimeMemId();
		Time nextTime = memory.get(memId+1);
		ArrayList<Note> nextNotes = nextTime.getNotes();
		return nextNotes;
	}
	
	@Override
	public String toString() {
		String string = "";
		string+= "\r\n"+"Occurrence: ";
		string+= values;
		return string;
	}
	
	public int getStaff(){
		return staff;
	}

	public Note lastNote() {
		int size = values.size();
		if(size>0){
			return values.get(size-1).getCurNote();
		}
		return null;
	}

	

}
