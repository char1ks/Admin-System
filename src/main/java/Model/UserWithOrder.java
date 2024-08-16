package Model;

public class UserWithOrder {
    private int id;
    private String name;
    private String birthdate;
    private String purchase_name;

    public UserWithOrder() {}

    public UserWithOrder(int id, String name, String birthdate, String purchase_name) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.purchase_name = purchase_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPurchase_name() {
        return purchase_name;
    }

    public void setPurchase_name(String purchase_name) {
        this.purchase_name = purchase_name;
    }
}
