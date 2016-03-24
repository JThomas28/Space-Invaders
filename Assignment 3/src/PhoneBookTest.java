import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.junit.Test;

import edu.cnu.cs.gooey.Gooey;
import edu.cnu.cs.gooey.GooeyDialog;
import edu.cnu.cs.gooey.GooeyFrame;

public class PhoneBookTest {

	@Test
	public void testFrameStartsUpCorrectly() {
		Gooey.capture(new GooeyFrame() {
			@Override
			public void invoke() {
				PhoneBook.main(new String[] {});
			}

			@Override
			public void test(JFrame f) {
				JButton addButton = Gooey.getButton(f, "Add");
				JButton deleteButton = Gooey.getButton(f, "Delete");
				JButton nextButton = Gooey.getButton(f, "Next");
				JButton beforeButton = Gooey.getButton(f, "Before");
				List<JTextField> fields = Gooey.getComponents(f, JTextField.class);
				for (JTextField e : fields) {
					assertTrue("Field should be empty", e.getText().equals(""));
				}

				assertTrue("Frame should be showing", f.isShowing());

				assertTrue("Add should be enabled", addButton.isEnabled());
				assertFalse("Delete button should not be enabled", deleteButton.isEnabled());
				assertFalse("Next button should not be enabled", nextButton.isEnabled());
				assertFalse("Before button should not be enabled", beforeButton.isEnabled());
				assertEquals("Incorrect title", "PhoneBook", f.getTitle());
			}
		});
	}

	@Test
	public void testCloseThroughMenu() {
		Gooey.capture(new GooeyFrame() {
			@Override
			public void invoke() {
				PhoneBook.main(new String[] {});
			}

			@Override
			public void test(JFrame f) {
				JMenuBar menuBar = Gooey.getMenuBar(f);
				JMenu program = Gooey.getMenu(menuBar, "Program");
				JMenuItem exit = Gooey.getMenu(program, "Exit");
				
				Gooey.capture(new GooeyDialog() {
					@Override
					public void invoke() {
						exit.doClick();
					}
					
					@Override
					public void test(JDialog dialog) {
						assertEquals("Incorrect title. Should be: Select an Option", dialog.getTitle(),
								"Select an Option");
						JButton cancel = Gooey.getButton(dialog, "Cancel");
						JButton no = Gooey.getButton(dialog, "No");
						JButton yes = Gooey.getButton(dialog, "Yes");

						no.doClick();
						assertTrue("Frame should still be showing", f.isShowing());
						cancel.doClick();
						assertTrue("Frame should still be showing", f.isShowing());


						// test closing dialog will let us continue
//						int actual = dialog.getDefaultCloseOperation();
//						assertTrue("Incorrect result 1", actual == JDialog.DISPOSE_ON_CLOSE);
						
						dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
						assertFalse("Dialog should not be showing", dialog.isShowing());
						assertTrue("Frame is not showing",f.isShowing());
						
						// yes button not being pressed
						yes.doClick();
						assertFalse("Dialog should not be showing", dialog.isShowing());
						assertFalse("Frame should not be showing", f.isShowing());
					}
				});
			}
		});
	}
	
	@Test
	public void testsAboutWindow(){
		Gooey.capture(new GooeyFrame(){
			@Override
			public void invoke(){
				PhoneBook.main(new String []{});
			}
			
			@Override
			public void test(JFrame f){
				JMenuBar menus = Gooey.getMenuBar(f);
				JMenu help = Gooey.getMenu(menus, "Help");
				JMenuItem about = Gooey.getMenu(help, "About...");
				Gooey.capture(new GooeyDialog(){
					@Override
					public void invoke(){
						about.doClick();
					}
					
					@Override
					public void test(JDialog dialog){
						
					}
				});
			}
		});
	}
}
