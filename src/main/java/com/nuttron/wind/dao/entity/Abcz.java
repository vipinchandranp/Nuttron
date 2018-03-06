package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ABCZ database table.
 * 
 */
@Entity
@Table(name="ABCZ")
public class Abcz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ABC_ID")
	private Integer abcId;

	@Column(name="ABCZ_VALUE")
	private String abczValue;

	//bi-directional one-to-one association to Abc
	@OneToOne
	@JoinColumn(name="ABC_ID")
	private Abc abc;

	public Abcz() {
	}

	public Integer getAbcId() {
		return this.abcId;
	}

	public void setAbcId(Integer abcId) {
		this.abcId = abcId;
	}

	public String getAbczValue() {
		return this.abczValue;
	}

	public void setAbczValue(String abczValue) {
		this.abczValue = abczValue;
	}

	public Abc getAbc() {
		return this.abc;
	}

	public void setAbc(Abc abc) {
		this.abc = abc;
	}

}