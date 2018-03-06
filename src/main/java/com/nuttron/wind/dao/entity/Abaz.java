package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ABAZ database table.
 * 
 */
@Entity
@Table(name="ABAZ")
public class Abaz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ABA_ID")
	private Integer abaId;

	@Column(name="ABAZ_VALUE")
	private String abazValue;

	//bi-directional one-to-one association to Aba
	@OneToOne
	@JoinColumn(name="ABA_ID")
	private Aba aba;

	public Abaz() {
	}

	public Integer getAbaId() {
		return this.abaId;
	}

	public void setAbaId(Integer abaId) {
		this.abaId = abaId;
	}

	public String getAbazValue() {
		return this.abazValue;
	}

	public void setAbazValue(String abazValue) {
		this.abazValue = abazValue;
	}

	public Aba getAba() {
		return this.aba;
	}

	public void setAba(Aba aba) {
		this.aba = aba;
	}

}