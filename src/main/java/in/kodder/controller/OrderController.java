package in.kodder.controller;

import in.kodder.entity.Order;
import in.kodder.entity.User;
import in.kodder.request.OrderRequest;
import in.kodder.service.CartService;
import in.kodder.service.OrderService;
import in.kodder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest req, @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Order order = orderService.createOrder(req, user);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        List<Order> orders = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
