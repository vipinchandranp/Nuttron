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
 * The persistent class for the ACCZ database table.
 * 
 */
@Entity
@Table(name = "ACCZ")
public class Accz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACC_ID")
	private Integer accId;

	@Column(name = "ACCZ_VALUE")
	private String acczValue;

	// bi-directional one-to-one association to Acc
	@OneToOne
	@JoinColumn(name = "ACC_ID")
	private Acc acc;

	public Accz() {
	}

	public Integer getAccId() {
		return this.accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getAcczValue() {
		return this.acczValue;
	}

	public void setAcczValue(String acczValue) {
		this.acczValue = acczValue;
	}

	public Acc getAcc() {
		return this.acc;
	}

	public void setAcc(Acc acc) {
		this.acc = acc;
	}

}