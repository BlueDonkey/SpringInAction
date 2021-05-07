package sia.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.domain.Order;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/current")
    public String order(Model model) {
        model.addAttribute("order", new Order());

        return "orderForm";
    }
}
