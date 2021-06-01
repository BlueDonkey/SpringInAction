package sia.tacocloud.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import sia.tacocloud.data.impl.OrderRepository;
import sia.tacocloud.domain.Order;
import sia.tacocloud.domain.Taco;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("Taco_Order").usingGeneratedKeyColumns(
                "id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("Taco_Order_Tacos");
        this.objectMapper = new ObjectMapper();

    }

    @Override
    public void save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetail(order);
        List<Taco> tacos = order.getTaco();
        for (Taco taco : tacos) {
            saveTacoToOder(taco, orderId);
        }
    }

    private long saveOrderDetail(Order order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        // the objectMapper will convert the date to a long value, we manuel set this value to prevent it.
        values.put("placeAt", order.getPlacedAt());

        long id = orderInserter.executeAndReturnKey(values).longValue();

        return id;
    }

    private void saveTacoToOder(Taco taco, long orderId) {
        Map<String, Object> map = new HashMap<>();
        map.put("tacoOrder", orderId);
        map.put("taco", taco.getId());
        orderTacoInserter.execute(map);
    }
}
