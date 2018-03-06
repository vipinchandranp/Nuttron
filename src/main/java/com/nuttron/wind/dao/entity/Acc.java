package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ACC database table.
 * 
 */
@Entity
@Table(name="ACC")
public class Acc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACC_ID")
	private Integer accId;

	@Column(name="ACC_VALUE")
	private String accValue;

	//bi-directional many-to-one association to Ac
	@ManyToOne
	@JoinColumn(name="AC_ID")
	private Ac ac;

	//bi-directional one-to-one association to Accz
	@OneToOne(mappedBy="acc")
	private Accz accz;

	public Acc() {
	}

	public Integer getAccId() {
		return this.accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getAccValue() {
		return this.accValue;
	}

	public void setAccValue(String accValue) {
		this.accValue = accValue;
	}

	public Ac getAc() {
		return this.ac;
	}

	public void setAc(Ac ac) {
		this.ac = ac;
	}

	public Accz getAccz() {
		return this.accz;
	}

	public void setAccz(Accz accz) {
		this.accz = accz;
	}

}