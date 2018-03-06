package com.nuttron.wind.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * The persistent class for the Z database table.
 * 
 */
@Entity
public class Z implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "A_ID")
	private Integer aId;

	@Column(name = "Z_VALUE")
	private String zValue;

	// bi-directional one-to-one association to A
	@OneToOne
	@JoinColumn(name = "A_ID")
	private A a;

	public Z() {
	}

	public Integer getAId() {
		return this.aId;
	}

	public void setAId(Integer aId) {
		this.aId = aId;
	}

	public String getZValue() {
		return this.zValue;
	}

	public void setZValue(String zValue) {
		this.zValue = zValue;
	}

	public A getA() {
		return this.a;
	}

	public void setA(A a) {
		this.a = a;
	}

}