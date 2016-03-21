import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PhoneBook extends JFrame {
	private int index;
	public PhoneBook() {
		super("PhoneBook");
		setLayout(new GridLayout(5, 1));

		JPanel first = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		first.add(new JLabel("Entries:"));
		JLabel nbrOfEntries = new JLabel("");
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
		JButton before = new JButton("Before");
		JButton next = new JButton("Next");
		JButton add = new JButton("Add");
		JButton delete = new JButton("Delete");
		fifth.add(before);
		fifth.add(next);
		fifth.add(add);
		fifth.add(delete);

		add(first);
		add(second);
		add(third);
		add(fourth);
		add(fifth);
		
		pack();
		
		ArrayList <Entry> list = new ArrayList <Entry>();
		
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Entry person = new Entry(name.getText(),phoneNum.getText(),work.isSelected());
				list.add(person);
				nbrOfEntries.setText(list.size() + "/" + list.size());
				index = list.size();
			}
		});
		next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Entry nextPerson = list.get(index+1);
				name.setText(nextPerson.getName());
				phoneNum.setText(nextPerson.getNumber());
				nbrOfEntries.setText(index+1 + "/" + list.size());
			}
		});
		before.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Entry prevPerson = list.get(index-1);
				name.setText(prevPerson.getName());
				phoneNum.setText(prevPerson.getNumber());
				nbrOfEntries.setText(index-1 + "/" + list.size());
			}
		});
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		JFrame frame = new PhoneBook();
		frame.setVisible(true);
	}
}
