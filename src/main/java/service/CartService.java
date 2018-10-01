package service;

import datasource.UserMapper;
import domain.Cart;
import domain.Product;
import domain.User;

import java.util.HashMap;
import java.util.Map;

public class CartService {
    private static CartService instance;
    static {
        instance = new CartService();
    }
    public static CartService getInstance() {
        return instance;
    }

    private CartService(){}

    public int addToCart(int productId, int userId, int saleCount){
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setUserId(userId);
        cart.setSaleCount(saleCount);
        User user = new UserMapper().findById(userId);
        user.addCart(cart);
        return cart.getId();
    }

    public int getCartCount(int userId){
        User user = new UserMapper().findById(userId);
        return user.getCartItems().size();
    }

    public void deleteCartByUser(int userId){
        User user = new UserMapper().findById(userId);
        user.deleteAllCart();
    }

    public void deleteCartById(int userId, int cartId){
        User user = new UserMapper().findById(userId);
        Cart cart = new Cart();
        cart.setId(cartId);
        user.deleteCart(cart);
    }

    public void updateCartCount(int userId, int cartId, int saleCount){
        User user = new UserMapper().findById(userId);
        user.updateCartCount(cartId, saleCount);
    }

    public Map<Cart, Product> GetAllCartInfoByUserID(int userId){
        User user = new UserMapper().findById(userId);
        Map<Cart, Product> map = new HashMap<Cart, Product>();
        if (!user.getCartItems().isEmpty()){
            for (Cart cart: user.getCartItems()){
                map.put(cart, cart.getProduct());
            }
        }
        return map;
    }
}
