package mp2.ng.hw.hw4.t22;

public class Issue {
	String name;
	boolean magasine;//else newspaper
	int subcriptionIndex;
	int issueNo;
	Object content;
	
	
	public Issue(String name, boolean magasine) {
		this.name = name;
		this.magasine = magasine;
	}

	int publish(Object content){
		this.content = content;
		return ++issueNo;
	}
	
}
