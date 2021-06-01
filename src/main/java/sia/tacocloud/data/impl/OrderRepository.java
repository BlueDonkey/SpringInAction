package sia.tacocloud.data.impl;

import sia.tacocloud.domain.Order;

public interface OrderRepository {
    public void save(Order order);
}
