package mp2.ng.hw.hw4.t22;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ThePress {
	private static final int publishers = 2;
	private static final int titles = 2;
	private static final int postOfficesCount = 2;
	private static final int subscribersCount = 2;
	LinkedList<PublishingHouse> publishingHouses = new LinkedList<>();
	LinkedList<PostOffice> postOffices = new LinkedList<>();
	LinkedList<Subscriber> subscribers = new LinkedList<>();
	void init(){
		for (int i = 0; i < publishers; i++) {
			publishingHouses.add(new PublishingHouse());
			for (int j = 0; j < titles; j++) 
				publishingHouses.get(i).addNewTitle(new Issue("Title" + (i+1) + (j+1), j%2 == 0));
		}
		for (int i = 0; i < postOfficesCount; i++) {
			postOffices.add(new PostOffice());
			publishingHouses.parallelStream().forEach(a -> a.addObserver(postOffices.getLast()));
			for (int j = 0; j < subscribersCount; j++) {
				subscribers.add(new Subscriber());
				postOffices.getLast().addObserver(subscribers.getLast());
			}
		}

	}
	void run(){
		publishingHouses.stream().forEach(PublishingHouse::publish);
	}
	public static void main(String[] args) {
		
		ThePress thePress = new ThePress();
		thePress.init();
		thePress.run();
		

	}

}
