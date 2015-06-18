package com.wiipu.mall.activity;

import com.wiipu.mall.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends Activity {

	private ImageButton ibBack;
	private EditText etAccount;
	private EditText etPassword;
	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initView();
	}

	private void initView() {
		ibBack = (ImageButton) findViewById(R.id.login_ib_back);
		ibBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		MyWatcher watcher = new MyWatcher();
		etAccount = (EditText) findViewById(R.id.login_et_account);
		etAccount.addTextChangedListener(watcher);
		etPassword = (EditText) findViewById(R.id.login_et_password);
		etPassword.addTextChangedListener(watcher);
		btnLogin = (Button) findViewById(R.id.login_btn_login);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});
	}

	class MyWatcher implements TextWatcher {

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			if ("".equals(etAccount.getText().toString())
					|| "".equals(etPassword.getText().toString())) {
				btnLogin.setEnabled(false);
			} else {
				btnLogin.setEnabled(true);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}

		@Override
		public void afterTextChanged(Editable arg0) {
		}
	}
}
