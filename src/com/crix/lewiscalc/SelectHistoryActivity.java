package com.crix.lewiscalc;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectHistoryActivity extends ListActivity {
	EvalContext con = EvalContext.getSingleton();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.getHistory());
        setListAdapter(adapter);
	}
	
	private void leaveActivity(int position) {
		Intent intent = getIntent();
		intent.putExtra("expr", position == -1 ? "null" : MainActivity.getHistory().get(position));
		setResult(0, intent);
		finish();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		leaveActivity(position);
	}
	
	@Override
	public void onBackPressed() {
		leaveActivity(-1);
	}
}
