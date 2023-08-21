package www.fptshopfood.store.model;


import lombok.Data;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.*;


@Data
@Entity
@Table(name="accounts")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;

	private String email;
	private String fullname;

	private String passwords;

	private String photo;

	private String username;

	}