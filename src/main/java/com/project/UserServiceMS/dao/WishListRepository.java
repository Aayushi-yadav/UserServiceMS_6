package com.project.UserServiceMS.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.UserServiceMS.entity.WishListId;
import com.project.UserServiceMS.entity.WishlistEntity;

public interface WishListRepository extends CrudRepository<WishlistEntity, WishListId>{
	public List<WishlistEntity> findByIdBuyerId(int buyerId);
	
	public WishlistEntity findByIdBuyerIdAndIdProdId(int buyerId,int ProdId);
}
