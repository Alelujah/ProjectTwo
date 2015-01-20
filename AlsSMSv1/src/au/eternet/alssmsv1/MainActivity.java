package au.eternet.alssmsv1;

import android.telephony.SmsManager;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/** this version sends an sms */
public class MainActivity extends Activity {
	
	/* global vars */
	EditText mobileNo;
	EditText msgTxt;
	Button smsButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /* get ui input and validate */
        
        //ui_txt_mob, ui_txt_msg, btn_txt_sms
        smsButton = (Button) findViewById(R.id.btn_txt_sms);
        mobileNo = (EditText) findViewById(R.id.ui_txt_mob);
        msgTxt = (EditText) findViewById(R.id.ui_txt_msg);
        
        /* button listener with anonymous method */
        smsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String mobile = mobileNo.getText().toString();
				String message = msgTxt.getText().toString();
				
				/* validate mobile no & msg entered*/
				
				if (mobile.length()>9 && message.length()>4) 
				{
					/* send SMS message to mobile */
					sendSMS(mobile, message);
				}
				else
				{
					/* send temporary message to ui */
					Toast.makeText(getBaseContext(),
							"Need to enter number > 9 & message > 4", 
							Toast.LENGTH_SHORT).show();
				}
				
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
    
    /* send sms without monitoring */
    private void sendSMS(String mobile, String message)
    {
    	/* context, requestCode, intent, flags, options */
    	PendingIntent pi = PendingIntent.getActivity(this, 0,
    			new Intent(this, MainActivity.class), 0);
    	/* Use android.telephonySmsManager  .gsm.smsManager deprecated */
    	SmsManager sms = SmsManager.getDefault();  //static method
    	sms.sendTextMessage(mobile,  null,  message,  pi,  null);
    }	

    
    

}
