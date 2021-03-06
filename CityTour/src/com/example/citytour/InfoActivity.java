package com.example.citytour;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class InfoActivity extends Activity {

	private ProgressDialog pDialog;
	
	// wikipedia URL
//	private static String url = "http://es.wikipedia.org/w/api.php?format=json&action=parse&page=Templo_de_Debod";
	private String url;
	// JSON nose names
	private static final String TAG_TITLE = "title";
	private static final String TAG_TEXT = "text";
	private static final String TAG_PARSE = "parse";
	private static String TITLE,DATA,TEXT;
	String[] paragraphs,titles;
	TextView titleTextView;
	WebView jsonWebView;
	JSONParser jsonparser = new JSONParser();
	JSONObject jobjParseString,jobjParse,jobjTitle,jobjText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
				
		Intent intent = getIntent();
		
		titleTextView = (TextView)findViewById(R.id.titleTextView);
		jsonWebView = (WebView)findViewById(R.id.JSONwebView);
//		jsonWebView.getSettings().setJavaScriptEnabled(true);
		url = intent.getStringExtra("url");
		new GetData().execute();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info, menu);
		return super.onCreateOptionsMenu(menu);
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
	
	// Async action
		private class GetData extends AsyncTask<String, String, JSONObject>{
			@Override
			protected void onPreExecute(){
				super.onPreExecute();
				// show progress dialog
				pDialog = new ProgressDialog(InfoActivity.this);
				pDialog.setMessage(getResources().getString(R.string.wait));
				pDialog.setCancelable(false);
				pDialog.show();
			}
			@Override
			protected JSONObject doInBackground(String... arg0){
				jobjParseString = jsonparser.makeHttpRequest(url);
				return jobjParseString;
			}
			
			@Override
			protected void onPostExecute(JSONObject json){
				super.onPostExecute(json);
				try {
					jobjParse = jobjParseString.getJSONObject(TAG_PARSE);
					jobjText = jobjParse.getJSONObject(TAG_TEXT);
					TITLE = jobjParse.getString(TAG_TITLE);
					DATA = jobjText.toString();
					paragraphs = DATA.split("<p>");
					titles = DATA.split("<h2>");
					String correctText="";
					if(url.contains("Debod")){
						// caso del Templo de Debod
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[3];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Plaza_de_Esp")){
						// caso de Plaza de España
						TEXT = paragraphs[1]+paragraphs[2];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Reina_Sof")){
						// caso del museo Reina Sofía
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[3]+paragraphs[4]+paragraphs[5];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Museo_del_Pra")){
						// caso del museo del prado
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[3]+paragraphs[4]+paragraphs[5]+paragraphs[6]+paragraphs[7]+paragraphs[8];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Museo_Thy")){
						// caso del museo thyssen
						TEXT = paragraphs[1]+paragraphs[2];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("CaixaForum")){
						// caso del caixaforum
						TEXT = paragraphs[1]+paragraphs[2];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Galdiano")){
						// caso del museo lazaro galdiano
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[3]+paragraphs[4]+paragraphs[5];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Museo_Sorol")){
						// caso del museo sorolla
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[3];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Museo_Arqueol")){
						// caso del museo arqueologico
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[5]+paragraphs[6]+paragraphs[7]+paragraphs[8];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Museo_Nav")){
						// caso del museo naval
						TEXT = paragraphs[1]+paragraphs[2];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Palacio_Real")){
						// caso del palacio real
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[3]+paragraphs[4]+paragraphs[5]+paragraphs[6]+paragraphs[7];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Puerta_del_Sol")){
						// caso de la pueta del sol
						TEXT = paragraphs[1];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Plaza_Mayor")){
						// caso de la plaza mayor
						TEXT = paragraphs[1]+paragraphs[4]+paragraphs[5]+paragraphs[6]+paragraphs[7]+paragraphs[8];
						String aux = TEXT.replace("El nombre de la plaza"," ");
						correctText = aux.replace("<\\" , "<");
					}else if(url.contains("Colegiata_de_San_Isidro")){
						// caso de la colegiata de san isidro
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[3];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Plaza_de_la_Villa")){
						// caso de la plaza de la villa
						TEXT = paragraphs[2]+paragraphs[3]+paragraphs[4];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Puerta_de_Al")){
						// caso de la puerta de alcala
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[3]+paragraphs[4]+paragraphs[5]+paragraphs[6];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Gran")){
						// caso de la gran via
						TEXT = paragraphs[1];
						correctText = TEXT.replace("<\\" , "<");
					}else if(url.contains("Cibeles")){
						// caso de la plaza de cibeles
						TEXT = paragraphs[1]+paragraphs[2]+paragraphs[3]+paragraphs[4];
						correctText = TEXT.replace("<\\" , "<");
					}
					titleTextView.setText(TITLE);
					jsonWebView.loadDataWithBaseURL("", correctText, "text/html; charset=UTF-8", "UTF-8", "");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// dismiss progress dialog
				pDialog.dismiss();		
			}
		}
}
