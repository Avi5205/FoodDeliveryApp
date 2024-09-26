package in.kodder.service;

import in.kodder.entity.Cart;
import in.kodder.entity.CartItem;
import in.kodder.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculatecartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long cartId) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;
}
