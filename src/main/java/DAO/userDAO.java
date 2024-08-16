package DAO;
import Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
@Component
public class userDAO {
    private JdbcTemplate operations;
    @Autowired
    public void setOperations(JdbcTemplate operations) {
        this.operations = operations;
    }
    public List<user> getAllUsers(){
        return operations.query("SELECT * FROM users", (resultSet, i) -> {
            user user=new user();
            user.setId(resultSet.getInt("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setBirthdate(resultSet.getString("user_birthdate"));
            return user;
        });
    }
    public user getConcret(int id){
        return operations.queryForObject("SELECT * FROM users WHERE user_id = ?", new Object[]{id}, (resultSet, i) -> {
            user user=new user();
            user.setId(resultSet.getInt("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setBirthdate(resultSet.getString("user_birthdate"));
            return user;
        });
    }
    public List<UserWithOrder> getUsersWithOrders() {
        String sql = "select * from users inner join purchases on users.user_id=purchases.user_id";
        return operations.query(sql, new RowMapper<UserWithOrder>() {
            @Override
            public UserWithOrder mapRow(ResultSet resultSet, int i) throws SQLException {
                UserWithOrder userWithOrder=new UserWithOrder();
                userWithOrder.setId(resultSet.getInt("user_id"));
                userWithOrder.setName(resultSet.getString("user_name"));
                userWithOrder.setPurchase_name(resultSet.getString("purchase_name"));
                userWithOrder.setBirthdate(resultSet.getString("user_birthdate"));
                return userWithOrder;
            }
        });
    }
    public int addNewUser(user user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        String sql = "INSERT INTO users (user_name, user_birthdate) VALUES (?, ?) RETURNING user_id";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = operations.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getBirthdate());
            return ps;
        }, keyHolder);
        if (rowsAffected == 0) {
            throw new IllegalStateException("No rows affected, user not added.");
        }
        Number generatedKey = keyHolder.getKey();
        if (generatedKey == null) {
            throw new IllegalStateException("Expected single generated key, but found none.");
        }
        return generatedKey.intValue();
    }
    public void editInDB(int id,user user){
        operations.update("UPDATE users SET user_name=?,user_birthdate=? WHERE user_id=?", user.getName(),user.getBirthdate(),id);
    }
    public void deleteInDB(int id) {
        operations.update("DELETE FROM users WHERE user_id=?", id);
    }
}
