package ph.edu.mcl.mmdc;

import java.util.HashMap;

public class Stock {
    private Index[] indexes = new Index[0];
    private HashMap<String, Item> storage = new HashMap<>();

    public void add(Item item) {
        // Initialize a temporary indexes array with a size of the current indexes array + 1
        Index[] tempindexes = new Index[indexes.length + 1];

        // Copy the contents of the current indexes array to the temporary indexes array
        // for (int i = 0; i < indexes.length; i++) {
        //     tempindexes[i] = indexes[i];
        // }
        System.arraycopy(indexes, 0, tempindexes, 0, indexes.length);

        // Create an index
        Index index = new Index(item.getBrand(), item.getBrand());
        storage.put(item.getBrand(), item);

        // Add the new index to the temporary indexes array
        tempindexes[indexes.length] = index;

        // Set the current indexes array to the temporary indexes array
        indexes = tempindexes;
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= indexes.length) {
            throw new IndexOutOfBoundsException("Index.java " + index + " is out of bounds");
        }

        // Initialize a temporary indexes array with a size of the current indexes array - 1
        Index[] tempindexes = new Index[indexes.length - 1];

        // Copy the contents of the current indexes array to the temporary indexes array
        for (int i = 0, k = 0; i < indexes.length; i++) {
            if (i != index) {
                tempindexes[k] = indexes[i];
                k++;
            } else if (i == index) {
                storage.remove(indexes[i].getBrand());
            }
        }

        // Set the current indexes array to the temporary indexes array
        indexes = tempindexes;
    }

    private void merge(int left, int middle, int right) {
        // Find the sizes of the two temporary indexes arrays to be merged
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        // Create temporary indexes arrays
        Index[] leftArray = new Index[leftSize];
        Index[] rightArray = new Index[rightSize];

        // Copy the contents of the current indexes array to the two temporary indexes arrays
        System.arraycopy(indexes, left, leftArray, 0, leftSize);
        System.arraycopy(indexes, middle + 1, rightArray, 0, rightSize);

        // Initial indexes of the two temporary indexes arrays
        int leftIndex = 0;
        int rightIndex = 0;

        // Initial index of the merged indexes array
        int indexesIndex = left;
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftArray[leftIndex].getBrand().compareTo(rightArray[rightIndex].getBrand()) <= 0) {
                indexes[indexesIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                indexes[indexesIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            indexesIndex++;
        }

        // Copy remaining elements of leftArray if any
        while (leftIndex < leftSize) {
            indexes[indexesIndex] = leftArray[leftIndex];
            leftIndex++;
            indexesIndex++;
        }

        // Copy remaining elements of rightArray if any
        while (rightIndex < rightSize) {
            indexes[indexesIndex] = rightArray[rightIndex];
            rightIndex++;
            indexesIndex++;
        }
    }

    public void sort(int left, int right) {
        if (left < right) {
            // Divide the array into two halves
            int middle = (left + right) / 2;

            // Sort the first half
            sort(left, middle);

            // Sort the second half
            sort(middle + 1, right);

            // Merge the sorted halves
            merge(left, middle, right);
        }
    }

    public int search(int left, int right, String brand) {
        if (right >= left) {
            int middle = left + (right - left) / 2;
            if (indexes[middle].getBrand().equals(brand)) {
                return middle;
            }
            if (indexes[middle].getBrand().compareTo(brand) > 0) {
                return search(left, middle - 1, brand);
            }
            return search(middle + 1, right, brand);
        }
        return -1;
    }

    public HashMap<String, Item> getStorage() {
        return storage;
    }
    public Index[] getIndexes() {
        return indexes;
    }
}
