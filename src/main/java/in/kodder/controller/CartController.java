package in.kodder.controller;

import in.kodder.entity.Cart;
import in.kodder.entity.CartItem;
import in.kodder.entity.User;
import in.kodder.request.AddCartItemRequest;
import in.kodder.request.UpdateCartItemRequest;
import in.kodder.service.CartService;
import in.kodder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req, @RequestHeader("Authorization") String token) throws Exception {
        CartItem cartItem = cartService.addItemToCart(req, token);
        return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req, @RequestHeader("Authorization") String token) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id, @RequestHeader("Authorization") String token) throws Exception {
        Cart cart = cartService.removeItemFromCart(id, token);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Cart cart = cartService.clearCart(user.getId());
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Cart cart = cartService.findCartByUserId(user.getId());
        return ResponseEntity.ok(cart);
    }
}
