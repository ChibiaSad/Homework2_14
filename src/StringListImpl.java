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
        if(item == null) throw new RuntimeException();
        if (size == storage.length) {
            storage = Arrays.copyOf(storage, size + 1);
        }
        storage[size] = item;
        size++;
        return storage[size - 1];
    }

    @Override
    public String add(int index, String item) {
        if(index < 0 || index >= size|| item == null){
            throw new RuntimeException();
        }
        if (size == storage.length) {
            storage = Arrays.copyOf(storage, size + 1);
        }
        String[] buff = Arrays.copyOfRange(storage, index, storage.length);
        storage[index] = item;
        size++;
        for (int i = index + 1, j = 0; i < size; i++, j++) {
            storage[i] = buff[j];
        }

        return storage[index];
    }

    @Override
    public String set(int index, String item) {
        if(item == null) throw new RuntimeException();
        storage[index] = item;
        return storage[index];
    }

    @Override
    public String remove(String item) {
        String[] buff = null;
        String removed = null;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                buff = Arrays.copyOfRange(storage, i + 1, size);
                index = i;
                removed = storage[i];
                size--;
                break;
            }
        }
        if(index != -1){
            for (int i = index, j = 0; i < size; i++, j++) {
                storage[i] = buff[j];
            }
            storage[size] = null;
        }else{
            throw new RuntimeException();
        }
        return removed;
    }

    @Override
    public String remove(int index) {
        String[] subarray = Arrays.copyOfRange(storage, index + 1, size);
        String removed = storage[index];
        size--;
        for (int i = index, j = 0; i < size; i++, j++) {
            storage[i] = subarray[j];
        }
        storage[size] = null;
        return removed;
    }

    @Override
    public boolean contains(String item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i], item)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new RuntimeException();
        } else {
            return index;
        }
    }

    @Override
    public int lastIndexOf(String item) {
        int index = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(storage[i], item)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new RuntimeException();
        } else {
            return index;
        }
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
        if (this == otherList) return true;
        if (otherList == null || getClass() != otherList.getClass()) return false;
        return size == otherList.size() && Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return storage[0] == null;
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
        return Arrays.copyOfRange(storage, 0, size);
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
