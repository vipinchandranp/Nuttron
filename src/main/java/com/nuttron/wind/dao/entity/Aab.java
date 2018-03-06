
package com.nuttron.wind.dao.entity;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AAB database table.
 * 
 */
@Entity
@Table(name="AAB")
public class Aab implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="AAB_ID")
	private Integer aabId;

	@Column(name="AAB_VALUE")
	private String aabValue;

	//bi-directional many-to-one association to Aa
	@ManyToOne
	@JoinColumn(name="AA_ID")
	private Aa aa;

	//bi-directional one-to-one association to Aabz
	@OneToOne(mappedBy="aab")
	private Aabz aabz;

	public Aab() {
	}

	public Integer getAabId() {
		return this.aabId;
	}

	public void setAabId(Integer aabId) {
		this.aabId = aabId;
	}

	public String getAabValue() {
		return this.aabValue;
	}

	public void setAabValue(String aabValue) {
		this.aabValue = aabValue;
	}

	public Aa getAa() {
		return this.aa;
	}

	public void setAa(Aa aa) {
		this.aa = aa;
	}

	public Aabz getAabz() {
		return this.aabz;
	}

	public void setAabz(Aabz aabz) {
		this.aabz = aabz;
	}

}