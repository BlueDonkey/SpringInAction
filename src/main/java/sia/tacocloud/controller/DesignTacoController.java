package sia.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static sia.tacocloud.Ingredient.Type;

import sia.tacocloud.Ingredient;
import sia.tacocloud.domain.Taco;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    @GetMapping
    public String showDesignFrom(Model model) {
        List<Ingredient> list = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            var l = filterListByType(list, type);
            model.addAttribute(type.toString().toLowerCase(), l);
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(Taco taco) {
        log.info("Process data: " + taco);
//        System.out.println("Process data: " + taco);
        return "redirect:/order/current";
    }

    private List<Ingredient> filterListByType(List<Ingredient> list, Ingredient.Type type) {
        return list.stream().filter(i -> i.getType().equals(type)).collect(Collectors.toList());
    }
}
