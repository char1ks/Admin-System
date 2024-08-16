package Model;

import javax.validation.constraints.NotEmpty;

public class product {
    private int product_id;
    @NotEmpty(message = "Product name is should not be empty!")
    private String name;

    public product() {
    }

    public product(int product_id, String name) {
        this.product_id = product_id;
        this.name = name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
