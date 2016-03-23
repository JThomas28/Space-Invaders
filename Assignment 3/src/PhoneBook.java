import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * PhoneBook uses JFrame as a type of phonebook entry system. Can add new
 * entries, delete entries, and view the next or previous entries.
 * 
 * @author JonathanThomas
 * @version 3/22/16
 */
@SuppressWarnings("serial")
public class PhoneBook extends JFrame {
	/**
	 * index is the pointer to the current phonebook entry in the arraylist
	 */
	private int index = 0;

	/**
	 * list is the arraylist containing all the entries.
	 */
	private ArrayList<Entry> list = new ArrayList<Entry>();

	/**
	 * before, next, add, and delete are the JButtons used to give commands to
	 * the program.
	 */
	private JButton before = new JButton("Before");
	private JButton next = new JButton("Next");
	private JButton add = new JButton("Add");
	private JButton delete = new JButton("Delete");

	/**
	 * Constructor for phonebook. Creates the window's grid layout and adds all
	 * buttons, fields, and menus
	 */
	public PhoneBook() {
		// Set title of window
		super("PhoneBook");
		setLayout(new GridLayout(5, 1));

		// Set up menu bars
		JMenuBar menus = new JMenuBar();
		JMenu program = new JMenu("Program");
		JMenuItem exit = new JMenuItem("Exit");
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About...");

		// add menus to menu bar
		menus.add(program);
		menus.add(help);

		// add submenus to menus
		program.add(exit);
		help.add(about);

		// add menubar to window
		setJMenuBar(menus);

		// action listener for exit button. Displays confirm dialog box.
		// If yes, exits confirm box and main window
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Want to exit?");
				if (result == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		// action listener for about button. Displays dialogue box with author's
		// name
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "PhoneBook\nby JonathanThomas", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Set up all the panels

		JPanel first = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		first.add(new JLabel("Entries:"));
		JLabel nbrOfEntries = new JLabel("0/0");
		first.add(nbrOfEntries);

		JPanel second = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		JTextField name = new JTextField(40);
		second.add(new JLabel("Name"));
		second.add(name);

		JPanel third = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		JTextField phoneNum = new JTextField(20);
		third.add(new JLabel("Phone"));
		third.add(phoneNum);

		JPanel fourth = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JCheckBox home = new JCheckBox("Home");
		JCheckBox work = new JCheckBox("Work");
		fourth.add(home);
		fourth.add(work);

		JPanel fifth = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

		// add all buttons to the fifth panel
		fifth.add(before);
		fifth.add(next);
		fifth.add(add);
		fifth.add(delete);

		// initially, all buttons are disabled except add which is always
		// enabled
		before.setEnabled(false);
		next.setEnabled(false);
		delete.setEnabled(false);
		add.setEnabled(true);

		add(first);
		add(second);
		add(third);
		add(fourth);
		add(fifth);

		// pack all panels into window
		pack();

		// add's action listener. Adds an entry object to the array list of
		// entries.
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Entry person = new Entry(name.getText(), phoneNum.getText(), work.isSelected(), home.isSelected());
				list.add(person);
				index = list.size() - 1;
				nbrOfEntries.setText(list.size() + "/" + list.size());
				checkButtons();
			}
		});

		// next's action listener. Displays next stored entry if there is one.
		// Button disabled if there's no next entry
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index += 1;
				Entry nextPerson = list.get(index);
				name.setText(nextPerson.getName());
				phoneNum.setText(nextPerson.getNumber());
				work.setSelected(nextPerson.getWork());
				home.setSelected(nextPerson.getHome());
				nbrOfEntries.setText(index + 1 + "/" + list.size());
				checkButtons();
			}
		});

		// before's action listener. Displays previous entry if there is one.
		// Disabled if we are looking at entry from beginning of list.
		before.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index -= 1;
				Entry prevPerson = list.get(index);
				name.setText(prevPerson.getName());
				phoneNum.setText(prevPerson.getNumber());
				work.setSelected(prevPerson.getWork());
				home.setSelected(prevPerson.getHome());
				nbrOfEntries.setText(index + 1 + "/" + list.size());
				checkButtons();
			}
		});

		// Delete's action listener. Deletes entry we are looking at and
		// displays next entry if there is one. If not, displays previous. If we
		// delete the last entry in the list, clears all fields.
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (index == 0 && list.size() == 1) {
					list.remove(index);
					index = 0;
					name.setText("");
					phoneNum.setText("");
					work.setSelected(false);
					home.setSelected(false);
					checkButtons();
					nbrOfEntries.setText("0/0");
				}
				else if (index == list.size() - 1) {
					// at end of list. Delete current index and index points to
					// previous
					int newIndex = index - 1;
					Entry replace = list.get(newIndex);
					list.remove(index);
					index = newIndex;

					name.setText(replace.getName());
					phoneNum.setText(replace.getNumber());
					work.setSelected(replace.getWork());
					home.setSelected(replace.getHome());
					nbrOfEntries.setText(index + 1 + "/" + list.size());
					checkButtons();
				}
				else {
					int newIndex = index;
					index += 1;
					Entry replace = list.get(index);
					list.remove(newIndex);
					index = newIndex;

					name.setText(replace.getName());
					phoneNum.setText(replace.getNumber());
					work.setSelected(replace.getWork());
					home.setSelected(replace.getHome());
					nbrOfEntries.setText(index + 1 + "/" + list.size());
					checkButtons();
				}
			}
		});

		// center frame and set close operation
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Helper method. Called each time a button is pressed to ensure the correct
	 * buttons are enabled/disabled.
	 */
	public void checkButtons() {
		if (list.size() > 0) {
			delete.setEnabled(true);
		}
		else {
			delete.setEnabled(false);
		}

		if (index + 1 < list.size()) {
			next.setEnabled(true);
		}
		else {
			next.setEnabled(false);
		}
		if (index > 0 && list.size() > 0) {
			before.setEnabled(true);
		}
		else {
			before.setEnabled(false);
		}
	}

	/**
	 * Main method. Displays frame.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new PhoneBook();
		frame.setVisible(true);
	}
}