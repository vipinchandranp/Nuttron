package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ABC database table.
 * 
 */
@Entity
@Table(name="ABC")
public class Abc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ABC_ID")
	private Integer abcId;

	@Column(name="ABC_VALUE")
	private String abcValue;

	//bi-directional many-to-one association to Ab
	@ManyToOne
	@JoinColumn(name="AB_ID")
	private Ab ab;

	//bi-directional one-to-one association to Abcz
	@OneToOne(mappedBy="abc")
	private Abcz abcz;

	public Abc() {
	}

	public Integer getAbcId() {
		return this.abcId;
	}

	public void setAbcId(Integer abcId) {
		this.abcId = abcId;
	}

	public String getAbcValue() {
		return this.abcValue;
	}

	public void setAbcValue(String abcValue) {
		this.abcValue = abcValue;
	}

	public Ab getAb() {
		return this.ab;
	}

	public void setAb(Ab ab) {
		this.ab = ab;
	}

	public Abcz getAbcz() {
		return this.abcz;
	}

	public void setAbcz(Abcz abcz) {
		this.abcz = abcz;
	}

}