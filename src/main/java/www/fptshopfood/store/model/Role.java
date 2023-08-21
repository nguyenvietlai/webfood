package www.fptshopfood.store.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="roles")
public class Role {
    @Id
    private Integer id;

    private String name;
}
