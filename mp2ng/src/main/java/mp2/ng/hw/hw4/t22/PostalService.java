package mp2.ng.hw.hw4.t22;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class PostalService {
	private static final int publishers = 2;
	private static final int titles = 2;
	private static final int postOfficesCount = 2;
	private static final int subscribersCount = 2;
	LinkedList<PublishingHouse> publishingHouses = new LinkedList<>();
	LinkedList<PostOffice> postOffices = new LinkedList<>();
	LinkedList<Subscriber> subscribers = new LinkedList<>();
	
	Map<Integer, Issue> pressCatalog = new HashMap<>();
	int nextSubcriptionIndex;
	void init(){
		for (int i = 0; i < publishers; i++) {
			publishingHouses.add(new PublishingHouse());
			for (int j = 0; j < titles; j++){
				Issue issue = new Issue("Title" + (i+1) + (j+1), j%2 == 0, nextSubcriptionIndex++);
				publishingHouses.get(i).establishNewTitle(issue);
				pressCatalog.put(issue.subcriptionIndex, issue);
			}
		}
		for (int i = 0; i < postOfficesCount; i++) {
			postOffices.add(new PostOffice(this));
			publishingHouses.parallelStream().forEach(a -> a.addObserver(postOffices.getLast()));
			for (int j = 0; j < subscribersCount; j++) {
				subscribers.add(new Subscriber(postOffices.getLast()));
				postOffices.getLast().addObserver(subscribers.getLast());
				
			}
		}
		Random r = new Random();
		r.nextInt(3);
		subscribers.parallelStream().forEach(a -> a.subcribe(1));
	}
	void run(){
		publishingHouses.stream().forEach(PublishingHouse::publish);
	}
	public static void main(String[] args) {
		
		PostalService thePress = new PostalService();
		thePress.init();
		thePress.run();
		

	}

}
