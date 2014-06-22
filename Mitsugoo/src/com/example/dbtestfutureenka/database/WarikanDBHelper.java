package com.example.dbtestfutureenka.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class WarikanDBHelper extends SQLiteOpenHelper {
	private static String DB_NAME = "warikan_db";
	/** 「warikan」テーブルの作成用SQL */
	private static final String CREATE_TABLE_SQL = ""
			+ "create table warikan ("
			+ "rowid integer primary key autoincrement, "
			+ "name text not null ," + "day text not null ,"
			+ "price integer not null" + ")";

	/** 「dbtest」テーブルの削除用SQL */
	private static final String DROP_TABLE_SQL = "drop table if exists warikan";

	/**
	 * コンストラクタ（必須）
	 * 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public WarikanDBHelper(Context context, CursorFactory factory, int version) {

		super(context, DB_NAME, factory, version);
	}

	/**
	 * テーブルの生成（必須）
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SQL);
	}

	/**
	 * テーブルの再作成（必須）
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE_SQL);
	}

}