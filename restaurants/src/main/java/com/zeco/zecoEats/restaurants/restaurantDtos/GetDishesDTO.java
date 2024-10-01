package com.zeco.restaurants.restaurantDtos;

import com.zeco.restaurants.model.Menus;
import com.zeco.restaurants.model.Restaurant;
import com.zeco.restaurants.model.Spices;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class GetDishesDTO {

    Menus menu;
    Restaurant restaurant;
    String cookingTime;
    String description;
    BigDecimal price;
    Boolean discount;
    Long discountPrice;
    Long likes;
    String imageUrl;
    List<Spices> spicesList = new ArrayList<>();
}