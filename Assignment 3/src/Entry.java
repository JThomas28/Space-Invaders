/**
 * Entry object. Stores person's name, phone number, and whether the phone
 * number is their work or home phone.
 * 
 * @author JonathanThomas
 * @version 3/22/16
 */
public class Entry {
	/**
	 * name holds the name of the person.
	 */
	private String name;

	/**
	 * number holds the person's phone number
	 */
	private String number;

	/**
	 * work is true if the person checked the 'work' checkbox when entering
	 * their data. False otherwise. home is the same
	 */
	private boolean work;
	private boolean home;

	/**
	 * Contructor for an entry. Holds user's name, number, and whether the phone
	 * number is their work or home phone or both.
	 * 
	 * @param nm
	 *            is the name entered by the user
	 * @param nmb
	 *            - the phone number entered by user
	 * @param wrk
	 *            - true if user checked 'work' checkbox, false otherwise
	 * @param hme
	 *            - true if user checked 'home' checkbox, false otherwise
	 */
	public Entry(String nm, String nmb, boolean wrk, boolean hme) {
		name = nm;
		number = nmb;
		work = wrk;
		home = hme;
	}

	/**
	 * Return's the name of the current entry
	 * 
	 * @return name - name of the current entry
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return's the current entry's phone number
	 * 
	 * @return number - phone number of the current entry
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Return's whether or not the current phone number is work phone or not.
	 * 
	 * @return work - true if work checkbox was checked, fasle otherwise
	 */
	public boolean getWork() {
		return work;
	}

	/**
	 * Return's whether or not the current phone number is a home phone or not.
	 * 
	 * @return home - true if home checkbox was checked, fasle otherwise
	 */
	public boolean getHome() {
		return home;
	}
}
