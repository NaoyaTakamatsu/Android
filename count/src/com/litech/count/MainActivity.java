package com.litech.count;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView text;
	int number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text = (TextView) findViewById(R.id.textView1);

	}

	// ここから下に書こう

	// purasuボタンの処理
	public void purasu(View view) {
		number = number + 1;
		text.setText(String.valueOf(number));
	}

	//
	public void mainasu(View view) {
		number = number - 1;
		text.setText(String.valueOf(number));
	}

	//
	public void kakeru(View view) {
		number = number * 2;
		text.setText(String.valueOf(number));
	}

	//
	public void waru(View view) {
		number = number / 2;
		text.setText(String.valueOf(number));
	}

	//
	public void clear(View view) {
		number = 0;
		text.setText(String.valueOf(number));
	}

	// ここより上に書こう

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
