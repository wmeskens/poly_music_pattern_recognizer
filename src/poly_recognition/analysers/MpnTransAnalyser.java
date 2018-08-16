package poly_recognition.analysers;

import java.io.File;

import poly_recognition.patterns.MpnTransPattern;
import poly_recognition.sequences.MpnTransSequence;
import poly_recognition.sequences.Sequence;

public class MpnTransAnalyser extends TransAnalyser{
	
	public MpnTransAnalyser(File file){
		super(file,new MpnTransPattern());
	}
	
	
	@Override
	public MpnTransSequence createNewSequence(int staff) {
		MpnTransSequence exactSeq = new MpnTransSequence((MpnTransPattern)basePat, staff);
		return exactSeq;
	}
	
	@Override
	public MpnTransSequence getCurSeq(Sequence seq){
		return (MpnTransSequence)seq;
	}
}
