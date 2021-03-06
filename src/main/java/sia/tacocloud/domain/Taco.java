package sia.tacocloud.domain;

import lombok.Data;
import sia.tacocloud.Ingredient;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private int id;
    private Date createAt;
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @Size(min = 1, message = "You must choose at lease 1 ingredient")
    private List<Ingredient> ingredients;
}
