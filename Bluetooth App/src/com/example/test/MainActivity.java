package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button forward;
	Button backward;
	Button left;
	Button right;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//forward = (Button) findViewById (R.id.button4);//button4 not working for some reason
		backward =  (Button) findViewById (R.id.button1);
		left = (Button) findViewById (R.id.button3);
		right = (Button) findViewById (R.id.button2);

//-----------------------------------------
		/*forward.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Toast.makeText(getApplicationContext(), "Robot is going forward", Toast.LENGTH_LONG).show();// TODO Auto-generated method stub
				
			}
		});*/
//--------------------------------------		
		backward.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Toast.makeText(getApplicationContext(), "Robot is going backward", Toast.LENGTH_SHORT).show();// TODO Auto-generated method stub
				
			}
		});
//-----------------------------------------		
		left.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Toast.makeText(getApplicationContext(), "Robot is going left", Toast.LENGTH_SHORT).show();// TODO Auto-generated method stub
				
			}
		});
//-------------------------------------------	
		right.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Toast.makeText(getApplicationContext(), "Robot is going right", Toast.LENGTH_SHORT).show();// TODO Auto-generated method stub
				
			}
		});
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
}
