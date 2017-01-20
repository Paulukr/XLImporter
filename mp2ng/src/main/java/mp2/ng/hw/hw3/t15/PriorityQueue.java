package mp2.ng.hw.hw3.t15;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class PriorityQueue<T extends Comparable<? super T>> {// implements
	// Queue<E> arbitrary
	Heap<T> heap = new Heap<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public <E> E[] toArray(E[] a) {
		return (E[]) heap.toArray((T[]) a);
	}

	public T peek() {
		return heap.peek();
	}

	public T poll() {
		return heap.pop();
	}

	public boolean add(T e) {
		if (e != null) {
			heap.push(e);
			return true;
		}
		return false;
	}
}
