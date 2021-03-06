package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table(name="screen")
public class Screen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="seqgen")
	@SequenceGenerator(name="seqgen",sequenceName = "sid_seq")
	private int screenId;
	private String screenName;
	@OneToMany(targetEntity = Show.class, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="screenshowfk", referencedColumnName = "screenId")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Show> shows = new ArrayList<>();
	private int seatRows;
	private int seatColumns;

	public Screen() {

	}

	public Screen(int theaterId, String screenName, List<Show> shows, int seatRows, int seatColumns) {
		super();
		this.screenName = screenName;
		this.shows = shows;
		this.seatRows = seatRows;
		this.seatColumns = seatColumns;
	}

	
	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public int getSeatRows() {
		return seatRows;
	}

	public void setSeatRows(int seatRows) {
		this.seatRows = seatRows;
	}

	public int getSeatColumns() {
		return seatColumns;
	}

	public void setSeatColumns(int seatColumns) {
		this.seatColumns = seatColumns;
	}

	@Override
	public String toString() {
		return "Screen [screenId=" + screenId + ", screenName=" + screenName + ", shows=" + shows + ", seatRows="
				+ seatRows + ", seatColumns=" + seatColumns + "]";
	}

}