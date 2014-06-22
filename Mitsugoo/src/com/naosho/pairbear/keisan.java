package com.naosho.pairbear;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dbtestfutureenka.database.WarikanDBHelper;
import com.example.dbtestfutureenka.database.WarikanDao;
import com.example.dbtestfutureenka.database.WarikanEntity;

public class keisan extends Activity implements OnClickListener {

	private EditText etxtMoney;
	private TextView txtResult;
	private TextView txtOneMoney;
	private TextView txtYen;
	private TextView txtaite;

	private int prise;
	private int priseaite;
	private int priseview;
	private int prisedata, prisedatasyasin;
	private int prisehanbun;
	private double smile;
	private int syasinsmile, syasinsmilekanozyo;
	private int syasinflag = 0;
	private int kakuteiflag = 0;
	private String name;
	private Button S, A, B, C, D, X, kakutei, syasin;
	private WarikanDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keisan);
		// インテントを取得
		Intent intent = getIntent();
		// インテントに保存されたデータを取得
		name = intent.getStringExtra("name");
		prise = intent.getIntExtra("prise", prise);
		// smile = intent.getIntExtra("smile", smile);
		smile = intent.getDoubleExtra("smile", smile);
		syasinflag = intent.getIntExtra("syasinflag", syasinflag);

		prisehanbun = (int) (prise * 0.5);

		txtResult = (TextView) findViewById(R.id.txtResult);
		txtaite = (TextView) findViewById(R.id.txtaite);
		txtOneMoney = (TextView) findViewById(R.id.txtOneMoney);
		txtYen = (TextView) findViewById(R.id.txtYen);
		S = (Button) findViewById(R.id.S);
		A = (Button) findViewById(R.id.A);
		B = (Button) findViewById(R.id.B);
		C = (Button) findViewById(R.id.C);
		D = (Button) findViewById(R.id.D);
		X = (Button) findViewById(R.id.X);
		kakutei = (Button) findViewById(R.id.kakutei);
		syasin = (Button) findViewById(R.id.rekobutton);

		S.setOnClickListener(this);
		A.setOnClickListener(this);
		B.setOnClickListener(this);
		C.setOnClickListener(this);
		D.setOnClickListener(this);
		X.setOnClickListener(this);
		kakutei.setOnClickListener(this);
		syasin.setOnClickListener(this);

		// 写真判定をした場合の彼氏が払う金額
		syasinsmile = (int) (prise * smile);
		// 写真判定をした場合の彼女が支払う金額
		syasinsmilekanozyo = prise - syasinsmile;
		// それぞれテキストにセット
		txtResult.setText("" + syasinsmile);
		txtaite.setText("" + syasinsmilekanozyo);
		// 写真判定の場合にどれだけ奢ってもらえたか
		prisedatasyasin = syasinsmile - prisehanbun;

		// Button btn = (Button) findViewById(R.id.B);
		// btn.setOnClickListener(new View.OnClickListener() {
	}

	@Override
	public void onResume() {
		super.onResume();
		// インテントを取得
		Intent intent = getIntent();
		// インテントに保存されたデータを取得
		name = intent.getStringExtra("name");
		prise = intent.getIntExtra("prise", prise);
		// smile = intent.getIntExtra("smile", smile);
		smile = intent.getDoubleExtra("smile", smile);
		syasinflag = intent.getIntExtra("syasinflag", syasinflag);
		// 写真判定をした場合の彼氏が払う金額
		syasinsmile = (int) (prise * smile);
		// 写真判定をした場合の彼女が支払う金額
		syasinsmilekanozyo = prise - syasinsmile;
		// それぞれテキストにセット
		txtResult.setText("" + syasinsmile);
		txtaite.setText("" + syasinsmilekanozyo);
		// 写真判定の場合にどれだけ奢ってもらえたか
		prisedatasyasin = syasinsmile - prisehanbun;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.S:
			if (syasinflag == 0) {
				priseview = (int) (prise * 1.0);
				txtResult.setText("" + priseview);
				prisedata = priseview - prisehanbun;
				priseaite = prise - priseview;
				txtaite.setText("" + priseaite);
				kakuteiflag = 1;
			} else if (syasinflag == 1) {

			}
			break;
		case R.id.A:
			if (syasinflag == 0) {
				priseview = (int) (prise * 0.8);
				txtResult.setText("" + priseview);
				prisedata = priseview - prisehanbun;
				priseaite = prise - priseview;
				txtaite.setText("" + priseaite);
				kakuteiflag = 1;
			} else if (syasinflag == 1) {

			}
			break;
		case R.id.B:
			if (syasinflag == 0) {
				priseview = (int) (prise * 0.6);
				txtResult.setText("" + priseview);
				prisedata = priseview - prisehanbun;
				priseaite = prise - priseview;
				txtaite.setText("" + priseaite);
				kakuteiflag = 1;
			} else if (syasinflag == 1) {

			}
			break;
		case R.id.C:
			if (syasinflag == 0) {
				priseview = (int) (prise * 0.5);
				txtResult.setText("" + priseview);
				prisedata = priseview - prisehanbun;
				priseaite = prise - priseview;
				txtaite.setText("" + priseaite);
				kakuteiflag = 1;
			} else if (syasinflag == 1) {

			}
			break;
		case R.id.D:
			if (syasinflag == 0) {
				priseview = (int) (prise * 0.3);
				txtResult.setText("" + priseview);
				prisedata = priseview - prisehanbun;
				priseaite = prise - priseview;
				txtaite.setText("" + priseaite);
				kakuteiflag = 1;
			} else if (syasinflag == 1) {

			}
			break;
		case R.id.X:
			if (syasinflag == 0) {
				int ran = (int) (Math.random() * 10);
				priseview = (int) (prise * ran * 0.1);
				txtResult.setText("" + priseview);
				prisedata = priseview - prisehanbun;
				priseaite = prise - priseview;
				txtaite.setText("" + priseaite);
				kakuteiflag = 1;
			} else if (syasinflag == 1) {

			}
			break;
		case R.id.kakutei:
			if (kakuteiflag == 1 || syasinflag == 1) {
				if (syasinflag == 1) {
					kakuteisyasin();
				} else if (syasinflag == 0) {
					kakutei();
				}
			}
			break;
		case R.id.rekobutton:
			// syasinflag = 1;
			// インテントへのインスタンス生成
			Intent intent = new Intent(this, MainActivityreko.class);
			// 　インテントに値をセット
			intent.putExtra("name", name);
			// 　インテントに値をセット
			intent.putExtra("prise", prise);
			// サブ画面の呼び出し：写真判定画面へ
			startActivity(intent);
			break;
		}

	}

	// 写真判定使わない場合の確定処理
	private void kakutei() {
		// TODO Auto-generated method stub
		WarikanDBHelper helper = new WarikanDBHelper(this, null, 1);

		SQLiteDatabase db = helper.getReadableDatabase();

		dao = new WarikanDao(db);

		Date date = new Date();
		// フォーマットパターンを指定して、SimpleDateFormatオブジェクトsdf1を生成
		// します。
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);

		dao.insert(new WarikanEntity(name, sdf1.format(date).toString(),
				prisedata));

		Log.v("find", dao.findAll().get(0).getDay());

		// インテントへのインスタンス生成
		Intent kakuteiintent = new Intent(this, MainActivity.class);
		// サブ画面の呼び出し：写真判定画面へ
		startActivity(kakuteiintent);

	}

	// 写真判定を使った場合の確定処理
	private void kakuteisyasin() {
		// TODO Auto-generated method stub
		WarikanDBHelper helper = new WarikanDBHelper(this, null, 1);

		SQLiteDatabase db = helper.getReadableDatabase();

		dao = new WarikanDao(db);

		Date date = new Date();
		// フォーマットパターンを指定して、SimpleDateFormatオブジェクトsdf1を生成
		// します。
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);

		dao.insert(new WarikanEntity(name, sdf1.format(date).toString(),
				prisedatasyasin));

		Log.v("find", dao.findAll().get(0).getDay());

		// インテントへのインスタンス生成
		Intent kakuteiintent = new Intent(this, MainActivity.class);
		// サブ画面の呼び出し：写真判定画面へ
		startActivity(kakuteiintent);

	}
}