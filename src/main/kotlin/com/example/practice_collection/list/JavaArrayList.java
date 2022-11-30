package com.example.practice_collection.list;

public class JavaArrayList<E> implements JavaList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DIVISION = 2;
    public static final int NO_INDEX = -1;
    public static final int ZERO = 0;
    private Object[] emptyList = {};
    private Object[] data;
    private int size;

    public JavaArrayList() {
        data = emptyList;
        size = ZERO;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        if (data.length == size) {
            growCapacity(size / DIVISION);
        }
        data[size] = e;
        size += 1;
    }

    private void growCapacity(int capacity) {
        if (size == 0) {
            data = new Object[Math.max(capacity, DEFAULT_CAPACITY)];
            return;
        }
        int newCapacity = Math.max(getIncreasedCapacity(data.length), capacity);
        Object tmp[] = new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            tmp[i] = data[i];
        }

        data = tmp;
    }

    private int getIncreasedCapacity(int capacity) {
        return capacity + capacity / DIVISION;
    }

    @Override
    public E get(int index) {
        validateRange(index);

        return (E) data[index];
    }

    private void validateRange(int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException("[ERROR] 범위 밖임");
        }
    }

    @Override
    public boolean contains(E e) {
        return indexOfElements(e) > NO_INDEX;
    }

    private int indexOfElements(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == null) {
                if (e == null) {
                    return i;
                }
                continue;
            }

            if (data[i].equals(e)) {
                return i;
            }
        }

        return NO_INDEX;
    }

    @Override
    public void remove(E e) {
        int index = indexOfElements(e);

        if (index == NO_INDEX) {
            return;
        }

        removeByIndex(index);
    }

    private void removeByIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size -= 1;
    }

    @Override
    public void addAll(JavaList<E> list) {
        if (list.size() == ZERO) {
            return;
        }

        int newSize = size + list.size();
        growCapacity(newSize);

        for (int i = size; i < newSize; i++) {
            data[i] = list.get(i - size);
        }

        this.size = newSize;
    }
}
