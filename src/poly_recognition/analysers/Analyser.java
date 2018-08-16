package poly_recognition.analysers;

import java.util.ArrayList;
import java.util.List;

import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.Time;
import poly_recognition.patterns.Pattern;
import poly_recognition.readers.MusicReader;
import poly_recognition.sequences.Sequence;

public abstract class Analyser {
	
	protected MusicReader reader;
	protected ArrayList<List<Sequence>> sequences;
	protected Pattern basePat;
	
	public Analyser(MusicReader reader, ArrayList<List<Sequence>> sequences, Pattern basePat){
		this.reader = reader;
		this.sequences = sequences;
		this.basePat = basePat;
	}
	
	protected void initializeSequences(int staff){
		int size = sequences.size();
		for(int i = size; i<=staff; i++){
			sequences.add(new ArrayList<Sequence>());
		}
	}

	public void analyse() {
		ArrayList<ArrayList<Time>> memory = reader.getMemory();
		int staffs = memory.size();
		for(int i = 0; i<staffs; i++){
			ArrayList<Time> times = memory.get(i);
			int timesSize = times.size();
			for(int timeId = 0; timeId<timesSize; timeId++){
				Time time = times.get(timeId);
				processTime(time, memory);
				int size = sequences.get(i).size(); 
				System.out.println(""+time+" "+i+" "+size);
			}
		}
		clearSequences();
	}
	
	protected void processTime(Time next,ArrayList<ArrayList<Time>> memory) {
		int staff = next.getStaff();
		boolean genBaseSeq = false;
		boolean firstEmpty = false;
		List<Sequence> staffSequences = new ArrayList<Sequence>();
		List<Sequence> nextSequences = new ArrayList<Sequence>();
		if(sequences.size()>=staff+1){
			staffSequences = sequences.get(staff);
		}
		else{
			initializeSequences(staff);
			staffSequences = sequences.get(staff);
		}
		if(staffSequences.isEmpty()){
			firstEmpty = true;
			Sequence curSeq = createNewSequence(staff);
			staffSequences.add(curSeq);
		}
		for(Sequence seq: staffSequences){
			boolean isGenBaseSeq = seq.isBaseSeq(basePat);
			List<Sequence> nextSeqs = seq.extendTimeSequence(next, memory,basePat,this);
			nextSequences.addAll(nextSeqs);
			if(isGenBaseSeq) genBaseSeq = true;
		}
		if(lastNoteSequenceNeeded(genBaseSeq, firstEmpty, nextSequences)){
			Sequence seq = createNewSequence(staff);
			List<Sequence> nextSeqs =  seq.extendTimeSequence(next, memory, basePat,this);
			nextSequences.addAll(nextSeqs);
		}
		sequences.set(staff, nextSequences);
	}
	
	private void clearSequences() {
		for(int staff = 0; staff<sequences.size(); staff++){
			List<Sequence> staffSequences = sequences.get(staff);
			for(int sequenceVal = 0; sequenceVal<staffSequences.size(); sequenceVal++){
				Sequence sequence = staffSequences.get(sequenceVal);
				Pattern seqPat = sequence.getCurPat();
				Occurrence seqOcc = sequence.getCurOcc();
				int occSize = seqOcc.size();
				int patSize = seqPat.size();
				if(occSize==patSize){
					seqPat.addOccurence(seqOcc);
				}else if(occSize < patSize){
					if(occSize>1){
						seqPat.split(occSize-1);
						seqPat.addOccurence(seqOcc);
					}
				}	
			}
		}	
	}

	protected Sequence getCurSeq(Sequence seq) {
		return seq;
	}

	public Sequence createNewSequence(int staff) {
		Sequence seq = new Sequence((Pattern)basePat,new Occurrence(staff));
		return seq;
	}
	
	@Override
	public String toString() {
		String curString = "-------------New patterns---------------"+"\r\n";
		return curString+toString(basePat, new ArrayList<Object>());
	}
	
	@SuppressWarnings("unchecked")
	protected String toString(Pattern curPat, ArrayList<Object> values) {
		String curString = "";
		if(curPat.getGeneralPats().isEmpty()){
			curString += "\r\n"+"pattern: ";
			curString+=curPat.toString();
			curString+=curPat.getOccurences();
		}else{
			if(!curPat.getOccurences().isEmpty()){
				curString += "\r\n"+"pattern: ";
				curString+=values;
				curString+=curPat.getOccurences();
			}
			for(Pattern genPat:curPat.getGeneralPats()){
				ArrayList<Object> newValues = (ArrayList<Object>) values.clone();
				newValues.addAll(genPat.getValues());
				curString+=toString(genPat, newValues);
			}
		}
		return curString;
	}

	protected boolean lastNoteSequenceNeeded(Boolean noSequenceNeeded,
			boolean firstEmpty, List<Sequence> nextSequences) {
		return nextSequences.isEmpty()&&!noSequenceNeeded;
	}

	public Pattern getBasePat() {
		return basePat;
	}
}
