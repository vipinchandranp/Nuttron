package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the AC database table.
 * 
 */
@Entity
@Table(name="AC")
public class Ac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AC_ID")
	private Integer acId;

	@Column(name="AC_VALUE")
	private String acValue;

	//bi-directional many-to-one association to A
	@ManyToOne
	@JoinColumn(name="A_ID")
	private A a;

	//bi-directional many-to-one association to Aca
	@OneToMany(mappedBy="ac")
	private Set<Aca> acas;

	//bi-directional many-to-one association to Acb
	@OneToMany(mappedBy="ac")
	private Set<Acb> acbs;

	//bi-directional many-to-one association to Acc
	@OneToMany(mappedBy="ac")
	private Set<Acc> accs;

	//bi-directional one-to-one association to Acz
	@OneToOne(mappedBy="ac")
	private Acz acz;

	public Ac() {
	}

	public Integer getAcId() {
		return this.acId;
	}

	public void setAcId(Integer acId) {
		this.acId = acId;
	}

	public String getAcValue() {
		return this.acValue;
	}

	public void setAcValue(String acValue) {
		this.acValue = acValue;
	}

	public A getA() {
		return this.a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public Set<Aca> getAcas() {
		return this.acas;
	}

	public void setAcas(Set<Aca> acas) {
		this.acas = acas;
	}

	public Aca addAca(Aca aca) {
		getAcas().add(aca);
		aca.setAc(this);

		return aca;
	}

	public Aca removeAca(Aca aca) {
		getAcas().remove(aca);
		aca.setAc(null);

		return aca;
	}

	public Set<Acb> getAcbs() {
		return this.acbs;
	}

	public void setAcbs(Set<Acb> acbs) {
		this.acbs = acbs;
	}

	public Acb addAcb(Acb acb) {
		getAcbs().add(acb);
		acb.setAc(this);

		return acb;
	}

	public Acb removeAcb(Acb acb) {
		getAcbs().remove(acb);
		acb.setAc(null);

		return acb;
	}

	public Set<Acc> getAccs() {
		return this.accs;
	}

	public void setAccs(Set<Acc> accs) {
		this.accs = accs;
	}

	public Acc addAcc(Acc acc) {
		getAccs().add(acc);
		acc.setAc(this);

		return acc;
	}

	public Acc removeAcc(Acc acc) {
		getAccs().remove(acc);
		acc.setAc(null);

		return acc;
	}

	public Acz getAcz() {
		return this.acz;
	}

	public void setAcz(Acz acz) {
		this.acz = acz;
	}

}