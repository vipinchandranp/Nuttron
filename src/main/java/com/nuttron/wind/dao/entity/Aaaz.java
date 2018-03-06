package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AAAZ database table.
 * 
 */
@Entity
@Table(name="AAAZ")
public class Aaaz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AAA_ID")
	private Integer aaaId;

	@Column(name="AAAZ_VALUE")
	private String aaazValue;

	//bi-directional one-to-one association to Aaa
	@OneToOne
	@JoinColumn(name="AAA_ID")
	private Aaa aaa;

	public Aaaz() {
	}

	public Integer getAaaId() {
		return this.aaaId;
	}

	public void setAaaId(Integer aaaId) {
		this.aaaId = aaaId;
	}

	public String getAaazValue() {
		return this.aaazValue;
	}

	public void setAaazValue(String aaazValue) {
		this.aaazValue = aaazValue;
	}

	public Aaa getAaa() {
		return this.aaa;
	}

	public void setAaa(Aaa aaa) {
		this.aaa = aaa;
	}

}