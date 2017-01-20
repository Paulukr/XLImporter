package mp2.ng.hw.hw3.t15;

public class ArbitraryPriorityQue<E> {
	class PriorityContainer implements Comparable<PriorityContainer>{
		E object;
		int priority;
		
		public PriorityContainer(E object, int priority) {
			this.object = object;
			this.priority = priority;
		}

		@Override
		public int compareTo(ArbitraryPriorityQue<E>.PriorityContainer o) {
			return Integer.compare(priority, o.priority);
		}
	}
	
	PriorityQueue<PriorityContainer> priorityQueue = new PriorityQueue<>();
	
	public int size() {
		return priorityQueue.size();
	}

	public boolean isEmpty() {
		return priorityQueue.isEmpty();
	}

	public <E> E[] toArray(E[] a) {
		return (E[]) priorityQueue.toArray((E[]) a);
	}

	public E peek() {
		return priorityQueue.peek().object;
	}

	public  E poll() {
		return priorityQueue.poll().object;
	}

	public boolean addWithPriority(E e, int priority) {
		if (e != null) {
			priorityQueue.add(new PriorityContainer(e, priority));
			return true;
		}
		return false;
	}
}
