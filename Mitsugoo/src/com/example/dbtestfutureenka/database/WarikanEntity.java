package com.example.dbtestfutureenka.database;

public class WarikanEntity {

	private int rowId;

	private String name;

	private String day;

	private int price;

	public WarikanEntity(String name, String day, int price) {
		this.name = name;
		this.day = day;
		this.price = price;

	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
