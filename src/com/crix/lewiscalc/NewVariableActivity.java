package com.crix.lewiscalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewVariableActivity extends Activity {
	EvalContext con = EvalContext.getSingleton();
	
	EditText etName;
	EditText etValue;
	Button btnSubmit;
	Button btnCancel;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_var);
        
        etName = (EditText)findViewById(R.id.etName);
        etValue = (EditText)findViewById(R.id.etValue);
        try {
			etValue.setText(String.valueOf(con.getVariable("ANS")));
		} catch (UndefinedVariableException e) {
			// Never reach here.
		}
        
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (etName.getText().length() < 1) {
					Toast.makeText(NewVariableActivity.this, "You must specify variable name", Toast.LENGTH_SHORT).show();
					return;
				}
				String name = etName.getText().toString();
				if (name.equals("ANS") || name.equals("PI") || name.equals("E")) {
					Toast.makeText(NewVariableActivity.this, "Predefined variable cannot be overrided", Toast.LENGTH_SHORT).show();
					return;
				}
				double value;
				if (etValue.getText().length() < 1)
					value = 0;
				else
					value = Double.valueOf(etValue.getText().toString());
				con.addVariable(name, value);
				finish();
			}
        });
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		finish();
        	}
        });
	}
}
