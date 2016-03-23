import static org.junit.Assert.assertEquals;

import javax.swing.JFrame;

import edu.cnu.cs.gooey.Gooey;
//import edu.cnu.cs.gooey.GooeyDialog;
import edu.cnu.cs.gooey.GooeyFrame;

public class PhoneBookTest {

	public void testFrameStartsUpCorrectly(){
		Gooey.capture(new GooeyFrame(){
			@Override
			public void invoke(){
				PhoneBook.main(new String []{});
			}
			@Override
			public void test(JFrame f){
				//assertTrue("JFrame should be visible", f.isShowing());
				assertEquals( "Incorrect title", "PhoneBook", f.getTitle() );
			}
		});
	}
}
