package DAO;

import Model.product;
import Model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class productDAO {
    private JdbcTemplate operations;
    @Autowired
    public void setOperations(JdbcTemplate operations) {
        this.operations = operations;
    }
    public List<product> getAllProducts(){
        return operations.query("SELECT * FROM products", (resultSet, i) -> {
            product product=new product();
            product.setProduct_id(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            return product;
        });
    }
    public void addNewProduct(product product){
         operations.update("INSERT INTO products(name)VALUES(?)",product.getName());
    }
}
