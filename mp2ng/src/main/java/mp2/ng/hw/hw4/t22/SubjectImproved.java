package mp2.ng.hw.hw4.t22;

import java.util.ArrayList;
import java.util.List;

public abstract class SubjectImproved implements SubjectPublishing {
	List<ObserverPublishing> observers;

	@Override
	public void addObserver(ObserverPublishing observer) {
		if(observers == null)
			observers = new ArrayList<>();
		observers.add(observer);
	}

	@Override
	public void removeObserver(ObserverPublishing observer) {
		if(observers != null)
			observers.remove(observer);
	}
	public void notifyObservers(Issue issue) {
		observers.parallelStream().forEach(a -> a.update(issue));
	}
}
