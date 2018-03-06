package com.nuttron.wind.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the ACZ database table.
 * 
 */
@Entity
@Table(name = "ACZ")
public class Acz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AC_ID")
	private Integer acId;

	@Column(name = "ACZ_VALUE")
	private String aczValue;

	// bi-directional one-to-one association to Ac
	@OneToOne
	@JoinColumn(name = "AC_ID")
	private Ac ac;

	public Acz() {
	}

	public Integer getAcId() {
		return this.acId;
	}

	public void setAcId(Integer acId) {
		this.acId = acId;
	}

	public String getAczValue() {
		return this.aczValue;
	}

	public void setAczValue(String aczValue) {
		this.aczValue = aczValue;
	}

	public Ac getAc() {
		return this.ac;
	}

	public void setAc(Ac ac) {
		this.ac = ac;
	}

}