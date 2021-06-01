package sia.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.Ingredient;
import sia.tacocloud.data.impl.TacoRepository;
import sia.tacocloud.domain.Order;
import sia.tacocloud.domain.Taco;
import sia.tacocloud.data.impl.IngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sia.tacocloud.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
//
@SessionAttributes("order")
public class DesignTacoController {
    private TacoRepository designRepo;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository designRepo) {
        this.ingredientRepository = ingredientRepository;
        this.designRepo = designRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignFrom(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Type.values();
        for (var type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterListByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    // @ModelAttribute indicates that this value should come from the model and that Spring MVC shouldn’t attempt to bind
    // request parameters to it
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saved = designRepo.save(design);
        order.addDesign(saved);
        log.info("Process data: " + design);
//        System.out.println("Process data: " + taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterListByType(List<Ingredient> list, Ingredient.Type type) {
        return list.stream().filter(i -> i.getType().equals(type)).collect(Collectors.toList());
    }
}
