package com.csmoseley.discountcalculator;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditText input = (EditText) findViewById(R.id.fieldEntry);
		input.addTextChangedListener(typingListener);
		SeekBar seek = (SeekBar) findViewById(R.id.seekBar1);

		seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int value, boolean arg2) {
				// change custom value and update if custom selected.
				RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
				int selected = radioGroup.getCheckedRadioButtonId();
				TextView customValText = (TextView) findViewById(R.id.customValueText);
				customValText.setText(Integer.toString(value) + "%");
				if ((selected - 2131230725) == 3) {
					updateValue(value);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	public void updateValue(View view) {
		updateValue();
	}

	public void updateValue() {
		SeekBar customVal = (SeekBar) findViewById(R.id.seekBar1);
		Double doubleCustom = (double) customVal.getProgress();
		updateValue(doubleCustom);
	}

	public void updateValue(double custom) {
		TextView saved = (TextView) findViewById(R.id.dynSaveText);
		TextView pay = (TextView) findViewById(R.id.dynPayText);
		EditText edit = (EditText) findViewById(R.id.fieldEntry);
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		double total;
		if (0 != edit.getText().toString().length()) {
			total = Double.parseDouble(edit.getText().toString());
		} else {
			total = 0.00;
			edit.setError("Enter List Price");
		}
		double percent = custom;
		if (total == 0) {
			saved.setText("$0.00");
			pay.setText("$0.00");
		} else {
			int selected = radioGroup.getCheckedRadioButtonId();
			Log.i("percent", Integer.toString(selected));
			switch (selected - 2131230725) {
			case 0:
				percent = .1;
				break;
			case 1:
				percent = .25;
				break;
			case 2:
				percent = .50;
				break;
			case 3:
				percent = custom / 100;
				break;
			}
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			saved.setText(nf.format(percent * total));
			pay.setText(nf.format((1 - percent) * total));
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private TextWatcher typingListener = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable arg0) {
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {

		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// change values if edited.
			updateValue();
		}

	};

	public void exitApp(View v) {
		finish();
	}
}