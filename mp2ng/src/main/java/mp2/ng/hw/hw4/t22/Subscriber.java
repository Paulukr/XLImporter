package mp2.ng.hw.hw4.t22;

public class Subscriber implements ObserverPublishing {
	static int nextSubscriberID;
	String name;
	String address;
	int postalIndex;
	int subscriberID;
	PostOffice postOffice;
	
	public Subscriber(PostOffice postOffice) {
		this.postOffice = postOffice;
		postalIndex = postOffice.postalIndex;
		subscriberID = nextSubscriberID++;
	}
	
	public boolean subcribe(int subscriptionIndex){
		return postOffice.subcribe(subscriptionIndex, this);
	}
	@Override
	public void update(Issue issue) {
		System.out.println(subscriberID + " Issue received " + issue.name + " " + issue.issueNo);
	}

}
