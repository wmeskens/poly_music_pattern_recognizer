package poly_recognition;

public interface PatternValue {
	
	boolean sameValue(PatternValue val);
	
	int getStaff();

	Note getCurNote();
}
