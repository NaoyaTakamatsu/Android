package com.naosho.pairbear;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class hotpepper extends Activity implements OnClickListener {

	private WebView myWebView;
	private Button homeButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotpepper);

		// myWebViewのインスタンスを取得
		myWebView = (WebView) findViewById(R.id.htp_webView);
		homeButton = (Button) findViewById(R.id.homebutton);
		homeButton.setOnClickListener(this);

		// hotpepperを表示する
		myWebView.loadUrl("http://www.hotpepper.jp/");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.homebutton:
			finish();
			break;
		}
	}
}