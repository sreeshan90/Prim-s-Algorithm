package com.sreesha.code;

import java.lang.Comparable;
import java.lang.reflect.Array;

/**
 * 
 * @author Sreesha Nagaraj Indexed priority queue implementation.
 * @param <T>
 */
public class PriorityQueueIndexed<T extends Comparable<? super T> & PQIndex> {
	T[] queue;
	static int size = 0; // number of elements

	/** Build a priority queue with a given array q */
	PriorityQueueIndexed(T[] q) {

		this.queue = q;
		this.size = q.length - 1;

	}

	/** Create an empty priority queue of given maximum size */
	@SuppressWarnings("unchecked")
	PriorityQueueIndexed(int n) {

		this.queue = (T[]) Array.newInstance(queue.getClass()
				.getComponentType(), n); // reflection
		this.size = n;

	}

	/**
	 * 
	 * @param x
	 *            : value to be inserted in the queue check whether queue is
	 *            full or not. if queue is full resize the queue call add
	 *            function to locate the exact position of x.
	 */
	void insert(T x) {

		if (size == queue.length - 1) {
			resizeQueue(queue.length * 2 + 1);
		}
		add(x);
	}

	/**
	 * Resize the queue if queue is full
	 * 
	 * @param n
	 *            : new size
	 */
	private void resizeQueue(int newSize) {
		// TODO Auto-generated method stub
		T[] oldQueue = queue;
		T[] newQueue = (T[]) Array.newInstance(queue.getClass()
				.getComponentType(), newSize);
		for (int i = 1; i < oldQueue.length; i++) {
			newQueue[i] = oldQueue[i];

		}
		queue = newQueue;

	}

	/**
	 * Add the value in the priority queue call percolate up to locate the exact
	 * position of the new element
	 * 
	 * @param x
	 *            : value to be added
	 */
	void add(T x) {
	
		int pos = size + 1;
		queue[pos] = x;
		percolateUp(pos);

		size++;
	}

	T remove() {
		return deleteMin();
	}

	T deleteMin() {
		T val = queue[1];
		queue[1] = queue[size];
		size--;
		percolateDown(1);

		return val;
	}

	/** restore heap order property after the priority of x has decreased */
	void decreaseKey(T x) {
		percolateUp(x.getIndex());

	}

	/**
	 * 
	 * @return minimum value of the queue.
	 */
	T min() {
		return queue[1]; // returning minimum value
	}

	/**
	 * Priority of element at index i of queue has decreased. It may violate
	 * heap order. Restore heap property
	 */
	void percolateUp(int i) {

		T val = queue[i];

		for (; i > 1 && val.compareTo(queue[i / 2]) < 0; i = i / 2) {
			queue[i] = queue[i / 2]; //swap
			queue[i].putIndex(i); //update index

		}
		queue[i] = val;
		queue[i].putIndex(i); // update index

	}

	/**
	 * Create heap order for sub-heap rooted at node i. Precondition: Heap order
	 * may be violated at node i, but its children are roots of heaps that are
	 * fine. Restore heap property
	 */
	void percolateDown(int i) {
		if (2 * i > size)
			return; // overflow

		T tmp = queue[i];
		int child;

		for (; 2 * i <= size; i = child) {
			child = 2 * i;

			if (child != size && queue[child].compareTo(queue[child + 1]) > 0)
				child++;

			if (tmp.compareTo(queue[child]) > 0) {
				queue[i] = queue[child]; // swap
				queue[i].putIndex(i); // update index
			} else
				break;
		}
		queue[i] = tmp;
		queue[i].putIndex(i); // update index

	}

	/**
	 * Create a heap. Precondition: none. Array may violate heap order in many
	 * places.
	 */
	void buildHeap(T[] arr) {

		for (int i = (size + 1) / 2; i >= 1; i--)
			percolateDown(i);

	}

	/**
	 * checks whether queue is empty or not
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

}
