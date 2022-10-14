import java.util.Arrays;
import java.util.Objects;

public class StringListImpl implements StringList {
    private String[] storage;
    private int size;

    public StringListImpl(int length) {
        this.storage = new String[length];
        this.size = 0;
    }

    @Override
    public String add(String item) {
        if (item == null) throw new RuntimeException();
        if (size == storage.length) {
            storage = Arrays.copyOf(storage, size + 1);
        }
        storage[size++] = item;
        return storage[size - 1];
    }

    @Override
    public String add(int index, String item) {
        if (index < 0 || index > size || item == null) {
            throw new RuntimeException();
        }

        if (size == storage.length) {
            storage = Arrays.copyOf(storage, size + 1);
        }

        if(index == size){
            storage[size++] = item;
            return storage[size - 1];
        }

        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return storage[index];
    }

    @Override
    public String set(int index, String item) {
        if (item == null) throw new RuntimeException();
        if (index >= 0 && index < size) {
            storage[index] = item;
            return storage[index];
        }
        throw new RuntimeException();
    }

    @Override
    public String remove(String item) {
        if (item == null) throw new RuntimeException();
        int index = indexOf(item);
        if (index == -1) {
            throw new RuntimeException();
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        String removed = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        size--;
        return removed;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) throw new RuntimeException();
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i], item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) throw new RuntimeException();
        int index = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(storage[i], item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public String get(int index) {
        if (index >= 0 && index < size) {
            return storage[index];
        }
        throw new RuntimeException();
    }

    @Override
    public boolean equals(StringList otherList) {
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
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";

        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(storage[i]);
            if (i == size - 1) {
                stringBuilder.append("]");
            } else {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
