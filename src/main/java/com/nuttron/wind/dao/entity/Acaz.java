package com.nuttron.wind.dao.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ACAZ database table.
 * 
 */
@Entity
@Table(name="ACAZ")
public class Acaz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACA_ID")
	private Integer acaId;

	@Column(name="ACAZ_VALUE")
	private String acazValue;

	//bi-directional one-to-one association to Aca
	@OneToOne
	@JoinColumn(name="ACA_ID")
	private Aca aca;

	public Acaz() {
	}

	public Integer getAcaId() {
		return this.acaId;
	}

	public void setAcaId(Integer acaId) {
		this.acaId = acaId;
	}

	public String getAcazValue() {
		return this.acazValue;
	}

	public void setAcazValue(String acazValue) {
		this.acazValue = acazValue;
	}

	public Aca getAca() {
		return this.aca;
	}

	public void setAca(Aca aca) {
		this.aca = aca;
	}

}