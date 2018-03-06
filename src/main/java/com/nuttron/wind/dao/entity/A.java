package com.nuttron.wind.dao.entity;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the A database table.
 * 
 */
@Entity
@Table(name = "A")
public class A implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "A_ID")
	private Integer aId;

	@Column(name = "A_VALUE")
	private String aValue;

	// bi-directional many-to-one association to Aa
	@OneToMany(mappedBy = "a")
	private Set<Aa> aas;

	// bi-directional many-to-one association to Ab
	@OneToMany(mappedBy = "a")
	private Set<Ab> abs;

	// bi-directional many-to-one association to Ac
	@OneToMany(mappedBy = "a")
	private Set<Ac> acs;

	// bi-directional one-to-one association to Z
	@OneToOne(mappedBy = "a")
	private Z z;

	public A() {
	}

	public Integer getAId() {
		return this.aId;
	}

	public void setAId(Integer aId) {
		this.aId = aId;
	}

	public String getAValue() {
		return this.aValue;
	}

	public void setAValue(String aValue) {
		this.aValue = aValue;
	}

	public Set<Aa> getAas() {
		return this.aas;
	}

	public void setAas(Set<Aa> aas) {
		this.aas = aas;
	}

	public Aa addAa(Aa aa) {
		getAas().add(aa);
		aa.setA(this);

		return aa;
	}

	public Aa removeAa(Aa aa) {
		getAas().remove(aa);
		aa.setA(null);

		return aa;
	}

	public Set<Ab> getAbs() {
		return this.abs;
	}

	public void setAbs(Set<Ab> abs) {
		this.abs = abs;
	}

	public Ab addAb(Ab ab) {
		getAbs().add(ab);
		ab.setA(this);

		return ab;
	}

	public Ab removeAb(Ab ab) {
		getAbs().remove(ab);
		ab.setA(null);

		return ab;
	}

	public Set<Ac> getAcs() {
		return this.acs;
	}

	public void setAcs(Set<Ac> acs) {
		this.acs = acs;
	}

	public Ac addAc(Ac ac) {
		getAcs().add(ac);
		ac.setA(this);

		return ac;
	}

	public Ac removeAc(Ac ac) {
		getAcs().remove(ac);
		ac.setA(null);

		return ac;
	}

	public Z getZ() {
		return this.z;
	}

	public void setZ(Z z) {
		this.z = z;
	}

}