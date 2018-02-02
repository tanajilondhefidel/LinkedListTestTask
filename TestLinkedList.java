import java.util.Scanner;

public class TestLinkedList {

	public static MyLinkedList myList;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		/*
		 * Default constructor to initialize LinkedList head with 0.
		 */
		myList = new MyLinkedList();

		/*
		 * Adding Elements in LinkedList
		 */
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");
		int choice;
		do {
			System.out.println("Enter Choice");
			System.out.println("1 . Append Element In LinkedList");
			System.out.println("2 . Removing Tail element from LinkedList (Last Element");
			System.out.println("3 . Removing All elements from LinkedList which are greater than specified target");
			System.out.println("0. Exit");

			choice = in.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter new element to append");
				int element = in.nextInt();

				myList.add(element);

				// Getting initial elements of LinkedList
				System.out.println("LinkedList elements : " + myList);
				System.out.println("Size :" + myList.size());

				break;

			case 2:

				// Removing Tail element from LinkedList (Last Element)
				System.out.println("Removing Tail element : " + myList.removeTail());
				System.out.println("Size : " + myList.size());
				System.out.println("LinkedList Elements : " + myList);

				break;
			case 3:
				System.out.println("Enter target element to remove all elements grater than target ");
				int targetElement = in.nextInt();

				// Removing All elements from LinkedList which are greater than specified target
				System.out.println("Removing elements greater than target value :" + myList.removeAll(targetElement));
				System.out.println("Linked List Elements : " + myList);
				System.out.println("Size : " + myList.size());
				break;

			case 0:
				System.exit(0);

			default:
				System.out.println("Invalid Choice");

			}
		} while (choice != 0);

	}

}

class MyLinkedList {

	private static int counter;
	private Node head;

	/*
	 * Default Constructor
	 */
	public MyLinkedList() {

	}

	/*
	 * appends the specified element to the end of this list.
	 */
	public void add(Object data) {

		if (head == null) {
			head = new Node(data);
		} else {

			Node myTemp = new Node(data);
			Node myCurrent = head;

			if (myCurrent != null) {
				while (myCurrent.getNext() != null) {
					myCurrent = myCurrent.getNext();
				}
				myCurrent.setNext(myTemp);
			}
		}
		incrementCounter();
	}

	/*
	 * Returns value of counter i.e. size of LinkedList
	 */
	private static int getCounter() {
		return counter;
	}

	/*
	 * Incrementing counter
	 */
	private static void incrementCounter() {
		counter++;
	}

	/*
	 * Decrementing counter
	 */
	private void decrementCounter() {
		counter--;
	}

	/*
	 * inserts the specified element at the specified position in this list
	 */
	public void add(Object data, int index) {
		Node myTemp = new Node(data);
		Node myCurrent = head;
		if (myCurrent != null) {
			for (int i = 0; i < index && myCurrent.getNext() != null; i++) {
				myCurrent = myCurrent.getNext();
			}
		}
		myTemp.setNext(myCurrent.getNext());
		myCurrent.setNext(myTemp);
		incrementCounter();
	}

	/*
	 * returns the element at the specified position in this list.
	 */
	public Object get(int index) {
		if (index < 0)
			return null;
		Node myCurrent = null;
		if (head != null) {
			myCurrent = head.getNext();
			for (int i = 0; i < index; i++) {
				if (myCurrent.getNext() == null)
					return null;

				myCurrent = myCurrent.getNext();
			}
			return myCurrent.getData();
		}
		return myCurrent;

	}

	/*
	 * removes the element at the specified position in this list.
	 */
	public boolean remove(int index) {

		// if the index is out of range, exit
		if (index < 1 || index > size())
			return false;

		Node myCurrent = head;
		if (head != null) {
			for (int i = 0; i < index; i++) {
				if (myCurrent.getNext() == null)
					return false;

				myCurrent = myCurrent.getNext();
			}
			myCurrent.setNext(myCurrent.getNext().getNext());

			decrementCounter();
			return true;
		}
		return false;
	}

	/*
	 * removes last element from LinkedList
	 */
	public boolean removeTail() {

		if (head.next == null) {
			head = null;
			decrementCounter();
		} else {
			Node nextToEnd = head;
			Node end = head.next;
			while (end.next != null) {
				nextToEnd = end;
				end = end.next;
			}
			nextToEnd.next = null;
			decrementCounter();
		}
		return true;
	}

	/*
	 * Remove all elements from the list
	 */
	public boolean removeAll() {
		head = null;
		counter = 0;
		return true;
	}

	/*
	 * Remove all elements from LinkedList which are greater than target value
	 */
	public boolean removeAll(int target) {

		// System.out.println("main head "+head.data);
		if (head == null)

			if (Integer.parseInt(head.data.toString()) > target && head.next == null)
				head = null;

		Node cur = head;
		Node prev = null;
		Node temp = null;

		while (cur != null && Integer.parseInt(cur.data.toString()) > target) {
			prev = cur;
			cur = cur.next;
			decrementCounter();
		}

		if (prev != null)
			prev.next = null;

		Node newHead = cur;

		while (cur.next != null) {
			if (Integer.parseInt(cur.next.data.toString()) > target) {
				cur.next = cur.next.next;
				decrementCounter();
			} else {
				cur = cur.next;
			}
		}

		head = newHead;

		return true;
	}

	/*
	 * returns the number of elements in this list.
	 */
	public int size() {
		return getCounter();
	}

	public String toString() {
		String output = "";

		if (head != null) {
			Node myCurrent = head;
			while (myCurrent != null) {

				output += "[" + myCurrent.data.toString() + "]";
				myCurrent = myCurrent.getNext();
			}

		}
		return output;
	}

	private class Node {

		Node next;

		Object data;

		/*
		 * Node constructor
		 */
		public Node(Object dataValue) {
			next = null;
			data = dataValue;
		}

		/*
		 * another Node constructor if we want to specify the node to point to.
		 */

		@SuppressWarnings("unused")
		public Node(Object dataValue, Node nextValue) {
			next = nextValue;
			data = dataValue;
		}

		/*
		 * Returns current data object of node
		 */
		public Object getData() {
			return data;
		}

		@SuppressWarnings("unused")
		public void setData(Object dataValue) {
			data = dataValue;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node nextValue) {
			next = nextValue;
		}

	}
}