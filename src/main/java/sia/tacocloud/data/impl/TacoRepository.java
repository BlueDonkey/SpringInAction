package sia.tacocloud.data.impl;

import sia.tacocloud.domain.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
