import java.util.*;

public class LList<T> {
	private LLNode<T> head;
	private LLNode<T> tail;
	private int numItems;
	
	private class LLNode <T>{
		private LLNode<T> next;
		private T obj;
		
		private LLNode(T obj) {
			this.obj = obj;
			next = null;
		}
		
		public String toString() {
			return this.obj.toString() + next.obj.toString();
		}
		
	}

	public LList() {
		head = null;
		tail = null;
		numItems = 0;
	}
	
	public boolean isEmpty() {
		return numItems == 0;
	}
	
	public boolean add(T obj) {
		LLNode<T> tempNode = new LLNode<T>(obj);
		if(isEmpty()) {
			head = tempNode;
			tail = tempNode;
			numItems++;
			return true;
		}
		if(numItems == 1) {
			tail = tempNode;
			head.next = tail;
			numItems++;
			return true;
		}
		tail.next = tempNode;
		tail = tempNode;
		numItems++;
		return true;
	}
	
	public boolean add(T obj, int index) {
		if(index < 0 || index > numItems + 1)
			return false;
		LLNode<T> tempNode = new LLNode<T>(obj);
		if(isEmpty()) {
			head = tempNode;
			tail = tempNode;
			numItems++;
			return true;
		}
		if(numItems == 1) {
			tail = tempNode;
			head.next = tail;
			numItems++;
			return true;
		}
		LLNode<T> trav = head;
		for(int i = 0; i < index - 1; i++) {
			trav = trav.next;
		}
		tempNode.next = trav.next;
		trav.next = tempNode;
		numItems++;
		return true;
	}
	
	public T remove() {
		T ret = head.obj;
		head = head.next;
		numItems--;
		return ret;
	}
	
	public T peek() {
		return head.obj;
	}
	
	public void printList() {
		if(isEmpty())
			return;
		String str = "{";
		LLNode<T> trav = head;
		while(trav != null) {
			str += trav.obj.toString() + ", ";
			trav = trav.next;
		}
		str = str.substring(0,str.length()-2);
		str += "}";
		System.out.println(str);
	}
	
	public Iterator<T> iterator(){
		Iterator<T> iter = new LListIterator<T>();
		return iter;
	}
	
	private class LListIterator<T> implements Iterator{
		private LLNode<T> curr;
		
		private LListIterator() {
			curr = (LLNode<T>) head;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return curr.next != null;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			T temp = curr.next.obj;
			curr = curr.next;
			return temp;
		}
		
	}
	
}
