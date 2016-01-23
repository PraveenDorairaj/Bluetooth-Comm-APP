package com.example.bluetoooth_controller;

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
	
	protected static final int DISCOVERY_REQUEST = 1;
	
	private BluetoothAdapter btAdapter;
	public TextView status;
	public Button connect;
	public Button disconnect;
	public ImageView bluetoothPic;
	public Button forward;
	public Button backward;
	public Button left;
	public Button right;
	public String toastText = "";
	private BluetoothDevice remoteDevice;
	
	// Creating Bluetooth broad cast receiver 
	BroadcastReceiver bluetoothState= new BroadcastReceiver(){
	@Override
	public void onReceive(Context context, Intent intent){
		String prevStateExtra = BluetoothAdapter.EXTRA_PREVIOUS_STATE;
		String stateExtra = BluetoothAdapter.EXTRA_STATE;
		int state = intent.getIntExtra(stateExtra, -1);
		int previousState = intent.getIntExtra(prevStateExtra,-1);
		
		switch(state){
			case(BluetoothAdapter.STATE_TURNING_ON):{
				toastText = "Bluetooth turning on...";
				Toast.makeText(MainActivity.this,  toastText,Toast.LENGTH_SHORT).show();
				break;
			}
			case (BluetoothAdapter.STATE_ON):{
				toastText = "Bluetooth On";
				Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
				setupUI();
				break;
			}
			case(BluetoothAdapter.STATE_TURNING_OFF):{
				toastText = "Bluetooth turing off...";
				Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
				break;
			}
			case(BluetoothAdapter.STATE_OFF):{
				toastText = "Bluetooth Off";
				Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
				setupUI();
				break;
			}
		}
	}
	};

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupUI();
	}
	
	private void setupUI(){
		
		forward = (Button) findViewById (R.id.button1);//button4 not working for some reason
		backward =  (Button) findViewById (R.id.button4);
		left = (Button) findViewById (R.id.button2);
		right = (Button) findViewById (R.id.button3);
		final TextView status = (TextView)findViewById(R.id.textview);
		final Button connect = (Button)findViewById(R.id.button6);
		final Button disconnect = (Button)findViewById(R.id.button5);
		final ImageView bluetoothPic = (ImageView)findViewById(R.id.imageView1);

//-----------------------------------------		
		disconnect.setVisibility(View.GONE);
		bluetoothPic.setVisibility(View.GONE);
//----------------------------------------
		btAdapter = BluetoothAdapter.getDefaultAdapter();
		
		if (btAdapter.isEnabled()){
			String address = btAdapter.getAddress();
			String name = btAdapter.getName();
			String statusText = name + " : " + address;
			status.setText(statusText);
			connect.setVisibility(View.GONE);
			disconnect.setVisibility(View.VISIBLE);
			bluetoothPic.setVisibility(View.VISIBLE);
		} else {
			connect.setVisibility(View.VISIBLE);
			status.setText("Bluetooth is not on");
		}
//-----------------------------------------	
		connect.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {				
				//String actionStateChanged = BluetoothAdapter.ACTION_STATE_CHANGED;
				//String actionRequestEnable = BluetoothAdapter.ACTION_REQUEST_ENABLE;
				//IntentFilter filter = new IntentFilter (actionStateChanged);
			//	registerReceiver(bluetoothState,filter);
				//startActivityForResult(new Intent(actionRequestEnable),0);
				
				//register for discovery events
				String scanModeChanged = BluetoothAdapter.ACTION_SCAN_MODE_CHANGED;
				String beDiscoverable = BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE;
				IntentFilter filter = new IntentFilter(scanModeChanged);
				registerReceiver(bluetoothSate, filter);
				startActivityForResult(new Intent(beDiscoverable),DISCOVERY_REQUEST);
				
				
			}	
		});
		
//------------------------------------------		
		disconnect.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {				
				btAdapter.disable();
				connect.setVisibility(View.VISIBLE);
				disconnect.setVisibility(View.GONE);
				bluetoothPic.setVisibility(View.GONE);
				status.setText("BlueTooth Off");
				
						
			}	
		});
//-----------------------------------------
		forward.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {				
				Toast.makeText(getApplicationContext(), "Robot is going forward", Toast.LENGTH_LONG).show();// TODO Auto-generated method stub			
			}
		});
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
	}// end of setup UI

	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data){
		
		if (requestCode == DISCOVERY_REQUEST){
			Toast.makeText(MainActivity.this, "Discovery in progress...", Toast.LENGTH_SHORT).show();
			setupUI();
			findDevices();
		}
		
	}
	
	private void findDevices(){
		String lastUsedRemoteDevice = getLastUsedRemoteBTDevice();
		
		if(lastUsedRemoteDevice != null){
			toastText = "Checking for known paired devices, namely: " + lastUsedRemoteDevice;
			Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
			
			//see if this device is in a list of current visible, paired devices
			Set<BluetoothDevice> pairedDevices = btAdapter.getondedDevices();
			for(BluetoothDevice pairedDevice : pairedDevice){
				if(pairedDevice.getAdress().equals(lastUsedRemoteDevice)){
					toastText = "Found device: " + pairedDevice.getNAme() + "a" + lastUsedRemoteDevice;
					Toast.makeText(MainActivity.this , toastText, Toast.LENGTH_SHORT).show();
					remoteDevice = pairedDevice;
				}
			}
		}//end if
		
		if(remoteDeivce === null){
			toastText = "Start discovery for remote devices...";
			Toast.makeText(MainActiviy.this, toastText, Toast.LENGTH_SHORT).show();
			//start discovery
			if(btAdapter.startDiscovery()){
				toastText = "Discovery thread started...Scanning for Devices";
				Toast.makeText(MainActiviy.this, toastText, Toast.LENGTH_SHORT).show();
				registerReceiver(discoveryResult, new IntentFilter(BluetoothDevice.ACTION_FOUND));
			}
		}
	}//end of find devices
	
	//Creating broadcast reciever to receive device discovvery
	BroadcastReceiver discoveryResult = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent){
			String remoteDeviceName - intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
			BluetoothDevice remoteDevice;
			remoteDevice = intent.getParselableExtra(BluetoothDevice.EXTRA_DEVICE);
			toastText = "Discovered: " + remoteDeviceName;
			Toast.makeText(MainActiviy.this, toastText, Toast.LENGTH_SHORT).show();
		}
				
	}
	
	
	private String getLastUsedRemoteBTDevice(){
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		String result = profs.getString("Last_REMOTE_DEVICE_ADDRESS",null);
		return result;
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
