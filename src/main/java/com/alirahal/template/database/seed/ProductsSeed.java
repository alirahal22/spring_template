package com.alirahal.template.database.seed;

import com.alirahal.template.database.repository.BrandRepository;
import com.alirahal.template.database.repository.ProductRepository;
import com.alirahal.template.model.Brand;
import com.alirahal.template.model.Product;
import com.alirahal.template.utils.HasLogger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;


//@Component
public class ProductsSeed implements HasLogger {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        Brand lenovo = new Brand("Lenovo");
        Brand apple = new Brand("Apple");
        Brand samsung = new Brand("Samsung");
        Brand toshiba = new Brand("Toshiba");
        Brand dell = new Brand("Dell");
        Brand huawei = new Brand("Huawei");

        getLogger().info("Brands seed");

        brandRepository.saveAll(
                Arrays.asList(
                        lenovo,
                        apple,
                        samsung,
                        toshiba,
                        dell,
                        huawei
                )
        );


        Product iphone7 = new Product("iPhone 7", 199.99f, apple);
        Product iphone8 = new Product("iPhone 8", 299.99f, apple);
        Product iphoneX = new Product("iPhone X", 399.99f, apple);
        Product iphoneXs = new Product("iPhone Xs", 499.99f, apple);
        Product iphone11 = new Product("iPhone 11", 599.99f, apple);
        Product iphone12 = new Product("iPhone 12", 699.99f, apple);

        Product xps13_2015 = new Product("xps 13 2015", 499.99f, dell);
        Product xps15_2015 = new Product("xps 15 2015", 599.99f, dell);
        Product xps13_2017 = new Product("xps 13 2017", 699.99f, dell);
        Product xps15_2017 = new Product("xps 15 2017", 799.99f, dell);
        Product xps13_2019 = new Product("xps 13 2019", 899.99f, dell);
        Product xps15_2019 = new Product("xps 15 2019", 999.99f, dell);
        Product xps15_2019_2_in_1 = new Product("xps 15 2019 2 in 1", 699.99f, dell);

        getLogger().info("Products seed");
        productRepository.saveAll(
                Arrays.asList(
                        iphone7,
                        iphone8,
                        iphoneX,
                        iphoneXs,
                        iphone11,
                        iphone12,
                        xps13_2015,
                        xps15_2015,
                        xps13_2017,
                        xps15_2017,
                        xps13_2019,
                        xps15_2019,
                        xps15_2019_2_in_1
                )
        );

        getLogger().info("Products seeded");

    }
}

