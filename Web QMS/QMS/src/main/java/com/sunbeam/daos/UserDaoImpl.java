package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojos.User;

public  class UserDaoImpl extends Dao implements UserDao {
	private PreparedStatement stmtFindByEmail;
	private PreparedStatement stmtFindById;
	private PreparedStatement stmtFindAll;
	private PreparedStatement stmtSave;
	private PreparedStatement stmtUpdate;
	private PreparedStatement stmtDeleteById;
	public UserDaoImpl() throws Exception {
		super();
		stmtFindByEmail = con.prepareStatement("SELECT * FROM users WHERE email=?");
		stmtFindById = con.prepareStatement("SELECT * FROM users WHERE user_id=?");
		stmtFindAll = con.prepareStatement("SELECT * FROM users");
		stmtSave = con.prepareStatement("INSERT INTO users( name, email, password_hash,role) VALUES( ?, ?, ?, ?)");
		stmtUpdate = con.prepareStatement("UPDATE users SET  name=?, email=?, password_hash=?,  role=? WHERE user_id=?");
		stmtDeleteById = con.prepareStatement("DELETE FROM users WHERE user_id=?");
	}
	@Override
	public void close() throws Exception {
		stmtDeleteById.close();
		stmtUpdate.close();
		stmtSave.close();
		stmtFindAll.close();
		stmtFindById.close();
		stmtFindByEmail.close();
		super.close();
	}	
	@Override
	public User findByEmail(String email) throws Exception {
		stmtFindByEmail.setString(1, email);
		try(ResultSet rs = stmtFindByEmail.executeQuery()) {
			if(rs.next()) {
				int id = rs.getInt("user_id");
				String name = rs.getString("name");
				email = rs.getString("email");
				String password = rs.getString("password_hash");
				String role = rs.getString("role");
				User user = new User(id, name, email, password,role);
				return user;
			}
		} // rs.close();
		return null;
	}
	@Override
	public User findById(int id) throws Exception {
		stmtFindById.setInt(1, id);
		try(ResultSet rs = stmtFindById.executeQuery()) {
			if(rs.next()) {
				id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password_hash = rs.getString("password");
				String role = rs.getString("role");
				User user = new User(id, name,  email, password_hash, role);
				return user;
			}
		} // rs.close();
		return null;
	}
	@Override
	public List<User> findAll() throws Exception {
		List<User> list = new ArrayList<>();
		try(ResultSet rs = stmtFindAll.executeQuery()) {
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String role = rs.getString("role");
				User user = new User(id, name, email, password,  role);
				list.add(user);
			}
		} // rs.close();
		return list;
	}
	@Override
	public int save(User u) throws Exception {
		stmtSave.setString(1, u.getName());
		stmtSave.setString(2, u.getEmail());
		stmtSave.setString(3, u.getPassword());
		stmtSave.setString(4, u.getRole());
		int cnt = stmtSave.executeUpdate();
		return cnt;
	}
	@Override
	public int update(User u) throws Exception {
		stmtUpdate.setString(1, u.getName());
		stmtUpdate.setString(2, u.getEmail());
		stmtUpdate.setString(3, u.getPassword());
		stmtUpdate.setString(4, u.getRole());
		stmtUpdate.setInt(5, u.getId());
		int cnt = stmtUpdate.executeUpdate();
		return cnt;
	}
	@Override
	public int deleteBy(int id) throws Exception {
		stmtDeleteById.setInt(1, id);
		int cnt = stmtDeleteById.executeUpdate();
		return cnt;
	}
}

