package com.naosho.pairbear;

import rekognition.RekoSDK;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityreko extends Activity {

	public final static String JSON = "demo.JSON";
	private final static int PICK_PHOTO_REQUEST_CODE = 1;
	private final static String ARG_IMAGE_BYTES = "image_holder";
	private ProgressDialog mDialog = null;
	private int prise;
	private double smile;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainreko);
		// インテントを取得
		Intent intent = getIntent();
		// インテントに保存されたデータを取得
		name = intent.getStringExtra("name");
		prise = intent.getIntExtra("prise", prise);
		// smile = intent.getIntExtra("smile", smile);
		smile = intent.getDoubleExtra("smile", smile);
		if (savedInstanceState != null) {
			ImageDataHolder.INSTANCE.recoverFromByteArray(savedInstanceState
					.getByteArray(ARG_IMAGE_BYTES));
			this.onImagePicked();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putByteArray(ARG_IMAGE_BYTES,
				ImageDataHolder.INSTANCE.getByteArray());
	}

	public void onPickImage(View view) {
		// Body of your click handler
		Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, PICK_PHOTO_REQUEST_CODE);
	}

	// 戻るボタン
	public void syasinback(View view) {
		// インテントへのインスタンス生成
		Intent keisanintent = new Intent(this, keisan.class);
		// サブ画面の呼び出し：写真判定画面へ
		startActivity(keisanintent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PICK_PHOTO_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				Uri chosenImageUri = data.getData();
				ImageDataHolder.INSTANCE.loadData(chosenImageUri, this);
				this.onImagePicked();
			}
		}
	}

	// 写真が選ばれたときに笑顔認識のボタンを出す
	private void onImagePicked() {
		Button pickButton = (Button) this.findViewById(R.id.button_pick_image);
		pickButton.setText(R.string.repick);
		Button faceButton = (Button) this
				.findViewById(R.id.button_face_detection);
		Button sceneButton = (Button) this
				.findViewById(R.id.button_scene_understanding);
		faceButton.setVisibility(View.VISIBLE);
		sceneButton.setVisibility(View.VISIBLE);
	}

	// 顔認識処理
	public void onFaceDetection(View view) {
		RekoSDK.APICallback callback = new RekoSDK.APICallback() {
			public void gotResponse(String sResponse) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mDialog.dismiss();
					}
				});
				startDisplayMessageActivity(sResponse);
			}
		};
		mDialog = ProgressDialog.show(this, "", "Loading...");
		RekoSDK.face_detect(ImageDataHolder.INSTANCE.getByteArray(), callback);
	}

	// シーン分析処理
	public void onSceneUnderstanding(View view) {
		RekoSDK.APICallback callback = new RekoSDK.APICallback() {
			public void gotResponse(String sResponse) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mDialog.dismiss();
					}
				});
				startDisplayMessageActivity(sResponse);
			}
		};
		mDialog = ProgressDialog.show(this, "", "Loading...");
		RekoSDK.scene_understand(ImageDataHolder.INSTANCE.getByteArray(),
				callback);
	}

	// 写真判定をするディスプレイに移動
	private void startDisplayMessageActivity(String message) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		intent.putExtra(JSON, message);
		// 　インテントに値をセット
		intent.putExtra("name", name);
		// 　インテントに値をセット
		intent.putExtra("prise", prise);
		startActivity(intent);
	}
}
