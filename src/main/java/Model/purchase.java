package Model;

public class purchase {
    private int purchase_id;
    private int user_id;
    private String name;

    public purchase() {
    }

    public purchase(int purchase_id, int user_id, String name) {
        this.purchase_id = purchase_id;
        this.user_id = user_id;
        this.name = name;
    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
