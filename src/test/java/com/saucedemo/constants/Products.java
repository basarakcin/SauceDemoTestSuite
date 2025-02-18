package com.saucedemo.constants;

public class Products {
    public static final class Product {
        private final String id;
        private final String name;
        private final String description;
        private final String price;
        private final String imageUrl;

        public Product(String id, String name, String description, String price, String imageUrl) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.imageUrl = imageUrl;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getPrice() { return price; }
        public String getImageUrl() { return imageUrl; }
    }

    public static final Product SAUCE_LABS_BACKPACK = new Product(
        "4",
        "Sauce Labs Backpack",
        "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
        "$29.99",
        "/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg"
    );

    public static final Product SAUCE_LABS_BIKE_LIGHT = new Product(
        "0",
        "Sauce Labs Bike Light",
        "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
        "$9.99",
        "/static/media/bike-light-1200x1500.37c843b0.jpg"
    );

    public static final Product SAUCE_LABS_BOLT_TSHIRT = new Product(
        "1",
        "Sauce Labs Bolt T-Shirt",
        "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
        "$15.99",
        "/static/media/bolt-shirt-1200x1500.c2599ac5.jpg"
    );

    public static final Product SAUCE_LABS_FLEECE_JACKET = new Product(
        "5",
        "Sauce Labs Fleece Jacket",
        "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
        "$49.99",
        "/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg"
    );

    public static final Product SAUCE_LABS_ONESIE = new Product(
        "2",
        "Sauce Labs Onesie",
        "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
        "$7.99",
        "/static/media/red-onesie-1200x1500.2ec615b2.jpg"
    );

    public static final Product TEST_ALL_THE_THINGS_TSHIRT = new Product(
        "3",
        "Test.allTheThings() T-Shirt (Red)",
        "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.",
        "$15.99",
        "/static/media/red-tatt-1200x1500.30dadef4.jpg"
    );

    public static final Product[] ALL_PRODUCTS = {
        SAUCE_LABS_BACKPACK,
        SAUCE_LABS_BIKE_LIGHT,
        SAUCE_LABS_BOLT_TSHIRT,
        SAUCE_LABS_FLEECE_JACKET,
        SAUCE_LABS_ONESIE,
        TEST_ALL_THE_THINGS_TSHIRT
    };
} 