package vn.edu.iuh.fit;

import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;

import java.util.Random;

@SpringBootApplication
public class Lab7LeHuuHiepApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab7LeHuuHiepApplication.class, args);
    }

    @Autowired
    private ProductRepository productRepository;

//    @Bean
    CommandLineRunner insertData(){
        return args -> {
            Faker faker = new Faker();
            Device device = faker.device();
            Random random = new Random();
            for (int i = 1; i <= 30; i++) {
                Product product = new Product(device.modelName(), faker.lorem().paragraph(20),faker.starCraft().unit(),device.manufacturer(), ProductStatus.values()[random.nextInt(0,3)]);
                productRepository.save(product);
            }
        };
    }

}
