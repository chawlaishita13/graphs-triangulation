

public class LinkedL <T> {

	

		static int c = 0;
		private Node<T> head;
		private Node<T> tail;
		private int size;

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size() == 0;
		}

		public T getFirst() throws Exception {
			if (isEmpty())
				throw new Exception("LL empty");
			return head.data;
		}

		public T getLast() throws Exception {
			if (isEmpty())
				throw new Exception("LL empty");
			return tail.data;
		}

		public T getAt(int k) {
//			if (k > size() || k < 0) {
//				throw new Exception("Out of range");
//			}

			Node<T> temp = new Node<T>();
			temp = head;
			for (int i = 1; i <= k; i++) {
				temp = temp.next;
			}
			return temp.data;

		}

		public Node<T> getNodeAt(int k) {
			Node<T> temp = new Node<T>();
			temp = head;
			for (int i = 1; i <= k; i++) {
				temp = temp.next;
			}
			return temp;

		}

		public void display() {
			Node<T> temp = new Node<T>();
			temp = head;
			while (temp != null) {
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
		}

		public void addLast(T item) {
			Node<T> temp = new Node<T>();
			temp.data = item;

			if (head == null && tail == null) { // ll empty
				head = temp;
				tail = temp;
				size++;
			} else {
				tail.next = temp;
				tail = temp;
				size++;
			}
		}

		public void addFirst(T item) {
			Node<T> temp = new Node<T>();
			temp.data = item;
			temp.next = head;
			if (isEmpty()) {
				head = temp;
				tail = temp;
				size++;
			} else {
				head = temp;
				size++;
			}
		}

	

		public T removeFirst() {
			T d = null;
			
			if (size == 1) {
				d = head.data;
				head = null;
				tail = null;
				size = 0;
			} else {
				d = head.data;
				head = head.next;
				size--;

			}
			return d;
		}

		

}
