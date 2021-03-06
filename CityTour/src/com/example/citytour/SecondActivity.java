package com.example.citytour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	
	Button goButton;
	ListView listView;
	TextView textView;
	ArrayAdapter<String> adapter;
	String[] zonas,cosasQueVer;
	int[] indices;
	int indexZonas,numZonas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		cosasQueVer = getResources().getStringArray(R.array.array_zonas_madrid);
		listView = (ListView)findViewById(R.id.listaZonas);
		goButton = (Button)findViewById(R.id.goButton2);
		textView = (TextView)findViewById(R.id.textoQueVer);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,cosasQueVer);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listView.setAdapter(adapter);
		
		// inicializamos numero de zonas seleccionadas a cero
		numZonas=0;
		
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
        String ciudad = b.getString("ciudad");
        Toast.makeText(getBaseContext(), "Selected city: "+ciudad, Toast.LENGTH_SHORT).show();
        
        goButton.setOnClickListener(new View.OnClickListener(){
        	@Override
        	public void onClick(View v){
        		String selected = "";
        		int countChoice = listView.getCount();
        		SparseBooleanArray checked = listView.getCheckedItemPositions();
        		for(int i=0; i < countChoice; i++){
        			if(checked.get(i)){
        				selected+=listView.getItemAtPosition(i).toString()+"\n";
        			}
        		}
        		zonas = selected.split("\n");
        		showZonasSeleccionadas(v);
        	}
        });
                
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	public void showZonasSeleccionadas(View view){
		Intent intent = new Intent(this, SelectedZonesActivity.class);
		intent.putExtra("zonas", zonas);
		startActivity(intent);
	}
}
