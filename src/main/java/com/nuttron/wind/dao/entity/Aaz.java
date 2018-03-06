package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AAZ database table.
 * 
 */
@Entity
@Table(name="AAZ")
public class Aaz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AA_ID")
	private Integer aaId;

	@Column(name="AAZ_VALUE")
	private String aazValue;

	//bi-directional one-to-one association to Aa
	@OneToOne
	@JoinColumn(name="AA_ID")
	private Aa aa;

	public Aaz() {
	}

	public Integer getAaId() {
		return this.aaId;
	}

	public void setAaId(Integer aaId) {
		this.aaId = aaId;
	}

	public String getAazValue() {
		return this.aazValue;
	}

	public void setAazValue(String aazValue) {
		this.aazValue = aazValue;
	}

	public Aa getAa() {
		return this.aa;
	}

	public void setAa(Aa aa) {
		this.aa = aa;
	}

}