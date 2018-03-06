package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ABB database table.
 * 
 */
@Entity
@Table(name="ABB")
public class Abb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ABB_ID")
	private Integer abbId;

	@Column(name="ABB_VALUE")
	private String abbValue;

	//bi-directional many-to-one association to Ab
	@ManyToOne
	@JoinColumn(name="AB_ID")
	private Ab ab;

	//bi-directional one-to-one association to Abbz
	@OneToOne(mappedBy="abb")
	private Abbz abbz;

	public Abb() {
	}

	public Integer getAbbId() {
		return this.abbId;
	}

	public void setAbbId(Integer abbId) {
		this.abbId = abbId;
	}

	public String getAbbValue() {
		return this.abbValue;
	}

	public void setAbbValue(String abbValue) {
		this.abbValue = abbValue;
	}

	public Ab getAb() {
		return this.ab;
	}

	public void setAb(Ab ab) {
		this.ab = ab;
	}

	public Abbz getAbbz() {
		return this.abbz;
	}

	public void setAbbz(Abbz abbz) {
		this.abbz = abbz;
	}

}