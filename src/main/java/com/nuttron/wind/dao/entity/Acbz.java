package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ACBZ database table.
 * 
 */
@Entity
@Table(name="ACBZ")
public class Acbz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACB_ID")
	private Integer acbId;

	@Column(name="ACBZ_VALUE")
	private String acbzValue;

	//bi-directional one-to-one association to Acb
	@OneToOne
	@JoinColumn(name="ACB_ID")
	private Acb acb;

	public Acbz() {
	}

	public Integer getAcbId() {
		return this.acbId;
	}

	public void setAcbId(Integer acbId) {
		this.acbId = acbId;
	}

	public String getAcbzValue() {
		return this.acbzValue;
	}

	public void setAcbzValue(String acbzValue) {
		this.acbzValue = acbzValue;
	}

	public Acb getAcb() {
		return this.acb;
	}

	public void setAcb(Acb acb) {
		this.acb = acb;
	}

}