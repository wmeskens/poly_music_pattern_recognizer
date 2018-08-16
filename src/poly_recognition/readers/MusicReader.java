package poly_recognition.readers;

import java.io.File;
import java.util.ArrayList;

import poly_recognition.Note;
import poly_recognition.Time;

public class MusicReader extends Reader {
	
	protected ArrayList<ArrayList<Time>> memory;
	protected ArrayList<Integer> counters;

	public MusicReader(File file) {
		super(file);
		memory = new ArrayList<ArrayList<Time>>();	
		counters = new ArrayList<Integer>();
		readFile();
	}
	
	private void initiateCounters(int staff) {
		int size = counters.size();
		for(int i = size; i<=staff; i++){
			counters.add(0);
		}
	}
	
	private void initiateMemory(int staff) {
		int size = memory.size();
		for(int i = size; i<=staff; i++){
			memory.add(new ArrayList<Time>());
		}
	}

	public void readFile(){
		Time current = new Time(-10,-10,-1,new ArrayList<Note>());
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] splited = line.split("\\s+");
			float time = convertFract(splited[0].substring(1,
					splited[0].length()));
			int mnn = Integer.parseInt(splited[1]);
			int mpn = Integer.parseInt(splited[2]);
			float length = convertFract(splited[3].substring(0,
					splited[3].length()));
			int staff = Integer.parseInt(splited[4].substring(0, splited[4].length()-1));
			int memoryId = 0;
			if(counters.size()>staff){
				memoryId = counters.get(staff);
			} else{
				initiateCounters(staff);
				initiateMemory(staff);
			}
			Note curNote = new Note(time, mnn, mpn, length, staff,memoryId);
			
			if(current.contains(curNote)){
				current.addNote(curNote);
			}
			else{
				Time lastStaffTime = getLastStaffTime(staff);
				if(lastStaffTime.getTime() == time){
					lastStaffTime.addNote(curNote);
					current = lastStaffTime;
				}
				else{
					ArrayList<Note> noteList = new ArrayList<Note>();
					noteList.add(curNote);
					Time newTime = new Time(memoryId,curNote.getTime(),curNote.getStaff(),noteList);
					memory.get(staff).add(newTime);
					counters.set(staff, memoryId+1);
				}				
			}
		}
	}
	
	public Time next(int memId, int staff){
		ArrayList<Time> times = memory.get(staff);
		int size = times.size();
		if(memId+1<size){
			return times.get(memId+1);
		}
		else{
			return null;
		}
	}
	
	private float convertFract(String substring) {
		int fractPlace = -1;
		for(int i = 0; i<substring.length(); i++){
			if(substring.substring(i,i+1).equals("/")){
				fractPlace = i;
				break;
			}
		}
		if(fractPlace!=-1){
			float num = Float.parseFloat(substring.substring(0, fractPlace));
			float den = Float.parseFloat(substring.substring(fractPlace+1, substring.length()));
			return num/den;
		}
		else{
			return Float.parseFloat(substring);
		}
	}
	
	public ArrayList<Time> getStaffMemory(int staff) {
		return this.memory.get(staff);
	}
	
	public int counterNextValue(int staff){
		int countersSize = counters.size();
		if(staff<countersSize){
			return counters.get(staff);
		}
		return -1;
	}

	public ArrayList<ArrayList<Time>> getMemory() {
		return memory;
	}
	
	private Time getLastStaffTime(int staff){
		ArrayList<Time> staffMemory = memory.get(staff);
		int memSize = staffMemory.size();
		return staffMemory.get(memSize-1);
	}
}
