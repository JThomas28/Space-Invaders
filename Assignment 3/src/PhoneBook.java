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

@SuppressWarnings("serial")
public class PhoneBook extends JFrame {
	// ****add javadoc to both index and list****
	private int index = 0;
	private ArrayList<Entry> list = new ArrayList<Entry>();
	private JButton before = new JButton("Before");
	private JButton next = new JButton("Next");
	private JButton add = new JButton("Add");
	private JButton delete = new JButton("Delete");
	private int newIndex = 0;
	public PhoneBook() {
		super("PhoneBook");
		setLayout(new GridLayout(5, 1));
		
		JMenuBar menus = new JMenuBar();
		JMenu program = new JMenu("Program");
		JMenuItem exit = new JMenuItem("Exit");
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About...");
		
		JOptionPane sure = new JOptionPane("Select an Option");
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		JButton cancel = new JButton("Cancel");
		sure.add(yes);
		sure.add(no);
		sure.add(cancel);
		
		menus.add(program);
		menus.add(help);
		
		program.add(exit);
		help.add(about);
		
		setJMenuBar(menus);

		JPanel first = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		first.add(new JLabel("Entries:"));
		JLabel nbrOfEntries = new JLabel("0/0");
		first.add(nbrOfEntries);

		JPanel second = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		JTextField name = new JTextField(40);
		second.add(new JLabel("Name:"));
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
		
		fifth.add(before);
		fifth.add(next);
		fifth.add(add);
		fifth.add(delete);
		
		before.setEnabled(false);
		next.setEnabled(false);
		delete.setEnabled(false);
		add.setEnabled(true);

		add(first);
		add(second);
		add(third);
		add(fourth);
		add(fifth);
		
		
		
		pack();

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Entry person = new Entry(name.getText(), phoneNum.getText(), work.isSelected());
				list.add(person);
				index++;
				nbrOfEntries.setText(index + "/" + list.size());
				checkButtons();
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newIndex = index++;
				Entry nextPerson = list.get(newIndex);
				name.setText(nextPerson.getName());
				phoneNum.setText(nextPerson.getNumber());
				nbrOfEntries.setText(newIndex + "/" + list.size());
				checkButtons();
			}
		});

		before.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newIndex = index--;
				Entry prevPerson = list.get(newIndex);
				name.setText(prevPerson.getName());
				phoneNum.setText(prevPerson.getNumber());
				nbrOfEntries.setText(newIndex + "/" + list.size());
				checkButtons();
			}
		});

		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void checkButtons(){
		if (index == list.size() && list.size() != 0) {
			next.setEnabled(false);
			before.setEnabled(true);
			delete.setEnabled(true);
		}
		
		if(index == 0 && list.size()!=0){
			before.setEnabled(false);
			delete.setEnabled(true);
			next.setEnabled(true);
		}
		
		if(index >0 && index <list.size()){
			before.setEnabled(true);
			next.setEnabled(true);
			delete.setEnabled(true);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new PhoneBook();
		frame.setVisible(true);
	}
}
