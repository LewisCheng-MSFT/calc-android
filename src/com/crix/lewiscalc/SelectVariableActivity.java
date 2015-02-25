package com.crix.lewiscalc;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SelectVariableActivity extends ListActivity {
	ArrayList<HashMap<String, String>> varList;
	EvalContext con = EvalContext.getSingleton();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		varList = new ArrayList<HashMap<String, String>>();
		
		for (String name : con.getVariableList()) {
			HashMap<String, String> map = new HashMap<String, String>();
			try {
				map.put("name", name);
				map.put("value", String.valueOf(con.getVariable(name)));
			} catch (UndefinedVariableException e) {
				// Never reach here.
			}
			varList.add(map);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		varList,
        		R.layout.list_item,
        		new String[] { "name", "value" },
        		new int[] { R.id.tvName, R.id.tvValue }
        );
        setListAdapter(adapter);
	}
	
	private void leaveActivity(int position) {
		Intent intent = getIntent();
		intent.putExtra("name", position == -1 ? "null" : varList.get(position).get("name"));
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
