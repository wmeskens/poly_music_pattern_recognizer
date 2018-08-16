package poly_recognition;

public class DifferenceValue implements PatternValue{
	
	private Note previous;
	private Note current;
	
	public DifferenceValue(Note previous, Note current){
		this.previous = previous;
		this.current = current;
	}

	@Override
	public boolean sameValue(PatternValue val) {
		boolean same = false;
		if(val.getClass().equals(DifferenceValue.class)){
			DifferenceValue diffVal = (DifferenceValue)val;
			if(getMnnDifference()==diffVal.getMnnDifference() && getMpnDifference()==diffVal.getMpnDifference()){
				same = true;
			}
		}
		return same;
	}
	
	public int getMnnDifference(){
		return previous.getMnn()-current.getMnn();
	}
	
	public int getMpnDifference(){
		return previous.getMpn()-current.getMpn();
	}

	public boolean sameMnnValue(DifferenceValue currDiffVal) {
		int mnnVal = getMnnDifference();
		int currMnnVal = currDiffVal.getMnnDifference();
		return mnnVal == currMnnVal;
	}
	
	public boolean sameMpnValue(DifferenceValue currDiffVal) {
		int mpnVal = getMpnDifference();
		int currMpnVal = currDiffVal.getMpnDifference();
		return mpnVal == currMpnVal;
	}
	
	public boolean sameLengthValue(DifferenceValue currDiffVal){
		
		Note currFirst = currDiffVal.getPrevNote();
		float currLength = currFirst.getLength();
		Note first = previous;
		float length = first.getLength();
		return length ==currLength;
	}

	@Override
	public int getStaff() {
		return previous.getStaff();
	}

	@Override
	public Note getCurNote() {
		return current;
	}
	
	public Note getPrevNote(){
		return previous;
	}
	
	@Override
	public String toString() {
		String curString = "";
		curString += "previousTime " + previous.getTime() + " nextTime " + current.getTime() + " mnnDiff " + getMnnDifference() + " mpnDiff " + getMpnDifference() + " staff "+ previous.getStaff();
		return curString;
	}

}
