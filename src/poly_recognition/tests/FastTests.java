package poly_recognition.tests;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@ExcludeCategory(Slow.class)
@SuiteClasses({ ExactPatternTest.class, MnnTransPatternTest.class,
		TransPatternTest.class })
public class FastTests {
	
}
