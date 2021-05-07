package sia.tacocloud.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import sia.tacocloud.Ingredient;

import java.util.List;

@Data
//@RequiredArgsConstructor
public class Taco {
    private String name;
    private List<String> ingredients;
}
