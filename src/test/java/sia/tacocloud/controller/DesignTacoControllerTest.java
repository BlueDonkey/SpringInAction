package sia.tacocloud.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sia.tacocloud.HomeController;
import sia.tacocloud.domain.Taco;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void processDesign() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/design")
                .accept(MediaType.TEXT_HTML)
                .param("name", "1"))
                .andExpect(MockMvcResultMatchers.view().name("design"));

    }
}
