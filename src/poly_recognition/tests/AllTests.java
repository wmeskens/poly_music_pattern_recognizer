package poly_recognition.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ExactPatternTest.class, MnnTransPatternTest.class,
		TransPatternTest.class,MpnTransPatternTest.class })
public class AllTests {
	
}
