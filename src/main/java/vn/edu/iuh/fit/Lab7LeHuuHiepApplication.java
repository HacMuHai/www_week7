package vn.edu.iuh.fit;

import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.logging.SimpleFormatter;

@SpringBootApplication
public class Lab7LeHuuHiepApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab7LeHuuHiepApplication.class, args);
    }

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

//    @Bean
    CommandLineRunner insertData(){
        return args -> {
            Faker faker = new Faker();
            Device device = faker.device();
            Random random = new Random();
            for (int i = 1; i <= 30; i++) {
                Product product = new Product(device.modelName(), faker.lorem().paragraph(20),faker.starCraft().unit(),device.manufacturer(), ProductStatus.values()[random.nextInt(0,3)]);
                productRepository.save(product);

                ProductPrice productPrice=new ProductPrice();
                for (int j = 0; j < 3; j++) {
                     productPrice = new ProductPrice(product, LocalDateTime.of(2022,4,j+1,4,5,0,0),
                            faker.random().nextDouble(20,50),"");
                    productPriceRepository.save(productPrice);

                    ProductImage productImage = new ProductImage("","",product);
                    productImageRepository.save(productImage);
                }



                Employee employee = new Employee(faker.name().fullName(),faker.date().birthday().toLocalDateTime().toLocalDate(),
                        faker.internet().emailAddress(), faker.phoneNumber().cellPhone(), faker.address().fullAddress(), EmployeeStatus.ACTIVE);
                employeeRepository.save(employee);

                Customer customer =  new Customer(faker.name().fullName(), faker.internet().emailAddress(),
                        faker.phoneNumber().cellPhone(), faker.address().fullAddress());
                customerRepository.save(customer);

                Order order = new Order(faker.date().birthday().toLocalDateTime(),employee,customer);
                orderRepository.save(order);
//                faker.date().between(
//                        LocalDate.of(2022,1,1),
//                        LocalDate.of(2022,12,12),
//                        "yyyy-MM-dd");


              OrderDetail orderDetail = new OrderDetail(faker.random().nextInt(1,20), productPrice.getPrice(), "",order,product);
                orderDetailRepository.save(orderDetail);
            }
        };
    }

}
