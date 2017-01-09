package mp2.ng.hw.hw4.t22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostOffice extends SubjectImproved implements ObserverPublishing{
	int postalIndex;
	PostalService thePress;
	
	Map<Integer, List<Subscriber>> subscriptionList = new HashMap<>();
	
	
	public PostOffice(PostalService thePress) {
		this.thePress = thePress;
	}

	public boolean subcribe(int subscriptionIndex, Subscriber subscriber){
		if(!thePress.pressCatalog.containsKey(subscriptionIndex))
			return false;
		if(subscriptionList.get(subscriptionIndex) == null)
			subscriptionList.put(subscriptionIndex, new ArrayList<>());
		subscriptionList.get(subscriptionIndex).add(subscriber);
		return true;
	}

	@Override
	public void update(Issue issue) {
		notifyObservers(issue);
	}

	@Override
	public void notifyObservers(Issue issue) {
		List<Subscriber> list = subscriptionList.get(issue.subcriptionIndex);
		if(list != null)
			list.parallelStream().forEach(a -> a.update(issue));
	}
	
	//factory

}
