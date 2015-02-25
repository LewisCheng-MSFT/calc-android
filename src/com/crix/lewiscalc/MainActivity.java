package com.crix.lewiscalc;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etInput;
	private TextView tvResult;
	
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	private Button btn9;
	
	private Button btnLParen;
	private Button btnRParen;
	private Button btnPlus;
	private Button btnMinus;
	private Button btnTime;
	private Button btnDivide;
	private Button btnEqual;
	
	private Button btnClear;
	private Button btnBack;
	
	private Button btnPower;
	private Button btnFact;
	private Button btnMod;
	private Button btnComma;
	private Button btnExp;
	private Button btnAns;
	private Button btnFun;
	private Button btnVar;
	private Button btnDot;
	
	private EvalContext con = EvalContext.getSingleton();
	
	private enum SelectVariableOperations {
		ADD,
		DELETE,
		STORE
	};
	
	private SelectVariableOperations operation = SelectVariableOperations.ADD;
	private boolean untouchedAfterCalculation = false;
	
	private static List<String> history = new ArrayList<String>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        history.clear();
        
        etInput = (EditText)findViewById(R.id.etInput);
        etInput.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				untouchedAfterCalculation = false;
			}
        });
        tvResult = (TextView)findViewById(R.id.tvResult);
        tvResult.setText("0.0");
        
        btn0 = (Button)findViewById(R.id.btn0);
        btn0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("0");
			}
        });
        
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("1");
			}
        });
        
        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("2");
			}
        });
        
        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("3");
			}
        });
        
        btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("4");
			}
        });
        
        btn5 = (Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("5");
			}
        });
        
        btn6 = (Button)findViewById(R.id.btn6);
        btn6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("6");
			}
        });
        
        btn7 = (Button)findViewById(R.id.btn7);
        btn7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("7");
			}
        });
        
        btn8 = (Button)findViewById(R.id.btn8);
        btn8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("8");
			}
        });
        
        btn9 = (Button)findViewById(R.id.btn9);
        btn9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("9");
			}
        });
        
        btnLParen = (Button)findViewById(R.id.btnLParen);
        btnLParen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("(");
			}
        });
        
        btnRParen = (Button)findViewById(R.id.btnRParen);
        btnRParen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString(")");
			}
        });
        
        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("ANS+", "+");
			}
        });
        
        btnMinus = (Button)findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("ANS-", "-");
			}
        });
        
        btnTime = (Button)findViewById(R.id.btnTime);
        btnTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("ANS*", "*");
			}
        });
        
        btnDivide = (Button)findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("ANS/", "/");
			}
        });
        
        btnEqual = (Button)findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        Tokenizer tkz = new Tokenizer(etInput.getText().toString());
		        Evaluator eval = new Evaluator(tkz, con);
		        
		        try {
		        	double result = eval.evaluate();
		        	con.setVariable("ANS", result);
		        	tvResult.setText(String.valueOf(result));
		        	history.add(etInput.getText().toString());
		        	untouchedAfterCalculation = true;
		        } catch (Exception e) {
		        	Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		        }
			}
        });
        
        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clear();
			}
        });
        
        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				back();
			}
        });
        
        btnPower = (Button)findViewById(R.id.btnPower);
        btnPower.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("ANS^", "^");
			}
        });
        
        btnFact = (Button)findViewById(R.id.btnFact);
        btnFact.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("ANS!", "!");
			}
        });
        
        btnMod = (Button)findViewById(R.id.btnMod);
        btnMod.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("ANS%", "%");
			}
        });
        
        btnComma = (Button)findViewById(R.id.btnComma);
        btnComma.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString(",");
			}
        });
        
        btnExp = (Button)findViewById(R.id.btnExp);
        btnExp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("e");
			}
        });
        
        btnAns = (Button)findViewById(R.id.btnAns);
        btnAns.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString("ANS");
			}
        });
        
        btnFun = (Button)findViewById(R.id.btnFun);
        btnFun.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SelectFunctionActivity.class);
				startActivityForResult(intent, 2);
			}
        });

        btnVar = (Button)findViewById(R.id.btnVar);
        btnVar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				operation = SelectVariableOperations.ADD;
				Intent intent = new Intent(MainActivity.this, SelectVariableActivity.class);
				startActivityForResult(intent, 1);
			}
        });
        
        btnDot = (Button)findViewById(R.id.btnDot);
        btnDot.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				putString(".");
			}
        });
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String name;
		String expr;
		
		switch (requestCode) {
		case 1:
			name = data.getStringExtra("name");
			if (name.equals("null"))
				return;
			switch (operation) {
			case ADD:
				putString(name);
				break;
			case DELETE:
				if (name.equals("ANS") || name.equals("PI") || name.equals("E")) {
					Toast.makeText(MainActivity.this, "Predefined variable cannot be removed", Toast.LENGTH_SHORT).show();
					return;
				}
				try {
					con.removeVariable(name);
				} catch (UndefinedVariableException e) {
					// Never reach here.
				}
				break;
			case STORE:
				if (name.equals("ANS") || name.equals("PI") || name.equals("E")) {
					Toast.makeText(MainActivity.this, "Predefined variable cannot be assigned", Toast.LENGTH_SHORT).show();
					return;
				}
				try {
					double ANS = con.getVariable("ANS");
					con.setVariable(name, ANS);
				} catch (UndefinedVariableException e) {
					// Never reach here.
				}
				break;
			}
			break;
		case 2:
			name = data.getStringExtra("name");
			if (name.equals("null"))
				return;
			putString(name + '(');
			break;
		case 3:
			expr = data.getStringExtra("expr");
			if (expr.equals("null"))
				return;
			setString(expr);
			untouchedAfterCalculation = false;
			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		
		switch (item.getItemId()) {
		case 1:
			intent = new Intent(MainActivity.this, NewVariableActivity.class);
			startActivity(intent);
			break;
		case 2:
			operation = SelectVariableOperations.DELETE;
			intent = new Intent(MainActivity.this, SelectVariableActivity.class);
			startActivityForResult(intent, 1);
			break;
		case 3:
			operation = SelectVariableOperations.STORE;
			intent = new Intent(MainActivity.this, SelectVariableActivity.class);
			startActivityForResult(intent, 1);
			break;
		case 4:
			intent = new Intent(MainActivity.this, SelectHistoryActivity.class);
			startActivityForResult(intent, 3);
			break;
		case 5:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Help")
				.setMessage("LewisCalc v1.1 By Lewis Cheng (2011.12.27)")
				.setCancelable(true)
				.setPositiveButton("OK", null)
				.create()
				.show();
			break;
		case 6:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, 1, Menu.NONE, "New Var");
    	menu.add(Menu.NONE, 2, Menu.NONE, "Delete Var");
		menu.add(Menu.NONE, 3, Menu.NONE, "Store Var");
		menu.add(Menu.NONE, 4, Menu.NONE, "History");
		menu.add(Menu.NONE, 5, Menu.NONE, "Help");
		menu.add(Menu.NONE, 6, Menu.NONE, "Exit");
		return super.onCreateOptionsMenu(menu);
    }
    
    private void putString(String afterTouch, String beforeTouch)
    {
    	if (untouchedAfterCalculation) {
			setString(afterTouch);
			untouchedAfterCalculation = false;
    	} else {
			addString(beforeTouch);
    	}
    }
    
    private void putString(String text)
    {
    	putString(text, text);
    }
    
    private void addString(String c) {
    	int start = etInput.getSelectionStart();
    	etInput.getText().insert(start, c);
    }
    
    private void setString(String c) {
    	etInput.setText(c);
    	etInput.setSelection(c.length());
    }
    
    private void clear() {
    	untouchedAfterCalculation = false;
    	etInput.setText("");
    	tvResult.setText("0.0");
    }
    
    private void back() {
    	untouchedAfterCalculation = false;
    	int start = etInput.getSelectionStart();
    	if (start == 0)
    		return;
    	etInput.getText().delete(start - 1, start);
    }
    
    public static List<String> getHistory() {
    	return history;
    }
}