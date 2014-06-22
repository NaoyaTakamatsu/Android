package com.example.dbtestfutureenka.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WarikanDao {

	// テーブルの定数
	private static String TABLE_NAME = "Warikan";
	private static String COLUMN_ID = "rowid";
	private static String COLUMN_NAME = "name";
	private static String COLUMN_DAY = "day";
	private static String COLUMN_PRICE = "price";
	private static String[] COLUMNS = { COLUMN_ID, COLUMN_NAME, COLUMN_DAY,
			COLUMN_PRICE };

	// SQLiteDatabase
	private SQLiteDatabase db;

	/**
	 * コンストラクタ
	 * 
	 * @param db
	 */
	public WarikanDao(SQLiteDatabase db) {
		this.db = db;
	}

	/**
	 * 全データの取得 ----------------①
	 * 
	 * @return
	 */
	public List<WarikanEntity> findAll() {
		List<WarikanEntity> entityList = new ArrayList<WarikanEntity>();
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null,
				COLUMN_ID);

		while (cursor.moveToNext()) {
			WarikanEntity entity = new WarikanEntity("", "", 0);
			entity.setRowId(cursor.getInt(0));
			entity.setName(cursor.getString(1));
			entity.setDay(cursor.getString(2));
			entity.setPrice(cursor.getInt(3));
			entityList.add(entity);
		}

		return entityList;
	}

	/**
	 * 特定IDのデータを取得 ----------------②
	 * 
	 * @param rowId
	 * @return
	 */
	public WarikanEntity findById(int rowId) {
		String selection = COLUMN_ID + "=" + rowId;
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, selection, null, null,
				null, null);

		cursor.moveToNext();
		WarikanEntity entity = new WarikanEntity("", "", 0);
		entity.setRowId(cursor.getInt(0));
		entity.setName(cursor.getString(1));
		entity.setDay(cursor.getString(2));
		entity.setPrice(cursor.getInt(3));

		return entity;
	}

	/**
	 * データの登録 ----------------③
	 * 
	 * @param data
	 * @return
	 */
	public long insert(WarikanEntity entity) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, entity.getName());
		values.put(COLUMN_DAY, entity.getDay());
		values.put(COLUMN_PRICE, entity.getPrice());
		return db.insert(TABLE_NAME, null, values);
	}

	/**
	 * データの更新 ----------------④
	 * 
	 * @param rowid
	 * @param date
	 * @return
	 */
	public int update(WarikanEntity entity) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, entity.getName());
		values.put(COLUMN_DAY, entity.getDay());
		values.put(COLUMN_PRICE, entity.getPrice());
		String whereClause = COLUMN_ID + "=" + entity.getRowId();
		return db.update(TABLE_NAME, values, whereClause, null);
	}

	/**
	 * データの削除 ----------------⑤
	 * 
	 * @param rowId
	 * @return
	 */
	public int delete(int rowId) {
		String whereClause = COLUMN_ID + "=" + rowId;
		return db.delete(TABLE_NAME, whereClause, null);
	}
}