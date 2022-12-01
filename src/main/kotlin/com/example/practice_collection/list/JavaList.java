package com.example.practice_collection.list;

public interface JavaList<E> {
    int size();
    void add(E e);
    E get(int index);
    boolean contains(E e);
    void remove(E e);
    void addAll(JavaList<E> list);
    E[] toArray();
}
