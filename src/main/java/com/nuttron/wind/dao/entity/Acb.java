package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ACB database table.
 * 
 */
@Entity
@Table(name="ACB")
public class Acb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACB_ID")
	private Integer acbId;

	@Column(name="ACB_VALUE")
	private String acbValue;

	//bi-directional many-to-one association to Ac
	@ManyToOne
	@JoinColumn(name="AC_ID")
	private Ac ac;

	//bi-directional one-to-one association to Acbz
	@OneToOne(mappedBy="acb")
	private Acbz acbz;

	public Acb() {
	}

	public Integer getAcbId() {
		return this.acbId;
	}

	public void setAcbId(Integer acbId) {
		this.acbId = acbId;
	}

	public String getAcbValue() {
		return this.acbValue;
	}

	public void setAcbValue(String acbValue) {
		this.acbValue = acbValue;
	}

	public Ac getAc() {
		return this.ac;
	}

	public void setAc(Ac ac) {
		this.ac = ac;
	}

	public Acbz getAcbz() {
		return this.acbz;
	}

	public void setAcbz(Acbz acbz) {
		this.acbz = acbz;
	}

}