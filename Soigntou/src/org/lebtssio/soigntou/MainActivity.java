package org.lebtssio.soigntou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	private EditText distance;
	private Button go;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		distance = (EditText) this.findViewById(R.id.kilometreD);
		
		go = (Button) this.findViewById(R.id.go);
		go.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		
		if(distance.getText().toString().isEmpty())
		{
			Toast.makeText(this, "Entrez une distance correcte", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this, "bi : "+distance.getText().toString(), Toast.LENGTH_SHORT).show();
			Intent intentRecherche = new Intent (this, ListePharmacie.class);
			startActivityForResult(intentRecherche, 10);
		}
		
	}
}
