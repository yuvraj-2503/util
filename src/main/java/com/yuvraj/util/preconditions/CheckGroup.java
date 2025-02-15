package com.yuvraj.util.preconditions;

import java.util.List;

/**
 * @author Yuvraj
 */
public class CheckGroup {
    private CheckGroup() {
    }

    public static <T> boolean nullOrEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    public static <T> boolean nullOrEmpty(List<T> arr) {
        return arr == null || arr.isEmpty();
    }
}
