package com.project.UserServiceMS.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.UserServiceMS.entity.SellerEntity;


public interface SellerRepository extends CrudRepository<SellerEntity,Integer>{
		public SellerEntity findBySellerId(int Id);

		public SellerEntity findByEmail(String email);

		public SellerEntity findByPhoneNumber(String phoneNumber);
		
}
