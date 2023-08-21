package www.fptshopfood.store.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class revenueDTO {
    @Id
    private Serializable id;

    private Double price;

    private Long quantity;

}
