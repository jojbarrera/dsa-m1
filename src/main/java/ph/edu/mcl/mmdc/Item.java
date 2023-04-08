package ph.edu.mcl.mmdc;

import java.util.Date;

public class Item {
    private final Date dateEntered;
    private final String status;
    private final String brand;
    private final String engineNumber;
    private final String purchaseStatus;

    public Item(Date dateEntered, String status, String brand, String engineNumber, String purchaseStatus) {
        this.dateEntered = dateEntered;
        this.status = purchaseStatus;
        this.brand = brand;
        this.engineNumber = engineNumber;
        this.purchaseStatus = purchaseStatus;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public String getStatus() {
        return status;
    }

    public String getBrand() {
        return brand;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }
}
