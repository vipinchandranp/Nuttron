package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the AB database table.
 * 
 */
@Entity
@Table(name="AB")
public class Ab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AB_ID")
	private Integer abId;

	@Column(name="AB_VALUE")
	private String abValue;

	//bi-directional many-to-one association to A
	@ManyToOne
	@JoinColumn(name="A_ID")
	private A a;

	//bi-directional many-to-one association to Aba
	@OneToMany(mappedBy="ab")
	private Set<Aba> abas;

	//bi-directional many-to-one association to Abb
	@OneToMany(mappedBy="ab")
	private Set<Abb> abbs;

	//bi-directional many-to-one association to Abc
	@OneToMany(mappedBy="ab")
	private Set<Abc> abcs;

	//bi-directional one-to-one association to Abz
	@OneToOne(mappedBy="ab")
	private Abz abz;

	public Ab() {
	}

	public Integer getAbId() {
		return this.abId;
	}

	public void setAbId(Integer abId) {
		this.abId = abId;
	}

	public String getAbValue() {
		return this.abValue;
	}

	public void setAbValue(String abValue) {
		this.abValue = abValue;
	}

	public A getA() {
		return this.a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public Set<Aba> getAbas() {
		return this.abas;
	}

	public void setAbas(Set<Aba> abas) {
		this.abas = abas;
	}

	public Aba addAba(Aba aba) {
		getAbas().add(aba);
		aba.setAb(this);

		return aba;
	}

	public Aba removeAba(Aba aba) {
		getAbas().remove(aba);
		aba.setAb(null);

		return aba;
	}

	public Set<Abb> getAbbs() {
		return this.abbs;
	}

	public void setAbbs(Set<Abb> abbs) {
		this.abbs = abbs;
	}

	public Abb addAbb(Abb abb) {
		getAbbs().add(abb);
		abb.setAb(this);

		return abb;
	}

	public Abb removeAbb(Abb abb) {
		getAbbs().remove(abb);
		abb.setAb(null);

		return abb;
	}

	public Set<Abc> getAbcs() {
		return this.abcs;
	}

	public void setAbcs(Set<Abc> abcs) {
		this.abcs = abcs;
	}

	public Abc addAbc(Abc abc) {
		getAbcs().add(abc);
		abc.setAb(this);

		return abc;
	}

	public Abc removeAbc(Abc abc) {
		getAbcs().remove(abc);
		abc.setAb(null);

		return abc;
	}

	public Abz getAbz() {
		return this.abz;
	}

	public void setAbz(Abz abz) {
		this.abz = abz;
	}

}