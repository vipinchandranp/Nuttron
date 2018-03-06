package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AACZ database table.
 * 
 */
@Entity
@Table(name="AACZ")
public class Aacz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AAC_ID")
	private Integer aacId;

	@Column(name="AACZ_VALUE")
	private String aaczValue;

	//bi-directional one-to-one association to Aac
	@OneToOne
	@JoinColumn(name="AAC_ID")
	private Aac aac;

	public Aacz() {
	}

	public Integer getAacId() {
		return this.aacId;
	}

	public void setAacId(Integer aacId) {
		this.aacId = aacId;
	}

	public String getAaczValue() {
		return this.aaczValue;
	}

	public void setAaczValue(String aaczValue) {
		this.aaczValue = aaczValue;
	}

	public Aac getAac() {
		return this.aac;
	}

	public void setAac(Aac aac) {
		this.aac = aac;
	}

}