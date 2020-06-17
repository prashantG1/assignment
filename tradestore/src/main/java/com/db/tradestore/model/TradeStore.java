package com.db.tradestore.model;


import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * class:TradeStore
 */
@Entity
@Table(name = "TradeStore")
public class TradeStore {
	
	@Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
	
	@Column(name = "Trade_Id")
	private String tradeID;
	@Column(name = "version")
	private String version ;
	@Column(name = "Counter_Party_Id")
	private String counterPartyID;
	@Column(name = "Book_Id")
	private String bookID;
	
	@Column(name = "Maturity_Date")
	private String maturityDate;
	
	@Column(name = "Created_Date")
	private String createdDate;
	@Column(name = "expired")
	private String expired;

	public TradeStore() {
		
	}
	public TradeStore(String tradeID,String version,String counterPartyID,String bookID,String maturityDate,String createdDate,String expired) {
		
		this.tradeID=tradeID;
		this.version=version;
		this.counterPartyID=counterPartyID;
		this.bookID=bookID;
		this.maturityDate=maturityDate;
		this.createdDate=createdDate;
		this.expired=expired;
			}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the tradeID
	 */
	public String getTradeID() {
		return tradeID;
	}

	/**
	 * @param tradeID the tradeID to set
	 */
	public void setTradeID(String tradeID) {
		this.tradeID = tradeID;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the counterPartyID
	 */
	public String getCounterPartyID() {
		return counterPartyID;
	}

	/**
	 * @param counterPartyID the counterPartyID to set
	 */
	public void setCounterPartyID(String counterPartyID) {
		this.counterPartyID = counterPartyID;
	}

	/**
	 * @return the bookID
	 */
	public String getBookID() {
		return bookID;
	}

	/**
	 * @param bookID the bookID to set
	 */
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	

	/**
	 * @return the maturityDate
	 */
	public String getMaturityDate() {
		return maturityDate;
	}

	/**
	 * @param maturityDate the maturityDate to set
	 */
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the expired
	 */
	public String getExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(String expired) {
		this.expired = expired;
	}
	
	

}
