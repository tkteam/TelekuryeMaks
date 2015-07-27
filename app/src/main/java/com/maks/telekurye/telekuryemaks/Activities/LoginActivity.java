package com.maks.telekurye.telekuryemaks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.maks.telekurye.telekuryemaks.Core.ApplicationContext;
import com.maks.telekurye.telekuryemaks.R;
import com.maks.telekurye.ui.FormBase;

/**
 * Created by yunusemre on 22.07.2015.
 */
public class LoginActivity extends FormBase {

	// region Widgets
	Button			btnLogin;
	Button			btnRequestPass;
	Button			btnTopbarSettings;
	EditText		txtUserName;
	EditText		txtPassword;
	CheckBox		chkRememberUsername;
	TextView		lblCountDown;
	// endregion

	// region Properties
	CountDownTimer	countDownTimer;

	static String	prefCheckboxState	= "CheckboxState";
	static String	prefUsername		= "Username";
	static String	userName			= "";

	// endregion

	// region Constructor
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		if (initWidget) {
			initWidgets();
		}
	}

	@Override
	public void initWidgets() {
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnRequestPass = (Button) findViewById(R.id.btnRequestPass);
		btnTopbarSettings = (Button) findViewById(R.id.btnTopbarSettings);
		txtUserName = (EditText) findViewById(R.id.txtUsername);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		chkRememberUsername = (CheckBox) findViewById(R.id.chkRememberUsername);
		lblCountDown = (TextView) findViewById(R.id.lblCountDown);

		btnLogin.setEnabled(false);
		txtPassword.setEnabled(false);

		btnLogin.setOnClickListener(btnLoginListener);
		btnRequestPass.setOnClickListener(btnRequestPassListener);
		btnTopbarSettings.setOnClickListener(btnTopbarSettingsListener);

		String chkState = ApplicationContext.getInstance().getPref(prefCheckboxState);
		if (chkState != null) {
			if (chkState.equals("1")) {
				chkRememberUsername.setChecked(true);
			}
		}

		String username = ApplicationContext.getInstance().getPref(prefUsername);
		if (username != null) {
			txtUserName.setText(username);
		}

	}

	// endregion

	// region Listeners
	View.OnClickListener	btnTopbarSettingsListener	= new View.OnClickListener() {
															@Override
															public void onClick(View v) {
																Intent intent = new Intent(LoginActivity.this, SettingsActivity.class);
																startActivity(intent);
															}
														};

	View.OnClickListener	btnLoginListener			= new View.OnClickListener() {
															@Override
															public void onClick(View v) {

																String strUsername = txtUserName.getText().toString();
																String strPassword = txtPassword.getText().toString();
																if (true) // Giris basarili ise
																{
																	countDownTimer.cancel();
																	if (chkRememberUsername.isChecked()) {
																		ApplicationContext.getInstance().setPref(prefCheckboxState, "1");
																		ApplicationContext.getInstance().setPref(prefUsername, strUsername);
																	}
																	else {
																		ApplicationContext.getInstance().setPref(prefCheckboxState, "0");
																		ApplicationContext.getInstance().setPref(prefUsername, "");
																	}

																	Intent intent = new Intent(LoginActivity.this, AssigmentMissionActivity.class);
																	startActivity(intent);

																}
															}
														};

	View.OnClickListener	btnRequestPassListener		= new View.OnClickListener() {
															@Override
															public void onClick(View v) {
																btnLogin.setEnabled(true);
																txtPassword.setEnabled(true);
																txtUserName.setEnabled(false);
																btnRequestPass.setEnabled(false);

																countDownTimer = new CountDownTimer(5000, 1000) {
																	@Override
																	public void onTick(long millisUntilFinished) {
																		lblCountDown.setText("" + millisUntilFinished / 1000);
																	}

																	@Override
																	public void onFinish() {
																		lblCountDown.setText("");
																		btnLogin.setEnabled(false);
																		txtPassword.setEnabled(false);
																		txtUserName.setEnabled(true);
																		btnRequestPass.setEnabled(true);
																	}
																};
																countDownTimer.start();

															}
														};

	// endregion

}
