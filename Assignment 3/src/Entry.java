
public class Entry {
	private String name;
	private String number;
	private boolean work;
	private int listSize = 0;
	
	public Entry(String nm, String nmb, boolean wrk){
		name = nm;
		number = nmb;
		work = wrk;
		listSize++;
	}
	public String getName(){
		return name;
	}
	public String getNumber(){
		return number;
	}
	public boolean getWork(){
		return work;
	}
	public int getListSize(){
		return listSize;
	}
}
