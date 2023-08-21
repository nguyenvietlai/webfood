package www.fptshopfood.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="comments")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;



	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedate;




	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "accountid", referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	private Account account;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "productid", referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	private Product product;

}