
package com.nuttron.wind.dao.entity;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AAC database table.
 * 
 */
@Entity
@Table(name="AAC")
public class Aac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AAC_ID")
	private Integer aacId;

	@Column(name="AAC_VALUE")
	private String aacValue;

	//bi-directional many-to-one association to Aa
	@ManyToOne
	@JoinColumn(name="AA_ID")
	private Aa aa;

	//bi-directional one-to-one association to Aacz
	@OneToOne(mappedBy="aac")
	private Aacz aacz;

	public Aac() {
	}

	public Integer getAacId() {
		return this.aacId;
	}

	public void setAacId(Integer aacId) {
		this.aacId = aacId;
	}

	public String getAacValue() {
		return this.aacValue;
	}

	public void setAacValue(String aacValue) {
		this.aacValue = aacValue;
	}

	public Aa getAa() {
		return this.aa;
	}

	public void setAa(Aa aa) {
		this.aa = aa;
	}

	public Aacz getAacz() {
		return this.aacz;
	}

	public void setAacz(Aacz aacz) {
		this.aacz = aacz;
	}

}