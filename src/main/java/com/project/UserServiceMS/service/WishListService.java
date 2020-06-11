package com.project.UserServiceMS.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.UserServiceMS.controller.BuyerController;
import com.project.UserServiceMS.controller.CartController;
import com.project.UserServiceMS.dao.CartRepository;
import com.project.UserServiceMS.dao.WishListRepository;
import com.project.UserServiceMS.entity.WishListId;
import com.project.UserServiceMS.entity.WishlistEntity;
import com.project.UserServiceMS.exception.NotPrivilegeBuyerException;
import com.project.UserServiceMS.exception.StockNotAvailableException;
import com.project.UserServiceMS.exception.UserException;
import com.project.UserServiceMS.exception.WishListEmptyException;
import com.project.UserServiceMS.exception.WishlistAlreadyExist;
import com.project.UserServiceMS.exception.WishlistNotAvailableException;
import com.project.UserServiceMS.model.Cart;
import com.project.UserServiceMS.model.Product;
import com.project.UserServiceMS.model.Wishlist;

@Service
public class WishListService {

	@Value("${productAPIURL}")
	public String productAPIURI;

	@Autowired
	WishListRepository wishListRepository;

	@Autowired
	CartController cartController;

	@Autowired
	BuyerController buyerController;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CartRepository cartRepository;

	public void addToWishList(Wishlist wishlist) throws UserException {
		WishlistEntity wishlistEntity = wishListRepository.findByIdBuyerIdAndIdProdId(wishlist.getBuyerId(),
				wishlist.getProdId());
		if (wishlistEntity == null) {
			WishlistEntity newwishlistEntity = new WishlistEntity(
					new WishListId(wishlist.getBuyerId(), wishlist.getProdId()));
			wishListRepository.save(newwishlistEntity);
		} else {
			throw new WishlistAlreadyExist("wishlist.ALREADY_EXISTS");

		}

	}

	public void removeFromWishList(Wishlist wishlist) throws UserException {
		WishlistEntity wishlistEntity = wishListRepository.findByIdBuyerIdAndIdProdId(wishlist.getBuyerId(),
				wishlist.getProdId());
		if (wishlistEntity != null) {
			wishListRepository.deleteById(new WishListId(wishlist.getBuyerId(), wishlist.getProdId()));
		} else {
			throw new WishlistNotAvailableException("wishlist.NOT_AVAILABLE");
		}
	}

	public List<Wishlist> getAllWishList(int buyerId) throws UserException {
		List<WishlistEntity> allWishlistEntity = wishListRepository.findByIdBuyerId(buyerId);
		if (allWishlistEntity != null) {
			List<Wishlist> allWIshlistmodal = new ArrayList<Wishlist>();
			for (WishlistEntity wishlistEntity : allWishlistEntity) {
				Wishlist wishlist = new Wishlist(wishlistEntity.getId().getBuyerId(),
						wishlistEntity.getId().getProdId());
				allWIshlistmodal.add(wishlist);
			}
			return allWIshlistmodal;
		} else {
			throw new WishListEmptyException("wishlist.EMPTY");
		}

	}

}
