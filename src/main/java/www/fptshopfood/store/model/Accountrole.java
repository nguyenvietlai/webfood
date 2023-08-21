package www.fptshopfood.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="accountroles")
public class Accountrole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "accountid", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    Account account;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "roleid", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    Role role;
}
