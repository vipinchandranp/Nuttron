package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ABZ database table.
 * 
 */
@Entity
@Table(name="ABZ")
public class Abz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AB_ID")
	private Integer abId;

	@Column(name="ABZ_VALUE")
	private String abzValue;

	//bi-directional one-to-one association to Ab
	@OneToOne
	@JoinColumn(name="AB_ID")
	private Ab ab;

	public Abz() {
	}

	public Integer getAbId() {
		return this.abId;
	}

	public void setAbId(Integer abId) {
		this.abId = abId;
	}

	public String getAbzValue() {
		return this.abzValue;
	}

	public void setAbzValue(String abzValue) {
		this.abzValue = abzValue;
	}

	public Ab getAb() {
		return this.ab;
	}

	public void setAb(Ab ab) {
		this.ab = ab;
	}

}