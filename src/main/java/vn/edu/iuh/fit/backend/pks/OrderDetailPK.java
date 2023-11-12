package vn.edu.iuh.fit.backend.pks;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import vn.edu.iuh.fit.backend.models.Order;
import vn.edu.iuh.fit.backend.models.Product;

import java.io.Serializable;
import java.util.Objects;

@Setter @Getter
public class OrderDetailPK implements Serializable {
    private long order;
    private long product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailPK that = (OrderDetailPK) o;
        return order == that.order && product == that.product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
