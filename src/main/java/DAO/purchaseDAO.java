package DAO;

import Model.purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class purchaseDAO {
    private JdbcTemplate operations;
    @Autowired
    public void setOperations(JdbcTemplate operations) {
        this.operations = operations;
    }
    public List<purchase> getAllPurchase(int id){
        return operations.query("SELECT * FROM purchases WHERE user_id = ?", new Object[]{id}, (resultSet, i) -> {
            purchase purchase=new purchase();
            purchase.setPurchase_id(resultSet.getInt("purchase_id"));
            purchase.setName(resultSet.getString("purchase_name"));
            purchase.setUser_id(resultSet.getInt("user_id"));
            return purchase;
        });
    }
    public void addPurchase(int personId,int productId){
        String sql = "SELECT name FROM products WHERE id = ?";
        String name = operations.queryForObject(sql, new Object[]{productId}, String.class);
        operations.update("INSERT INTO purchases(purchase_name,user_id) VALUES(?,?)", name,personId);
    }
}
