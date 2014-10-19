package com.gadgets.compass;

import java.util.List;

import com.gadgets.collection.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class CompassActivity extends Activity implements SensorEventListener   {
	private boolean        mRegisteredSensor;  
    //定义SensorManager  
    private SensorManager  mSensorManager;
	private CompassView _compassView;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compass);
		mRegisteredSensor = false;  
        //取得SensorManager实例  
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); 
        
        _compassView=(CompassView)findViewById(R.id.mycompassView);
	}
	
	@Override  
    protected void onResume()  
    {  
        super.onResume();  
  
        //接受SensorManager的一个列表(Listener)  
        //这里我们指定类型为TYPE_ORIENTATION(方向感应器)  
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);  
  
        if (sensors.size() > 0)  
        {  
            Sensor sensor = sensors.get(0);  
            //注册SensorManager  
            //this->接收sensor的实例  
            //接收传感器类型的列表  
            //接受的频率  
            mRegisteredSensor = mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);  
        }  
    }  
      
    @Override  
    protected void onPause()  
    {  
        if (mRegisteredSensor)  
        {  
            //如果调用了registerListener  
            //这里我们需要unregisterListener来卸载/取消注册  
            mSensorManager.unregisterListener(this);  
            mRegisteredSensor = false;  
        }  
        super.onPause();  
    }  
    //当进准度发生改变时  
    //sensor->传感器  
    //accuracy->精准度  
    @Override  
    public void onAccuracyChanged(Sensor sensor, int accuracy)  
    {  
          
    }  
    // 当传感器在被改变时触发  
    @Override  
    public void onSensorChanged(SensorEvent event)  
    {  
        // 接受方向感应器的类型  
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION)  
        {  
            // 这里我们可以得到数据，然后根据需要来处理  
            float x = event.values[SensorManager.DATA_X];  
              
            _compassView.setDegree(x);  
  
        }  
    }

}
