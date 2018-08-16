package poly_recognition;

import java.util.ArrayList;

public class Time {
	private final float time;
	private final int staff;
	private final ArrayList<Note> notes;
	private final int memId;
	
	public Time(int memId, float time, int staff, ArrayList<Note> notes){
		this.memId = memId;
		this.time = time;
		this.staff = staff;
		this.notes = notes;
	}
	
	public float getTime(){
		return time;
	}
	
	public int getStaff(){
		return staff;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}

	public boolean contains(Note curNote) {
		if(time==curNote.time && staff == curNote.staff){
			return true;
		}
		return false;
	}
	
	public void addNote(Note note){
		notes.add(note);
	}

	public Time getPrevious(ArrayList<ArrayList<Time>> memory) {
		ArrayList<Time> staffMemory = memory.get(staff);
		if(memId>0){
			return staffMemory.get(memId-1);
		}
		return null;
	}
	
	@Override
	public String toString() {
		return ""+time+" "+staff;
	}

}
