package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the AA database table.
 * 
 */
@Entity
@Table(name="AA")
public class Aa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AA_ID")
	private Integer aaId;

	@Column(name="AA_VALUE")
	private String aaValue;

	//bi-directional many-to-one association to A
	@ManyToOne
	@JoinColumn(name="A_ID")
	private A a;

	//bi-directional many-to-one association to Aaa
	@OneToMany(mappedBy="aa")
	private Set<Aaa> aaas;

	//bi-directional many-to-one association to Aab
	@OneToMany(mappedBy="aa")
	private Set<Aab> aabs;

	//bi-directional many-to-one association to Aac
	@OneToMany(mappedBy="aa")
	private Set<Aac> aacs;

	//bi-directional one-to-one association to Aaz
	@OneToOne(mappedBy="aa")
	private Aaz aaz;

	public Aa() {
	}

	public Integer getAaId() {
		return this.aaId;
	}

	public void setAaId(Integer aaId) {
		this.aaId = aaId;
	}

	public String getAaValue() {
		return this.aaValue;
	}

	public void setAaValue(String aaValue) {
		this.aaValue = aaValue;
	}

	public A getA() {
		return this.a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public Set<Aaa> getAaas() {
		return this.aaas;
	}

	public void setAaas(Set<Aaa> aaas) {
		this.aaas = aaas;
	}

	public Aaa addAaa(Aaa aaa) {
		getAaas().add(aaa);
		aaa.setAa(this);

		return aaa;
	}

	public Aaa removeAaa(Aaa aaa) {
		getAaas().remove(aaa);
		aaa.setAa(null);

		return aaa;
	}

	public Set<Aab> getAabs() {
		return this.aabs;
	}

	public void setAabs(Set<Aab> aabs) {
		this.aabs = aabs;
	}

	public Aab addAab(Aab aab) {
		getAabs().add(aab);
		aab.setAa(this);

		return aab;
	}

	public Aab removeAab(Aab aab) {
		getAabs().remove(aab);
		aab.setAa(null);

		return aab;
	}

	public Set<Aac> getAacs() {
		return this.aacs;
	}

	public void setAacs(Set<Aac> aacs) {
		this.aacs = aacs;
	}

	public Aac addAac(Aac aac) {
		getAacs().add(aac);
		aac.setAa(this);

		return aac;
	}

	public Aac removeAac(Aac aac) {
		getAacs().remove(aac);
		aac.setAa(null);

		return aac;
	}

	public Aaz getAaz() {
		return this.aaz;
	}

	public void setAaz(Aaz aaz) {
		this.aaz = aaz;
	}

}