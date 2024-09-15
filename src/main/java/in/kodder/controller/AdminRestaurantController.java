package in.kodder.controller;

import in.kodder.entity.Restaurant;
import in.kodder.entity.User;
import in.kodder.request.CreateRestaurantRequest;
import in.kodder.response.MessageResponse;
import in.kodder.service.RestaurantService;
import in.kodder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    private User getUserFromJwt(String jwt) throws Exception {
        return userService.findUserByJwtToken(jwt);
    }

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = getUserFromJwt(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(req, user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody @Valid CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = getUserFromJwt(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id, req);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = getUserFromJwt(jwt);
        restaurantService.deleteRestaurant(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Restaurant deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        User user = getUserFromJwt(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = getUserFromJwt(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}

//@RestController
//@RequestMapping("/api/admin/restaurants")
//public class AdminRestaurantController {
//
//    @Autowired
//    private RestaurantService restaurantService;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping()
//    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
//
//        User user = userService.findUserByJwtToken(jwt);
//        Restaurant restaurant = restaurantService.createRestaurant(req, user);
//
//        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
//
//        User user = userService.findUserByJwtToken(jwt);
//        Restaurant restaurant = restaurantService.updateRestaurant(id, req);
//
//        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
//
//        User user = userService.findUserByJwtToken(jwt);
//        restaurantService.deleteRestaurant(id);
//
//        MessageResponse res = new MessageResponse();
//        res.setMessage("Restaurant deleted successfully");
//
//        return new ResponseEntity<>(res, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}/status")
//    public ResponseEntity<Restaurant> updateRestaurantStatus(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
//
//        User user = userService.findUserByJwtToken(jwt);
//        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
//
//        return new ResponseEntity<>(restaurant, HttpStatus.GONE);
//    }
//
//    @GetMapping("/user")
//    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestBody CreateRestaurantRequest req,
//                                                             @RequestHeader("Authorization") String jwt) throws Exception {
//
//        User user = userService.findUserByJwtToken(jwt);
//        Restaurant restaurant = restaurantService.findRestaurantById(user.getId());
//
//        return new ResponseEntity<>(restaurant, HttpStatus.GONE);
//    }
//}
