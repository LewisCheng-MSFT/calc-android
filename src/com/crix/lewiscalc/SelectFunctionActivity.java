package com.crix.lewiscalc;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectFunctionActivity extends ListActivity {
	List<String> nameList;
	EvalContext con = EvalContext.getSingleton();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		nameList = con.getFunctionList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        setListAdapter(adapter);
	}
	
	private void leaveActivity(int position) {
		Intent intent = getIntent();
		intent.putExtra("name", position == -1 ? "null" : nameList.get(position));
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
