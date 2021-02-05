/*
 *    Copyright 2016-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.kazuki43zoo.jpetstore.api;

import com.kazuki43zoo.jpetstore.domain.Category;
import com.kazuki43zoo.jpetstore.domain.Item;
import com.kazuki43zoo.jpetstore.domain.Product;
import com.kazuki43zoo.jpetstore.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kazuki Shimizu
 */
@RequestMapping("/api/catalog")
@RestController
@RequiredArgsConstructor
public class CatalogApiController {

    private final CatalogService catalogService;

    @GetMapping("/categories/{categoryId}")
    public Object viewCategory(@PathVariable String categoryId) {
        Category category = catalogService.getCategory(categoryId);
        List<Product> productList = catalogService.getProductListByCategory(categoryId);
        Map<String, Object> map = new HashMap<>();
        map.put("category", category);
        map.put("productList", productList);
        return map;
    }

    @GetMapping("/products/{productId}")
    public Map<String, Object> viewProduct(@PathVariable String productId) {
        Product product = catalogService.getProduct(productId);
        List<Item> itemList = catalogService.getItemListByProduct(productId);
        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        map.put("itemList", itemList);
        return map;
    }

}
