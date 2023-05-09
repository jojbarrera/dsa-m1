package ph.edu.mcl.mmdc;

import java.util.Date;

public class Index {
    private final String key;
    private final String brand;

    public Index(String key, String brand) {
        this.key = key;
        this.brand = brand;
    }

    public String getKey() {
        return key;
    }

    public String getBrand() {
        return brand;
    }
}
