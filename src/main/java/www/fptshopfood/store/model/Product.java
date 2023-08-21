package www.fptshopfood.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="products")

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String picture;
	private Boolean available;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private String name;

	private String descript;

	private float price;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cateid", referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	private Category category;


}