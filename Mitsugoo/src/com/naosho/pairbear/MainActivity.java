package com.naosho.pairbear;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.dbtestfutureenka.database.WarikanDBHelper;
import com.example.dbtestfutureenka.database.WarikanDao;
import com.example.dbtestfutureenka.database.WarikanEntity;

public class MainActivity extends Activity implements OnClickListener {

	private String name, present_prise_string;

	private int present_prise;

	private EditText editName, editPrise;

	private Button buttonmitsugu, presentbotton, miseserchbottun, camerabottun;

	private ListView listContent;

	private WarikanDao dao;

	private WarikanAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		WarikanDBHelper helper = new WarikanDBHelper(this, null, 1);

		SQLiteDatabase db = helper.getReadableDatabase();

		dao = new WarikanDao(db);
		List<WarikanEntity> entity = dao.findAll();

		adapter = new WarikanAdapter(this, 0, entity);

		miseserchbottun = (Button) findViewById(R.id.miseserchbutton);

		miseserchbottun.setOnClickListener(this);

		buttonmitsugu = (Button) findViewById(R.id.mitsugu);

		buttonmitsugu.setOnClickListener(this);

		presentbotton = (Button) findViewById(R.id.presentbutton);

		presentbotton.setOnClickListener(this);

		camerabottun = (Button) findViewById(R.id.picturebutton);

		camerabottun.setOnClickListener(this);

		listContent = (ListView) findViewById(R.id.contentlist);

		listContent.setAdapter(adapter);

		editName = (EditText) findViewById(R.id.editName);

		editPrise = (EditText) findViewById(R.id.editPrise);

	}

	@Override
	public void onResume() {
		super.onResume();
		WarikanDBHelper helper = new WarikanDBHelper(this, null, 1);

		SQLiteDatabase db = helper.getReadableDatabase();

		dao = new WarikanDao(db);
		List<WarikanEntity> entity = dao.findAll();

		adapter = new WarikanAdapter(this, 0, entity);

		listContent.setAdapter(adapter);

		// アラートダイアログ処理
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);
		// アラートダイアログのタイトルを設定します
		alertDialogBuilder.setTitle("Onedari");
		// アラートダイアログのメッセージを設定します
		alertDialogBuilder.setMessage("削除しますか？");
		// アラートダイアログのキャンセルが可能かどうかを設定します
		alertDialogBuilder.setCancelable(true);
		// ここまでアラート処理

		// リストビューのアイテムが長押しされた時に呼び出す。
		listContent
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {

						// 長押しされたアイテムを取得する。
						final WarikanEntity item = (WarikanEntity) parent
								.getItemAtPosition(position);

						// アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
						alertDialogBuilder.setPositiveButton("削除する",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// データベースから選択された項目を削除
										dao.delete(item.getRowId());

										// 項目を削除
										adapter.remove(item);
									}
								});
						// アラートダイアログの否定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
						alertDialogBuilder.setNegativeButton("キャンセル",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								});
						// アラートダイアログを作成
						AlertDialog alertDialog = alertDialogBuilder.create();
						// アラートダイアログを表示します
						alertDialog.show();

						return false;
					}

				});

		editName.setText("");

		editPrise.setText("");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		// お店を探す為にホットペッパーの画面に飛ぶ処理
		case R.id.miseserchbutton:
			// インテントへのインスタンス生成
			Intent htpintent = new Intent(this, hotpepper.class);
			// サブ画面の呼び出し
			startActivity(htpintent);
			break;
		// 割り勘計算の画面に飛ぶ処置
		case R.id.mitsugu:
			if ((editName.length() != 0) & (editPrise.length() != 0)) {
				// インテントへのインスタンス生成
				Intent intent = new Intent(this, keisan.class);
				// 　インテントに値をセット
				intent.putExtra("name", editName.getText().toString());
				// 　インテントに値をセット
				intent.putExtra("prise",
						Integer.parseInt(editPrise.getText().toString()));
				// サブ画面の呼び出し
				startActivity(intent);
			}
			break;
		// プレゼントをもらった時を想定しているのでもらったものの金額がそのまま記録される
		case R.id.presentbutton:
			if ((editName.length() != 0) & (editPrise.length() != 0)) {
				WarikanDBHelper helper = new WarikanDBHelper(this, null, 1);

				SQLiteDatabase db = helper.getReadableDatabase();

				dao = new WarikanDao(db);

				Date date = new Date();
				// フォーマットパターンを指定して、SimpleDateFormatオブジェクトsdf1を生成
				// します。
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd",
						Locale.JAPAN);

				name = editName.getText().toString();

				present_prise_string = editPrise.getText().toString();

				present_prise = Integer.parseInt(present_prise_string);

				dao.insert(new WarikanEntity(name,
						sdf1.format(date).toString(), present_prise));

				List<WarikanEntity> entity = dao.findAll();

				adapter = new WarikanAdapter(this, 0, entity);

				listContent.setAdapter(adapter);

				editName.setText("");

				editPrise.setText("");
			}

			break;
		// カメラ機能にインテントする処理
		case R.id.picturebutton:
			Intent intent = new Intent();
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 1);
			break;

		}

	}
}
