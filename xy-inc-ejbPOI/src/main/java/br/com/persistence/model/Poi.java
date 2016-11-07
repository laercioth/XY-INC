package br.com.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the POI database table.
 * 
 */
@Entity
@Table(name = "POI")
@NamedQuery(name = "Poi.findAll", query = "SELECT p FROM Poi p")
public class Poi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ID", nullable = false, precision = 10, scale = 0)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME_PLACE", nullable = true, precision = 25)
	private String namePlace;

	@Column(name = "X_COORDINATE", nullable = true, precision = 25)
	private Long xCoordinate;

	@Column(name = "Y_COORDINATE", nullable = true, precision = 25)
	private Long yCoordinate;

	public Poi() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamePlace() {
		return this.namePlace;
	}

	public void setNamePlace(String namePlace) {
		this.namePlace = namePlace;
	}

	public Long getXCoordinate() {
		return this.xCoordinate;
	}

	public void setXCoordinate(Long xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Long getYCoordinate() {
		return this.yCoordinate;
	}

	/**
	 * @param yCoordinate
	 */
	public void setYCoordinate(Long yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poi other = (Poi) obj;
		if (id != other.id)
			return false;
		return true;
	}
}