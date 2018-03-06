package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AAA database table.
 * 
 */
@Entity
@Table(name="AAA")
public class Aaa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AAA_ID")
	private Integer aaaId;

	@Column(name="AAA_VALUE")
	private String aaaValue;

	//bi-directional many-to-one association to Aa
	@ManyToOne
	@JoinColumn(name="AA_ID")
	private Aa aa;

	//bi-directional one-to-one association to Aaaz
	@OneToOne(mappedBy="aaa")
	private Aaaz aaaz;

	public Aaa() {
	}

	public Integer getAaaId() {
		return this.aaaId;
	}

	public void setAaaId(Integer aaaId) {
		this.aaaId = aaaId;
	}

	public String getAaaValue() {
		return this.aaaValue;
	}

	public void setAaaValue(String aaaValue) {
		this.aaaValue = aaaValue;
	}

	public Aa getAa() {
		return this.aa;
	}

	public void setAa(Aa aa) {
		this.aa = aa;
	}

	public Aaaz getAaaz() {
		return this.aaaz;
	}

	public void setAaaz(Aaaz aaaz) {
		this.aaaz = aaaz;
	}

}