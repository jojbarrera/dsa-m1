package ph.edu.mcl.mmdc;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Stock stock = new Stock();

        stock.add(new Item(new Date(), "Old", "Kawasaki", "123456789", "On-hand"));
        stock.add(new Item(new Date(), "Old", "Honda", "123456789", "On-hand"));
        stock.add(new Item(new Date(), "Old", "Suzuki", "123456789", "On-hand"));
        stock.add(new Item(new Date(), "Old", "Yamaha", "123456789", "On-hand"));
        stock.add(new Item(new Date(), "Old", "Kymco", "123456789", "On-hand"));

        System.out.println("=================Brands=================");

        for (Item item : stock.getStorage().values()) {
            System.out.println(item.getBrand());
        }

        System.out.println("==============Remove Items==============");

        try {
            stock.remove(2);
            System.out.println("Removed Item: Suzuki");
            stock.remove(10); // This should throw an IndexOutOfBoundsException
        } catch (IndexOutOfBoundsException e) {
            // Suppress the exception or handle it here
            // System.out.println(e.getMessage());
        }

        System.out.println("============Brands Available============");

        for (Item item : stock.getStorage().values()) {
            System.out.println(item.getBrand());
        }

        System.out.println("=======Brands Available (Sorted)========");

        stock.sort(0, stock.getStorage().size() - 1);
        for (Item item : stock.getStorage().values()) {
            System.out.println(item.getBrand());
        }

        System.out.println("========================================");

        int itemIndex = stock.search(0, stock.getStorage().size() - 1, "Kymco");
        if (itemIndex != -1) {
            System.out.println("Found Item: " + stock.getIndexes()[itemIndex].getBrand());
        } else {
            System.out.println("Item not found");
        }
    }
}