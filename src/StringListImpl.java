
import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] storage;

    private int size;

    public StringListImpl() {
        this.storage = new String[1];
    }

    public StringListImpl(int size) {
        this.storage = new String[size];
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String add(String item) {
        validateItem(item);
        validateSize();
        this.storage[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            add(item);
            return item;
        }
        System.arraycopy(storage, index, storage, index +1 , size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        return storage[index] = item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        validateIndex(index);
        System.arraycopy(storage, index +1, storage, index, size - index);
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = get(index);
        System.arraycopy(storage, index +1, storage, index, size - index);
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            String s = storage[i];
            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        validateItem(item);
        for (int i = size - 1; i >= 0; i--) {
            String s = storage[i];
            if (s.equals(item)) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("otherList is null");
        }
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    public void validateItem(String item) {
        if (item == null) {
            throw new ItemIsNullException("Item should not be null");
        }
    }

    public void validateIndex(int index) {
        if (index < 0 || index == size) {
            throw new InvalidIndexException("Index is out of range");
        }
    }

    public void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException("Array is full");
        }
    }
}
