package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ACA database table.
 * 
 */
@Entity
@Table(name="ACA")
public class Aca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACA_ID")
	private Integer acaId;

	@Column(name="ACA_VALUE")
	private String acaValue;

	//bi-directional many-to-one association to Ac
	@ManyToOne
	@JoinColumn(name="AC_ID")
	private Ac ac;

	//bi-directional one-to-one association to Acaz
	@OneToOne(mappedBy="aca")
	private Acaz acaz;

	public Aca() {
	}

	public Integer getAcaId() {
		return this.acaId;
	}

	public void setAcaId(Integer acaId) {
		this.acaId = acaId;
	}

	public String getAcaValue() {
		return this.acaValue;
	}

	public void setAcaValue(String acaValue) {
		this.acaValue = acaValue;
	}

	public Ac getAc() {
		return this.ac;
	}

	public void setAc(Ac ac) {
		this.ac = ac;
	}

	public Acaz getAcaz() {
		return this.acaz;
	}

	public void setAcaz(Acaz acaz) {
		this.acaz = acaz;
	}

}