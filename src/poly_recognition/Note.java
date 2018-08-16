package poly_recognition;

public class Note implements PatternValue{

	//morphetic pitch number
	private final int mpn;
	private final float length;
	protected final float time;
	protected final int mnn;
	protected final int staff;
	protected final int timeMemId;
	
	public Note (float time, int mnn, int mpn, float length, int staff, int memoryId){
		this.time = time;
		this.mnn = mnn;
		this.mpn = mpn;
		this.length = length;
		this.staff = staff;
		this.timeMemId = memoryId;
	}


	public int getMpn() {
		return mpn;
	}

	public float getLength() {
		return length;
	}


	public int getStaff() {
		return staff;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass()!=this.getClass()){
			return false;
		}
		boolean allEqual = false;
		Note note = (Note) obj;
		boolean sameTime = this.time == note.getTime();
		boolean sameValue = sameValue(note);
		if(sameTime && sameValue){
			allEqual = true;
		}
		return allEqual;
	}
	
	@Override
	public String toString() {
		String curString = "";
		curString += "time " + time + " mnn " + mnn + " mpn " + mpn + " length "+ length +" staff "+staff;
		return curString;
	}

	public boolean sameValue(PatternValue val){
		boolean allEqual = false;
		if(val.getClass().equals(Note.class)){
			Note next = (Note) val;
			boolean sameMnn = this.mnn == next.getMnn();
			boolean sameMpn = this.mpn == next.getMpn();
			boolean sameLength = this.length == next.getLength();
			boolean sameStaff = this.staff == next.getStaff();
			if(sameMnn && sameMpn && sameLength && sameStaff){
				allEqual = true;
			}
		}
		return allEqual;
	}
	
	public boolean sameMnnValue(Note next) {
		boolean same = false;
		if(this.mnn==next.getMnn()){
			same =true;
		}
		return same;
	}
	
	public boolean sameMpnValue(Note next) {
		boolean same = false;
		if(this.mpn==next.getMpn()){
			same =true;
		}
		return same;
	}

	public boolean sameLengthValue(Note next) {
		boolean same = false;
		if(this.length==next.getLength()){
			same =true;
		}
		return same;
	}

	public DifferenceValue difference(Note next) {
		DifferenceValue diff = new DifferenceValue(this,next);
		return diff;
	}
	
	public float getTime(){
		return time;
	}
	
	public int getMnn(){
		return mnn;
	}
	
	public int getTimeMemId(){
		return timeMemId;
	}


	@Override
	public Note getCurNote() {
		return this;
	}
	
	
}
