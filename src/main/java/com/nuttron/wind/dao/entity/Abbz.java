package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ABBZ database table.
 * 
 */
@Entity
@Table(name="ABBZ")
public class Abbz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ABB_ID")
	private Integer abbId;

	@Column(name="ABBZ_VALUE")
	private String abbzValue;

	//bi-directional one-to-one association to Abb
	@OneToOne
	@JoinColumn(name="ABB_ID")
	private Abb abb;

	public Abbz() {
	}

	public Integer getAbbId() {
		return this.abbId;
	}

	public void setAbbId(Integer abbId) {
		this.abbId = abbId;
	}

	public String getAbbzValue() {
		return this.abbzValue;
	}

	public void setAbbzValue(String abbzValue) {
		this.abbzValue = abbzValue;
	}

	public Abb getAbb() {
		return this.abb;
	}

	public void setAbb(Abb abb) {
		this.abb = abb;
	}

}