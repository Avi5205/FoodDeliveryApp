package in.kodder.controller;

import in.kodder.dto.RestaurantDto;
import in.kodder.entity.Restaurant;
import in.kodder.entity.User;
import in.kodder.service.RestaurantService;
import in.kodder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    private User getUserFromJwt(String jwt) throws Exception {
        return userService.findUserByJwtToken(jwt);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws Exception {
        User user = getUserFromJwt(jwt);
        List<Restaurant> restaurant = restaurantService.searchRestaurants(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = getUserFromJwt(jwt);
        List<Restaurant> restaurant = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader("Authorization") String jwt, @PathVariable("id") Long id) throws Exception {
        User user = getUserFromJwt(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(@RequestHeader("Authorization") String jwt, @PathVariable("id") Long id) throws Exception {
        User user = getUserFromJwt(jwt);
        RestaurantDto restaurant = restaurantService.addFavourite(id, user);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
