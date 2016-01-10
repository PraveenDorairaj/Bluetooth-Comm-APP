package com.example.test;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private BluetoothAdapter btAdapter;
	public TextView status;
	public Button connect;
	public Button disconnect;
	public ImageView bluetoothPic;
	public Button forward;
	public Button backward;
	public Button left;
	public Button right;
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupUI();
	}
	
	private void setupUI(){
		
		//forward = (Button) findViewById (R.id.button4);//button4 not working for some reason
		backward =  (Button) findViewById (R.id.button1);
		left = (Button) findViewById (R.id.button3);
		right = (Button) findViewById (R.id.button2);
		final TextView status = (TextView)findViewById(R.id.blutoothButton);
		final Button connect = (Button)findViewById(R.id.Connect);
		final Button disconnect = (Button)findViewById(R.id.Disconnect);
		final ImageView bluetoothPic = (ImageView)findViewById(R.id.BluetoothPicture);

//-----------------------------------------		
		connect.setVisibility(View.GONE);
		disconnect.setVisibility(View.GONE);
		bluetoothPic.setVisibility(View.GONE);
//----------------------------------------
		btAdapter = BluetoothAdapter.getDefaultAdapter();
		
		if (btAdapter.isEnabled()){
			String address = btAdapter.getAddress();
			String name = btAdapter.getName();
			String statusText = name + " : " + address;
			status.setText(statusText);
		} else {
			connect.setVisibility(View.VISIBLE);
			status.setText("Bluetooth is not on");
		}
//-----------------------------------------	
		connect.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {				
				String actionStateChanged = BluetoothAdapter.ACTION_STATE_CHANGED;
				String actionRequestEnable = BluetoothAdapter.ACTION_REQUEST_ENABLE;
				IntentFilter filter = new IntentFilter (actionStateChanged);
				startActivityForResult(new Intent(actionRequestEnable),0);
			}	
		});
		
//------------------------------------------		
		disconnect.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {				
			}	
		});
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
