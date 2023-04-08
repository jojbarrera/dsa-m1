package ph.edu.mcl.mmdc;

public class Stock {
    private Item[] storage = new Item[0];

    public void add(Item item) {
        // Initialize a temporary storage array with a size of the current storage array + 1
        Item[] tempStorage = new Item[storage.length + 1];

        // Copy the contents of the current storage array to the temporary storage array
        // for (int i = 0; i < storage.length; i++) {
        //     tempStorage[i] = storage[i];
        // }
        System.arraycopy(storage, 0, tempStorage, 0, storage.length);


        // Add the new item to the temporary storage array
        tempStorage[storage.length] = item;

        // Set the current storage array to the temporary storage array
        storage = tempStorage;
    }

    public void remove(int index) {
        if (index < 0 || index >= storage.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        // Initialize a temporary storage array with a size of the current storage array - 1
        Item[] tempStorage = new Item[storage.length - 1];

        // Copy the contents of the current storage array to the temporary storage array
        for (int i = 0, k = 0; i < storage.length; i++) {
            if (i != index) {
                tempStorage[k] = storage[i];
                k++;
            }
        }

        // Set the current storage array to the temporary storage array
        storage = tempStorage;
    }

    private void merge(int left, int middle, int right) {
        // Find the sizes of the two temporary storage arrays to be merged
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        // Create temporary storage arrays
        Item[] leftArray = new Item[leftSize];
        Item[] rightArray = new Item[rightSize];

        // Copy the contents of the current storage array to the two temporary storage arrays
        System.arraycopy(storage, left, leftArray, 0, leftSize);
        System.arraycopy(storage, middle + 1, rightArray, 0, rightSize);

        // Initial indexes of the two temporary storage arrays
        int leftIndex = 0;
        int rightIndex = 0;

        // Initial index of the merged storage array
        int storageIndex = left;
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftArray[leftIndex].getBrand().compareTo(rightArray[rightIndex].getBrand()) <= 0) {
                storage[storageIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                storage[storageIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            storageIndex++;
        }

        // Copy remaining elements of leftArray if any
        while (leftIndex < leftSize) {
            storage[storageIndex] = leftArray[leftIndex];
            leftIndex++;
            storageIndex++;
        }

        // Copy remaining elements of rightArray if any
        while (rightIndex < rightSize) {
            storage[storageIndex] = rightArray[rightIndex];
            rightIndex++;
            storageIndex++;
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
            if (storage[middle].getBrand().equals(brand)) {
                return middle;
            }
            if (storage[middle].getBrand().compareTo(brand) > 0) {
                return search(left, middle - 1, brand);
            }
            return search(middle + 1, right, brand);
        }
        return -1;
    }

    public Item[] getStorage() {
        return storage;
    }
}
