package com.project.UserServiceMS.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.UserServiceMS.entity.CartEntity;
import com.project.UserServiceMS.entity.CartId;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, CartId>{
	
}