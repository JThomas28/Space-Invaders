import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import org.junit.Test;

import edu.cnu.cs.gooey.Gooey;
import edu.cnu.cs.gooey.GooeyDialog;
import edu.cnu.cs.gooey.GooeyFrame;

/**
 * This is the test class for PhoneBook. Tests all components and different
 * combinations of button presses.
 * 
 * @author JonathanThomas
 * @version 3/25/16
 */
public class PhoneBookTest {

	/**
	 * This test ensures all the buttons are enabled or disabled correctly based
	 * on the scenerio.
	 */
	@Test
	public void testButtonsMethod() {
		Gooey.capture(new GooeyFrame() {
			@Override
			public void invoke() {
				PhoneBook.main(new String[] {});
			}

			@Override
			public void test(JFrame f) {
				JButton add = Gooey.getButton(f, "Add");
				JButton before = Gooey.getButton(f, "Before");
				JButton next = Gooey.getButton(f, "Next");
				JButton delete = Gooey.getButton(f, "Delete");

				assertFalse(before.isEnabled());
				assertFalse(next.isEnabled());
				assertFalse(delete.isEnabled());
				assertTrue(add.isEnabled());

				add.doClick();
				assertFalse(before.isEnabled());
				assertFalse(next.isEnabled());
				assertTrue(delete.isEnabled());
				assertTrue(add.isEnabled());

				add.doClick();
				assertTrue(before.isEnabled());
				assertFalse(next.isEnabled());
				assertTrue(delete.isEnabled());
				assertTrue(add.isEnabled());

				before.doClick();
				assertFalse(before.isEnabled());
				assertTrue(next.isEnabled());
				assertTrue(delete.isEnabled());
				assertTrue(add.isEnabled());

				delete.doClick();
				delete.doClick();
				assertFalse(before.isEnabled());
				assertFalse(next.isEnabled());
				assertFalse(delete.isEnabled());
				assertTrue(add.isEnabled());
			}

		});
	}

	/**
	 * This test tests that all the components of the JFrame are set up
	 * correctly
	 */
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

	/**
	 * This test tests the exit menu displays a dialog box asking the user if
	 * he/she is sure he/she wants to exit. Tests cases where user clicks
	 * cancel, no, or yes. 'Yes' test keeps failing but it works when I run the
	 * program myself. Not sure what's going on here
	 */
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

					// tests clicking no in dialog box closes dialog but not
					// main window
					@Override
					public void test(JDialog dialog) {
						assertEquals("Incorrect title. Should be: Select an Option", dialog.getTitle(),
								"Select an Option");

						JButton no = Gooey.getButton(dialog, "No");
						// JButton yes = Gooey.getButton(dialog, "Yes");

						no.doClick();
						assertTrue("Frame should still be showing", f.isShowing());
					}
				});

				Gooey.capture(new GooeyDialog() {
					@Override
					public void invoke() {
						exit.doClick();
					}

					// test cancel closes dialog but not main window
					@Override
					public void test(JDialog dialog) {
						JButton cancel = Gooey.getButton(dialog, "Cancel");
						assertTrue("Frame should still be showing", f.isShowing());
						assertTrue("Dialog should be showing", dialog.isShowing());

						cancel.doClick();
						assertFalse("Dialog should not be showing", dialog.isShowing());
						assertTrue("Frame should still be showing", f.isShowing());
					}
				});

				Gooey.capture(new GooeyDialog() {
					@Override
					public void invoke() {
						exit.doClick();
					}

					// test clicking yes in dialog closes dialog and main window
					@Override
					public void test(JDialog dialog) {
						JButton yes = Gooey.getButton(dialog, "Yes");
						assertTrue("Frame should still be showing", f.isShowing());
						assertTrue("Dialog should be showing", dialog.isShowing());

						dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
						assertFalse("Dialog should not be showing", dialog.isShowing());
						assertTrue("Frame is not showing", f.isShowing());

						yes.doClick();
						assertFalse("Dialog should not be showing", dialog.isShowing());
						// Don't know why this is failing
						assertFalse("Frame should not be showing", f.isShowing());
					}
				});
			}
		});
	}

	/**
	 * This test tests the about window is shown and the 'ok' button closes the
	 * window without closing the original frame
	 */
	@Test
	public void testsAboutWindow() {
		Gooey.capture(new GooeyFrame() {
			@Override
			public void invoke() {
				PhoneBook.main(new String[] {});
			}

			@Override
			public void test(JFrame f) {
				JMenuBar menus = Gooey.getMenuBar(f);
				JMenu help = Gooey.getMenu(menus, "Help");
				JMenuItem about = Gooey.getMenu(help, "About...");
				Gooey.capture(new GooeyDialog() {
					@Override
					public void invoke() {
						about.doClick();
					}

					@Override
					public void test(JDialog dialog) {
						JButton ok = Gooey.getButton(dialog, "OK");
						assertTrue(dialog.isShowing());

						ok.doClick();
						assertFalse("Dialog should not be showing", dialog.isShowing());
						assertTrue("Main frame should be showing", f.isShowing());
					}
				});
			}
		});
	}

	/**
	 * This test tests all different button press combinations and ensured the
	 * entries label is incremented or decremented correctly
	 */
	@Test
	public void testCounterIncrementsCorrectly() {
		Gooey.capture(new GooeyFrame() {
			@Override
			public void invoke() {
				PhoneBook.main(new String[] {});
			}

			@Override
			public void test(JFrame f) {
				JLabel entries = Gooey.getLabel(f, "0/0");
				JButton add = Gooey.getButton(f, "Add");
				JButton before = Gooey.getButton(f, "Before");
				JButton next = Gooey.getButton(f, "Next");
				JButton delete = Gooey.getButton(f, "Delete");

				add.doClick();
				add.doClick();
				assertEquals(entries.getText(), "2/2");
				before.doClick();
				assertEquals(entries.getText(), "1/2");
				add.doClick();
				assertEquals(entries.getText(), "3/3");
				before.doClick();
				next.doClick();
				assertEquals(entries.getText(), "3/3");
				delete.doClick();
				assertEquals(entries.getText(), "2/2");
			}
		});
	}

	/**
	 * This test tests that pressing the different buttons displays the correct
	 * entries
	 */
	@Test
	public void testButtonsDisplayCorrectPerson() {
		Gooey.capture(new GooeyFrame() {
			@Override
			public void invoke() {
				PhoneBook.main(new String[] {});
			}

			@Override
			public void test(JFrame f) {
				JButton delete = Gooey.getButton(f, "Delete");
				JButton add = Gooey.getButton(f, "Add");
				JButton before = Gooey.getButton(f, "Before");
				JButton next = Gooey.getButton(f, "Next");

				JTextField name = Gooey.getComponent(f, JTextField.class, "Name field");
				JTextField phone = Gooey.getComponent(f, JTextField.class, "Phone Number Field");

				JCheckBox work = Gooey.getComponent(f, JCheckBox.class, "Work Checkbox");
				JCheckBox home = Gooey.getComponent(f, JCheckBox.class, "Home Checkbox");

				name.setText("Jane");
				phone.setText("456");
				work.setSelected(false);
				home.setSelected(false);
				add.doClick();

				name.setText("Jonathan");
				phone.setText("123");
				work.setSelected(true);
				home.setSelected(false);
				add.doClick();

				name.setText("Bob");
				phone.setText("789");
				work.setSelected(false);
				home.setSelected(true);
				add.doClick();

				// test before button displays prior entry
				before.doClick();
				assertEquals("Name before not correct", name.getText(), "Jonathan");
				assertEquals("Number before not same", phone.getText(), "123");
				assertTrue(work.isSelected());
				assertFalse(home.isSelected());

				// test add when in middle of list adds entry to end
				name.setText("John");
				phone.setText("987");
				work.setSelected(true);
				home.setSelected(true);
				add.doClick();

				before.doClick();
				assertEquals(name.getText(), "Bob");
				assertEquals(phone.getText(), "789");
				assertFalse(work.isSelected());
				assertTrue(home.isSelected());

				// test next displays next entry
				next.doClick();
				assertEquals("Name doesn't match", name.getText(), "John");
				assertEquals("Number not same", phone.getText(), "987");
				assertTrue(work.isSelected());
				assertTrue(home.isSelected());

				// test delete at end of list
				delete.doClick();
				assertEquals("Name doesn't match", name.getText(), "Bob");
				assertEquals("Number doesn't match", phone.getText(), "789");
				assertTrue(home.isSelected());
				assertFalse(work.isSelected());

				before.doClick();
				assertTrue(work.isSelected());
				assertFalse(home.isSelected());

				before.doClick();
				assertFalse(work.isSelected());
				assertFalse(home.isSelected());

				// test delete at beginning of list
				assertEquals("Name not same", name.getText(), "Jane");
				assertEquals("Number doesn't match", phone.getText(), "456");

				delete.doClick();
				delete.doClick();
				delete.doClick();

				// test delete last item clears everything
				assertEquals("Name should be empty", name.getText(), "");
				assertEquals("Number doesn't match", phone.getText(), "");
				assertFalse(work.isSelected());
				assertFalse(home.isSelected());
			}
		});
	}
}
