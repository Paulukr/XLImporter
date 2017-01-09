package mp2.ng.hw.hw4.t22;

public class PostOffice extends SubjectImproved implements ObserverPublishing{
	int PostalIndex;

	@Override
	public void update(Issue issue) {
		notifyObservers(issue);
	}
	//factory

}
