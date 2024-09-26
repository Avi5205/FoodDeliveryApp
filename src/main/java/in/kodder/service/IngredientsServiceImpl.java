package in.kodder.service;

import in.kodder.entity.IngredientsCategory;
import in.kodder.entity.IngredientsItem;
import in.kodder.entity.Restaurant;
import in.kodder.repository.IngredientCategoryRepository;
import in.kodder.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientsCategory createIngredientsCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory ingredientsCategory = new IngredientsCategory();
        ingredientsCategory.setRestaurant(restaurant);
        ingredientsCategory.setName(name);

        return ingredientCategoryRepository.save(ingredientsCategory);
    }

    @Override
    public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> ingredientsCategory = ingredientCategoryRepository.findById(id);
        if (ingredientsCategory.isEmpty()) {
            throw new Exception(String.format("Ingredient category with id %s not found", id));
        }
        return ingredientsCategory.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String name, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory ingredientsCategory = findIngredientsCategoryById(categoryId);
        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setRestaurant(restaurant);
        ingredientsItem.setName(name);
        ingredientsItem.setCategory(ingredientsCategory);
        IngredientsItem ingredientsItemSaved = ingredientItemRepository.save(ingredientsItem);
        ingredientsCategory.getIngredients().add(ingredientsItemSaved);

        return ingredientsItemSaved;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> ingredientsItem = ingredientItemRepository.findById(id);
        if (ingredientsItem.isEmpty()) {
            throw new Exception(String.format("Ingredient item with id %s not found", id));
        }
        IngredientsItem ingredientsItems = ingredientsItem.get();
        ingredientsItems.setInStock(!ingredientsItems.isInStock());
        return ingredientItemRepository.save(ingredientsItems);
    }
}
