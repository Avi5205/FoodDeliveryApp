package in.kodder.controller;

import in.kodder.entity.Food;
import in.kodder.entity.Restaurant;
import in.kodder.entity.User;
import in.kodder.request.CreateFoodRequest;
import in.kodder.response.MessageResponse;
import in.kodder.service.FoodService;
import in.kodder.service.RestaurantService;
import in.kodder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    private ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                            @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                       @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        foodService.deleteFood(id);
        MessageResponse message = new MessageResponse();
        message.setMessage("Successfully deleted food");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                              @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Food food = foodService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
