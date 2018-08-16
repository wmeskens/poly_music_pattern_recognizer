package poly_recognition.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import poly_recognition.DifferenceValue;
import poly_recognition.Note;
import poly_recognition.Occurrence;
import poly_recognition.PatternValue;
import poly_recognition.Time;
import poly_recognition.analysers.Analyser;
import poly_recognition.analysers.TransAnalyser;
import poly_recognition.patterns.Pattern;
import poly_recognition.patterns.TransPattern;

public class TransPatternTest {
	private TransPattern basePat;
	private TransPattern pat1;
	private ArrayList<ArrayList<Time>> memory;
	private Note note11;
	private Note note12;
	private Note note13;
	private Note note14;
	private Note note15;
	private Note note21;
	private Note note22;
	private Note note23;
	private Note note24;
	private Note note25;
	private Note note31;
	private Note note32;
	private Note note33;
	private Note note34;
	private Note note35;
	
	private DifferenceValue diff11;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		basePat = new TransPattern();
		memory = new ArrayList<ArrayList<Time>>();
		ArrayList<Time> memory0 = new ArrayList<Time>();
		memory.add(memory0);
		ArrayList<Time> memory1 = new ArrayList<Time>();
		Note note0 = new Note(0, 1, 1, 1, 1, 0);
		note11 = new Note(1, 55, 55, 1, 1, 1);
		note12 = new Note(2, 56, 56, 1, 1, 2);
		note13 = new Note(3, 57, 57, 1, 1, 3);
		note14 = new Note(4, 58, 58, 1, 1, 4);
		note15 = new Note(5, 59, 59, 1, 1, 5);
		note21 = new Note(6, 55, 55, 1, 1, 6);
		note22 = new Note(7, 56, 56, 1, 1, 7);
		note23 = new Note(8, 57, 57, 1, 1, 8);
		note24 = new Note(9, 58, 58, 1, 1, 9);
		note25 = new Note(10, 59, 59, 1, 1, 10);
		note31 = new Note(11, 55, 55, 1, 1, 12);
		note32 = new Note(12, 56, 56, 1, 1, 13);
		note33 = new Note(13, 57, 57, 1, 1, 14);
		note34 = new Note(14, 60, 60, 1, 1, 15);
		note35 = new Note(15, 61, 61, 1, 1, 16);
		diff11 = new DifferenceValue(note11,note12);
		DifferenceValue dif12 = new DifferenceValue(note12,note13);
		DifferenceValue dif13 = new DifferenceValue(note13,note14);
		DifferenceValue dif14 = new DifferenceValue(note14,note15);
		DifferenceValue dif21 = new DifferenceValue(note21,note22);
		DifferenceValue dif22 = new DifferenceValue(note22,note23);
		DifferenceValue dif23 = new DifferenceValue(note23,note24);
		DifferenceValue dif24 = new DifferenceValue(note24,note25);
		ArrayList<Note> notes0 = new ArrayList<Note>();
		notes0.add(note0);
		Time time0 = new Time(0, 0, 1, notes0);
		ArrayList<Note> notes11 = new ArrayList<Note>();
		notes11.add(note11);
		Time time11 = new Time(0, 0, 1, notes11);
		ArrayList<Note> notes12 = new ArrayList<Note>();
		notes12.add(note12);
		Time time12 = new Time(0, 0, 1, notes12);
		ArrayList<Note> notes13 = new ArrayList<Note>();
		notes13.add(note13);
		Time time13 = new Time(0, 0, 1, notes13);
		ArrayList<Note> notes14 = new ArrayList<Note>();
		notes14.add(note14);
		Time time14 = new Time(0, 0, 1, notes14);
		ArrayList<Note> notes15 = new ArrayList<Note>();
		notes15.add(note15);
		Time time15 = new Time(0, 0, 1, notes15);
		ArrayList<Note> notes21 = new ArrayList<Note>();
		notes21.add(note21);
		Time time21 = new Time(0, 0, 1, notes21);
		ArrayList<Note> notes22 = new ArrayList<Note>();
		notes22.add(note22);
		Time time22 = new Time(0, 0, 1, notes22);
		ArrayList<Note> notes23 = new ArrayList<Note>();
		notes23.add(note23);
		Time time23 = new Time(0, 0, 1, notes23);
		ArrayList<Note> notes24 = new ArrayList<Note>();
		notes24.add(note24);
		Time time24 = new Time(0, 0, 1, notes24);
		ArrayList<Note> notes25 = new ArrayList<Note>();
		notes25.add(note25);
		Time time25 = new Time(0, 0, 1, notes25);
		ArrayList<Note> notes31 = new ArrayList<Note>();
		notes31.add(note31);
		Time time31 = new Time(0, 0, 1, notes31);
		ArrayList<Note> notes32 = new ArrayList<Note>();
		notes32.add(note32);
		Time time32 = new Time(0, 0, 1, notes32);
		ArrayList<Note> notes33 = new ArrayList<Note>();
		notes33.add(note33);
		Time time33 = new Time(0, 0, 1, notes33);
		ArrayList<Note> notes34 = new ArrayList<Note>();
		notes34.add(note34);
		Time time34 = new Time(0, 0, 1, notes34);
		ArrayList<Note> notes35 = new ArrayList<Note>();
		notes35.add(note35);
		Time time35 = new Time(0, 0, 1, notes35);
		memory1.add(time0);
		memory1.add(time11);
		memory1.add(time12);
		memory1.add(time13);
		memory1.add(time14);
		memory1.add(time15);
		memory1.add(time21);
		memory1.add(time22);
		memory1.add(time23);
		memory1.add(time24);
		memory1.add(time25);
		memory1.add(time31);
		memory1.add(time32);
		memory1.add(time33);
		memory1.add(time34);
		memory1.add(time35);
		memory.add(memory1);
		ArrayList<PatternValue> patvals1 = new ArrayList<PatternValue>();
		patvals1.add(diff11);
		patvals1.add(dif12);
		patvals1.add(dif13);
		patvals1.add(dif14);
		ArrayList<PatternValue> patvals2 = new ArrayList<PatternValue>();
		patvals2.add(dif21);
		patvals2.add(dif22);
		patvals2.add(dif23);
		patvals2.add(dif24);
		pat1 = new TransPattern(new ArrayList<Occurrence>(),basePat,patvals1);
		ArrayList<PatternValue> patClone1 = (ArrayList<PatternValue>)patvals1.clone();
		ArrayList<PatternValue> patClone2 = (ArrayList<PatternValue>)patvals2.clone();
		Occurrence occurrence1 = new Occurrence(patClone1);
		Occurrence occurrence2 = new Occurrence(patClone2);
		pat1.addOccurence(occurrence1);
		pat1.addOccurence(occurrence2);
		basePat.addToGenerals(pat1);
	}

	@Test
	public void splitTest() {
		pat1.split(3);
		ArrayList<Pattern> genPats = pat1.getGeneralPats();
		assertTrue(genPats.size()==1);
		TransPattern genPat = (TransPattern)genPats.get(0);
		assertTrue(pat1.getCurrentValue(3)==null);
		DifferenceValue controlDiffVal = new DifferenceValue(new Note(4, 58, 58, 1, 1, 4), new Note(5, 59, 59, 1, 1, 5));
		PatternValue patValue = genPat.getCurrentValue(0);
		System.out.println(genPat.getOccurences().size());
		assertTrue(patValue.sameValue(controlDiffVal));
		assertTrue(genPat.getOccurences().size()==2);
		assertTrue(pat1.getOccurences().size()==0);
	}
	
	@Test
	public void findGeneralPatsTest(){
		assertTrue(basePat.findExtendGenPats(diff11).contains(pat1));
	}
	
	@Test
	public void findExtendNotePatsTest(){
		int patSize = pat1.getSize();
		DifferenceValue extendVal = new DifferenceValue(new Note(10, 59, 59, 1, 1, 10), new Note(11, 55, 55, 1, 1, 12));
		Pattern extendedPat = pat1.findExtendPat(extendVal,memory);
		assertTrue(extendedPat.equals(pat1));
		assertTrue(pat1.getSize()==patSize+1);
		assertTrue(pat1.getCurrentValue(patSize).sameValue(extendVal));
	}
	
	@Test
	@Category(Slow.class)
	public void exactResultsTest(){
		File file = new File("wtc2f20.txt");
		Analyser an = new TransAnalyser(file, basePat);
		an.analyse();
		boolean correct = assertRightPat(basePat);
		assertTrue(correct);
	}

	private boolean assertRightPat(Pattern pat) {
		boolean current = true;
		List<Pattern> pats = pat.getGeneralPats();
		for(Pattern patt:pats){
			if(!assertRightPat(patt)){
				current = false;
			}
		}
		if(!pat.getOccurences().isEmpty()){
			int patSize = pat.getSize();
			List<Occurrence> occs = pat.getOccurences();
			for(Occurrence currOcc: occs){
				int occSize = currOcc.size();
				if(patSize!=occSize){
					System.out.println("different length occ");
					System.out.println(""+currOcc);
					System.out.println(""+pat);
					current = false;
				}
			}
			for(int i = 0; i<patSize; i++){
				PatternValue patVal = pat.getTotalValue(i);
				for(Occurrence currOcc: occs){
					PatternValue occValue = currOcc.getValueAt(i);
					if(!(patVal.sameValue(occValue))){
						System.out.println("different val occ");
						System.out.println(""+currOcc);
						System.out.println(""+pat);
						current = false;
					}
				}
			}
		}
		return current;
	}

}
