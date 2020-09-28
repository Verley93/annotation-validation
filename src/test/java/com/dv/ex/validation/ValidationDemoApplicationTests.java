package com.dv.ex.validation;

import com.dv.ex.validation.model.FridgeRequestModel;
import com.dv.ex.validation.model.PantryRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DisplayName("Integration tests")
class ValidationDemoApplicationTests {

    static final String URI_FRIDGE = "/api/v1/fridge/food";
    static final String URI_PANTRY = "/api/v1/pantry/food";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    class FridgeServiceTests {

        @Nested
        class AddFoodToFridgeV1 {

            @Test
            void testSuccess() throws Exception {
                FridgeRequestModel fridgeRequest = new FridgeRequestModel();
                fridgeRequest.setName("chicken");
                fridgeRequest.setCategory("meats");
                fridgeRequest.setQuantity(3);
                fridgeRequest.setRefrigerated(true);

                mockMvc.perform(MockMvcRequestBuilders
                        .post(URI_FRIDGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fridgeRequest)))
                        .andExpect(status().isOk());
            }

            @Test
            void testNegativeQuantity() throws Exception {
                FridgeRequestModel fridgeRequest = new FridgeRequestModel();
                fridgeRequest.setName("chicken");
                fridgeRequest.setCategory("meats");
                fridgeRequest.setQuantity(-3);
                fridgeRequest.setRefrigerated(true);

                mockMvc.perform(MockMvcRequestBuilders
                        .post(URI_FRIDGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fridgeRequest)))
                        .andExpect(jsonPath("$.errorMessage")
                                .value("Quantity must be positive"))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void testHighQuantity() throws Exception {
                FridgeRequestModel fridgeRequest = new FridgeRequestModel();
                fridgeRequest.setName("chicken");
                fridgeRequest.setCategory("meats");
                fridgeRequest.setQuantity(50);
                fridgeRequest.setRefrigerated(true);

                mockMvc.perform(MockMvcRequestBuilders
                        .post(URI_FRIDGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fridgeRequest)))
                        .andExpect(jsonPath("$.errorMessage")
                                .value("Quantity must not exceed 25"))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void testInvalidCategory() throws Exception {
                FridgeRequestModel fridgeRequest = new FridgeRequestModel();
                fridgeRequest.setName("pretzels");
                fridgeRequest.setCategory("snacks");
                fridgeRequest.setQuantity(20);
                fridgeRequest.setRefrigerated(false);

                mockMvc.perform(MockMvcRequestBuilders
                        .post(URI_FRIDGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fridgeRequest)))
                        .andExpect(jsonPath(
                                "$.errorMessage")
                                .value("Category must be one of the following: [meats, vegetables, beer]"))
                        .andExpect(status().isBadRequest());
            }

        }

    }

    @Nested
    class PantryServiceTests {

        @Nested
        class AddFoodToPantryV1 {

            @Test
            void testSuccess() throws Exception {
                PantryRequestModel pantryRequest = new PantryRequestModel();
                pantryRequest.setName("oatmeal");
                pantryRequest.setCategory("grains");
                pantryRequest.setQuantity(3);
                pantryRequest.setRefrigerated(false);

                mockMvc.perform(MockMvcRequestBuilders
                        .post(URI_PANTRY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pantryRequest)))
                        .andExpect(status().isOk());
            }

            @Test
            void testNegativeQuantity() throws Exception {
                PantryRequestModel pantryRequest = new PantryRequestModel();
                pantryRequest.setName("oatmeal");
                pantryRequest.setCategory("grains");
                pantryRequest.setQuantity(-3);
                pantryRequest.setRefrigerated(false);

                mockMvc.perform(MockMvcRequestBuilders
                        .post(URI_PANTRY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pantryRequest)))
                        .andExpect(jsonPath("$.errorMessage")
                                .value("Quantity must be positive"))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void testHighQuantity() throws Exception {
                PantryRequestModel pantryRequest = new PantryRequestModel();
                pantryRequest.setName("oatmeal");
                pantryRequest.setCategory("grains");
                pantryRequest.setQuantity(50);
                pantryRequest.setRefrigerated(false);

                mockMvc.perform(MockMvcRequestBuilders
                        .post(URI_PANTRY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pantryRequest)))
                        .andExpect(jsonPath("$.errorMessage")
                                .value("Quantity must not exceed 25"))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void testInvalidCategory() throws Exception {
                PantryRequestModel pantryRequest = new PantryRequestModel();
                pantryRequest.setName("chicken");
                pantryRequest.setCategory("meats");
                pantryRequest.setQuantity(20);
                pantryRequest.setRefrigerated(true);

                mockMvc.perform(MockMvcRequestBuilders
                        .post(URI_PANTRY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pantryRequest)))
                        .andExpect(jsonPath("$.errorMessage")
                                .value("Category must be one of the following: [grains, canned, snacks]"))
                        .andExpect(status().isBadRequest());
            }

        }

    }

}
