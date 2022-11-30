package com.example.practice_collection;

import com.example.practice_collection.list.JavaArrayList;
import com.example.practice_collection.list.JavaList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaArrayListTest {

    @Test
    @DisplayName("JavaArrayList add 테스트")
    void testArrayList() {
        JavaList<String> list = new JavaArrayList<>();

        list.add("add 1");
        list.add("add 2");

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("JavaArrayList get 테스트")
    void testGet() {
        JavaList<String> list = new JavaArrayList<>();
        list.add("get sample");

        // success
        assertThat(list.get(0)).isEqualTo("get sample");

        // 범위에 없는 index 를 넣으면 Exception
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> list.get(1));
    }

    @Test
    @DisplayName("JavaArrayList contains 테스트")
    void testContains() {
        JavaList<String> list = new JavaArrayList<>();
        list.add("contains sample");

        // 있는 값을 넣으면 true
        assertThat(list.contains("contains sample")).isTrue();

        // 없는 값을 넣으면 false
        assertThat(list.contains("contains")).isFalse();

        // null 값이 없으면 false
        assertThat(list.contains(null)).isFalse();

        // null 값이 있으면 true
        list.add(null);
        assertThat(list.contains(null)).isTrue();
    }

    @Test
    @DisplayName("JavaArrayList remove 테스트")
    void testRemove() {
        JavaList<String> list = new JavaArrayList<>();

        // 없는 요소를 넣으면 Nothing
        list.remove("hello");

        list.add("remove 1");
        list.add("remove 2");
        list.add("remove 3");
        list.add("remove 4");
        list.add("remove 5");

        // 0 원소 제거
        list.remove("remove 1");

        // 마지막 원소 제거
        list.remove("remove 5");

        // 가운데 원소 제거
        list.remove("remove 3");

        // 최종 결과
        assertThat(list.get(0)).isEqualTo("remove 2");
        assertThat(list.get(1)).isEqualTo("remove 4");
    }

    @Test
    @DisplayName("JavaArrayList addAll 테스트")
    void testAddAll() {
        JavaList<String> list = new JavaArrayList<>();

        // 빈 리스트를 넣으면 아무것도 없음
        list.addAll(new JavaArrayList<>());
        assertThat(list.size()).isEqualTo(0);

        // 빈 리스트에 다른 리스트를 넣을 수 있음
        JavaList<String> otherList = new JavaArrayList<>();
        otherList.add("addAll 1");
        otherList.add("addAll 2");

        list.addAll(otherList);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo("addAll 1");
        assertThat(list.get(1)).isEqualTo("addAll 2");

        // 리스트가 존재할 때 다른 리스트를 넣을 수 있음
        list.addAll(otherList);
        assertThat(list.size()).isEqualTo(4);
        assertThat(list.get(0)).isEqualTo("addAll 1");
        assertThat(list.get(1)).isEqualTo("addAll 2");
        assertThat(list.get(2)).isEqualTo("addAll 1");
        assertThat(list.get(3)).isEqualTo("addAll 2");
    }
}
