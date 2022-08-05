package edu.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import edu.poly.model.User;

public class UserDao extends AbstractEntityDao<User> {

	public UserDao() {
		super(User.class);
		
	}



}
