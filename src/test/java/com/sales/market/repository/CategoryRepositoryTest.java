/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;

import com.sales.market.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testPersistence() {
        Category categoryTest = new Category();
        String expectedCode = "test";
        categoryTest.setCode(expectedCode);
        categoryTest.setCode("test");
        Category categoryTestPersisted = categoryRepository.save(categoryTest);
        Category observedCategory = categoryRepository.getById(categoryTestPersisted.getId());
        assertEquals(expectedCode, observedCategory.getCode());
    }

}
