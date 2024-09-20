package in.kodder.controller;

import in.kodder.entity.Food;
import in.kodder.entity.User;
import in.kodder.service.FoodService;
import in.kodder.service.RestaurantService;
import in.kodder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    private ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                                  @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        List<Food> food = foodService.searchFood(name);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    private ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegeterian,
                                                         @RequestParam boolean nonvegeterian,
                                                         @RequestParam boolean seasonal,
                                                         @RequestParam(required = false) String foodCategory,
                                                         @PathVariable Long restaurantId,
                                                         @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        List<Food> food = foodService.getRestaurantFoods(restaurantId, vegeterian, nonvegeterian, seasonal, foodCategory);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
