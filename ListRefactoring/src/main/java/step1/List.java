package step1;

public class List {
	private static final int INITIAL_STORE_SIZE = 10;
	private static final int STORE_SIZE_INCREAMENT = 10;

	private Object[] elementStore = new Object[INITIAL_STORE_SIZE];
	private boolean readOnly;
	private int size = 0;

	public void add(Object element) {
		if(readOnly){
			return;
		}

		if (isElementStoreFull()) {
			increaseElementStore();
		}

		addElementAtEnd(element);
	}

	private void addElementAtEnd(Object element) {
		elementStore[size] = element;
		size++;
	}

	private void increaseElementStore() {
		Object[] newElements = new Object[elementStore.length + STORE_SIZE_INCREAMENT];
		for (int i = 0; i < size; i++) {
            newElements[i] = elementStore[i];
        }

		elementStore = newElements;
	}

	private boolean isElementStoreFull() {
		return size + 1 > elementStore.length;
	}

	public int size() {
		return size;
	}

	public Object get(int index) {
		return elementStore[index];
	}
}