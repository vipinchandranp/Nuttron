package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ABA database table.
 * 
 */
@Entity
@Table(name="ABA")
public class Aba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ABA_ID")
	private Integer abaId;

	@Column(name="ABA_VALUE")
	private String abaValue;

	//bi-directional many-to-one association to Ab
	@ManyToOne
	@JoinColumn(name="AB_ID")
	private Ab ab;

	//bi-directional one-to-one association to Abaz
	@OneToOne(mappedBy="aba")
	private Abaz abaz;

	public Aba() {
	}

	public Integer getAbaId() {
		return this.abaId;
	}

	public void setAbaId(Integer abaId) {
		this.abaId = abaId;
	}

	public String getAbaValue() {
		return this.abaValue;
	}

	public void setAbaValue(String abaValue) {
		this.abaValue = abaValue;
	}

	public Ab getAb() {
		return this.ab;
	}

	public void setAb(Ab ab) {
		this.ab = ab;
	}

	public Abaz getAbaz() {
		return this.abaz;
	}

	public void setAbaz(Abaz abaz) {
		this.abaz = abaz;
	}

}