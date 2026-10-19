import java.util.Arrays;

/**
 * Small collection of classic algorithms with simple self-checks.
 * Compile: javac Algorithms.java
 * Run:     java Algorithms
 */
public class Algorithms {

    /** Returns true if the text reads the same forwards and backwards (letters and digits only). */
    public static boolean isPalindrome(String text) {
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9]", "");
        int left = 0;
        int right = cleaned.length() - 1;
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /** Returns the n-th Fibonacci number (0, 1, 1, 2, 3, 5...), iteratively. */
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        long previous = 0;
        long current = 1;
        for (int i = 0; i < n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return previous;
    }

    /** Binary search in a sorted array. Returns the index, or -1 if not found. */
    public static int binarySearch(int[] sorted, int target) {
        int low = 0;
        int high = sorted.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (sorted[middle] == target) {
                return middle;
            } else if (sorted[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    /** Bubble sort: returns a sorted copy and leaves the original array unchanged. */
    public static int[] bubbleSort(int[] values) {
        int[] result = Arrays.copyOf(values, values.length);
        boolean swapped = true;
        for (int pass = 0; pass < result.length - 1 && swapped; pass++) {
            swapped = false;
            for (int i = 0; i < result.length - 1 - pass; i++) {
                if (result[i] > result[i + 1]) {
                    int temp = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = temp;
                    swapped = true;
                }
            }
        }
        return result;
    }

    // ----- tiny test runner (no external library needed) -----
    private static int failures = 0;

    private static void check(String name, boolean condition) {
        System.out.println((condition ? "PASS " : "FAIL ") + name);
        if (!condition) {
            failures++;
        }
    }

    public static void main(String[] args) {
        check("palindrome: Radar", isPalindrome("Radar"));
        check("palindrome: A man, a plan, a canal: Panama", isPalindrome("A man, a plan, a canal: Panama"));
        check("not palindrome: hello", !isPalindrome("hello"));

        check("fibonacci(0) = 0", fibonacci(0) == 0);
        check("fibonacci(1) = 1", fibonacci(1) == 1);
        check("fibonacci(10) = 55", fibonacci(10) == 55);
        check("fibonacci(50) = 12586269025", fibonacci(50) == 12586269025L);

        int[] sorted = {1, 3, 5, 7, 9, 11};
        check("binarySearch finds 7 at index 3", binarySearch(sorted, 7) == 3);
        check("binarySearch returns -1 when missing", binarySearch(sorted, 4) == -1);
        check("binarySearch on empty array", binarySearch(new int[0], 1) == -1);

        int[] unsorted = {5, 2, 9, 1, 5, 6};
        check("bubbleSort result", Arrays.equals(bubbleSort(unsorted), new int[]{1, 2, 5, 5, 6, 9}));
        check("bubbleSort keeps original unchanged", Arrays.equals(unsorted, new int[]{5, 2, 9, 1, 5, 6}));

        System.out.println(failures == 0 ? "All checks passed." : failures + " check(s) failed.");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
