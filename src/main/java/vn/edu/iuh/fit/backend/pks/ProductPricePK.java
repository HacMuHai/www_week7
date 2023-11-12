package vn.edu.iuh.fit.backend.pks;

import lombok.Getter;
import lombok.Setter;
import vn.edu.iuh.fit.backend.models.Product;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Setter @Getter
public class ProductPricePK implements Serializable {
    private long product;
    private LocalDateTime price_date_time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPricePK that = (ProductPricePK) o;
        return product == that.product && Objects.equals(price_date_time, that.price_date_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, price_date_time);
    }
}
