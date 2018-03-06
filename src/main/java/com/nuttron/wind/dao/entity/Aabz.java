package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AABZ database table.
 * 
 */
@Entity
@Table(name="AABZ")
public class Aabz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AAB_ID")
	private Integer aabId;

	@Column(name="AABZ_VALUE")
	private String aabzValue;

	//bi-directional one-to-one association to Aab
	@OneToOne
	@JoinColumn(name="AAB_ID")
	private Aab aab;

	public Aabz() {
	}

	public Integer getAabId() {
		return this.aabId;
	}

	public void setAabId(Integer aabId) {
		this.aabId = aabId;
	}

	public String getAabzValue() {
		return this.aabzValue;
	}

	public void setAabzValue(String aabzValue) {
		this.aabzValue = aabzValue;
	}

	public Aab getAab() {
		return this.aab;
	}

	public void setAab(Aab aab) {
		this.aab = aab;
	}

}