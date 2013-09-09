package com.example.shaker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity implements SensorEventListener {
    private float mLastX, mLastY, mLastZ;
    private static final double nbElements = 30;
    private boolean mInitialized; private SensorManager mSensorManager; private Sensor mAccelerometer; private final float NOISE = (float) 2.0;
    private long now = 0;
    private long time = 0;
    private int temp = 0;
    private static String KEY_SUCCESS = "success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /** Called when the activity is first created. */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInitialized = false;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        TextView tvX= (TextView)findViewById(R.id.x_axis);
        TextView tvY= (TextView)findViewById(R.id.y_axis);
        TextView tvZ= (TextView)findViewById(R.id.z_axis);
        TextView value = (TextView)findViewById(R.id.value);
        EditText inputHost = (EditText)findViewById(R.id.host);
        String inputString = "";
        String  androidId  = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);


        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            if (!mInitialized) {
                mLastX = x;
                mLastY = y;
                mLastZ = z;
                tvX.setText("null");
                tvY.setText("0.0");
                tvZ.setText("0.0");
                value.setText("0.0");
                mInitialized = true;
            } else {

                /*
                    hitung delta perpindahn axix x, y, z

                 */

                float deltaX = Math.abs(mLastX - x);
                float deltaY = Math.abs(mLastY - y);
                float deltaZ = Math.abs(mLastZ - z);

                if (deltaX < NOISE) deltaX = (float)0.0;
                if (deltaY < NOISE) deltaY = (float)0.0;
                if (deltaZ < NOISE) deltaZ = (float)0.0;

                mLastX = x;
                mLastY = y;
                mLastZ = z;

                /*
                    hitung refresh rate sensor
                    untuk dapatin berapa frequency nya
                 */
                long tS;

                // Get timestamp of the event
                tS = sensorEvent.timestamp;

                // Get the mean frequency for "nbElements" (=30) elements
                if (now != 0) {
                    temp++;
                    if (temp == nbElements) {
                        time = tS - now;
                        value.setText(Double.toString(nbElements * 1000000000 / time));
                        temp = 0;
                    }
                }

                // To set up now on the first event and do not change it while we do not have "nbElements" events
                if (temp == 0) {
                    now = tS;
                }

                //tampilkan delta axix x, y dan z
                tvX.setText(Float.toString(deltaX));
                tvY.setText(Float.toString(deltaY));
                tvZ.setText(Float.toString(deltaZ));

                // jika delta x atau y atau z lebih besar dari 5
                // kirim data ke server
                if(deltaX > 5 || deltaY > 5 || deltaZ > 5){
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;

                    CharSequence text = "";
                    inputString = inputHost.getText().toString();
                    if(inputString.matches("")){
                        text = "Please insert Server's IP !";
                    }
                    else{
                        UserFunctions userFunction = new UserFunctions();
                        doJson requestJson = new doJson();


                        requestJson.execute(inputString, androidId, Float.toString(deltaX) ,Float.toString(deltaY), Float.toString(deltaZ));

                        //text = "inputString "+inputString+" androidId "+androidId+" "+Float.toString(deltaX) +" "+ Float.toString(deltaY) +" "+ Float.toString(deltaZ);

                        JSONObject json = null;
                        try {
                            json = requestJson.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        text = androidId + " Sent Request!";


                        /*
                           periksa balasan dari server apakah proses input berhasil
                        */

                        // check for login response
                        /*
                        try {
                            if (json.getString(KEY_SUCCESS) != null) {
                                String res = json.getString(KEY_SUCCESS);
                                if(Integer.parseInt(res) == 1){

                                    text = androidId + " Sent Request!";

                                    finish();

                                }else{
                                    // Error in login
                                    text = "Gagal menambah data";
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        */
                    }

                    //tampilkan pesan
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        }
    }

    @Override

    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume() {

        super.onResume();

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    protected void onPause() {

        super.onPause();

        mSensorManager.unregisterListener(this);

    }
}
