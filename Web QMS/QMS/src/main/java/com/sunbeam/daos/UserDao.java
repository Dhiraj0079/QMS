package com.sunbeam.daos;
import java.util.List;
import com.sunbeam.pojos.*;

public interface UserDao extends AutoCloseable  {
	User findByEmail(String email) throws Exception;
	User findById(int id) throws Exception;
	List<User>findAll() throws Exception;
	int save(User u) throws Exception;
	int update(User u)throws Exception;
	int deleteBy(int userId)throws Exception;
	
	

}

