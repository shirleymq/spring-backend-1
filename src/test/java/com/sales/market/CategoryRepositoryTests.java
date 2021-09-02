package com.sales.market;

import com.sales.market.model.Category;
import com.sales.market.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryRepositoryTests {
@Autowired
private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
        Category category = new Category();
        category.setName("AB");
        category.setCode("AB");
        categoryRepository.save(category);
        System.out.println("categoryElements: "+categoryRepository.count());
    }

}
