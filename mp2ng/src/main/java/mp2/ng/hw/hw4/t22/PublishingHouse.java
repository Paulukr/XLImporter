package mp2.ng.hw.hw4.t22;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PublishingHouse extends SubjectImproved{
	List<Issue> issuesLive = new ArrayList<>();
	Deque<List<Issue>> issuesArchive = new ArrayDeque<>();
	
	public int addNewTitle(Issue issue){
		issuesLive.add(issue);
		return issuesLive.size();
	}
	public int removeTitle(String name){
		for (int i = 0; i < issuesLive.size(); i++) {
			if(issuesLive.get(i).name == name){
				issuesLive.remove(i);
				return issuesLive.size();
			}
		}
		return -1;
	}
	public int publish(){
		issuesLive.parallelStream().forEach(a -> a.publish(null));
		issuesArchive.add(issuesLive);
		issuesArchive.getLast().parallelStream().forEach(a -> notifyObservers(a));
		return issuesLive.size();
	}
	void init(){
		
	}
}
