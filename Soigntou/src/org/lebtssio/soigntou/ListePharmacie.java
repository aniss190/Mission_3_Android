package org.lebtssio.soigntou;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListePharmacie extends Activity{
	ListView list;
	TextView ver;
	TextView name;
	TextView api;
	ArrayList<HashMap<String, String>> listPharmacie = new ArrayList<HashMap<String, String>>();
	//URL to get JSON Array
	private static String url = "http://anissmadani.shost.ca/cours/mission3/liste_pharmacie.json";
	//JSON Node Names
	private static final String TAG_OBJ = "fields";
	private static final String TAG_NOM = "rslongue";
	//private static final String TAG_NAME = "name";
	//private static final String TAG_API = "api";
	JSONArray fields = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_pharmacie);
		listPharmacie = new ArrayList<HashMap<String, String>>();

		
		new JSONParse().execute();
	}
	private class JSONParse extends AsyncTask<String, String, JSONObject> {
		private ProgressDialog pDialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			ver = (TextView)findViewById(R.id.nom);
			name = (TextView)findViewById(R.id.name);
			api = (TextView)findViewById(R.id.api);
			pDialog = new ProgressDialog(ListePharmacie.this);
			pDialog.setMessage("Getting Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected JSONObject doInBackground(String... args) {
			JSONParser jParser = new JSONParser();
			// Getting JSON from URL
			JSONObject json = jParser.getJSONFromUrl(url);
			return json;
		}
		@Override
		protected void onPostExecute(JSONObject json) {
			pDialog.dismiss();
			try {
				// Getting JSON Array from URL
				fields = json.getJSONArray(TAG_OBJ);
				for(int i = 0; i < fields.length(); i++){
					JSONObject c = fields.getJSONObject(i);
					// Storing  JSON item in a Variable
					String nom = c.getString(TAG_NOM);
					//String name = c.getString(TAG_NAME);
					//String api = c.getString(TAG_API);
					// Adding value HashMap key => value
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(TAG_NOM, nom);
					//map.put(TAG_NAME, name);
					//map.put(TAG_API, api);
					listPharmacie.add(map);
					list=(ListView)findViewById(R.id.list);
					ListAdapter adapter = new SimpleAdapter(ListePharmacie.this, listPharmacie,
							R.layout.list_v,
							new String[] { TAG_NOM}, new int[] {
							R.id.nom});
					list.setAdapter(adapter);
					list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							Toast.makeText(ListePharmacie.this, "You Clicked at "+listPharmacie.get(+position).get("name"), Toast.LENGTH_SHORT).show();
						}
					});
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}

