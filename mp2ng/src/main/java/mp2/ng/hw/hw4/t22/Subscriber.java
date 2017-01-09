package mp2.ng.hw.hw4.t22;

public class Subscriber implements ObserverPublishing {
	static int nextSubscriberID;
	String name;
	String address;
	int index;
	int subscriberID;
	
	public Subscriber() {
		subscriberID = nextSubscriberID++;
	}

	@Override
	public void update(Issue issue) {
		System.out.println(subscriberID + " Issue received " + issue.name + " " + issue.issueNo);
	}

}
