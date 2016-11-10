package prateekpawar.proximity.com.proximitywakelock;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.PowerManager;

/**
 * Created by root on 10/11/16.
 */
public class SensorService extends Service implements SensorEventListener {

    private Sensor proximitySensor;
    private SensorManager sensorManager;
    private PowerManager.WakeLock mWakeLock;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        int flags = PowerManager.SCREEN_BRIGHT_WAKE_LOCK| PowerManager.ACQUIRE_CAUSES_WAKEUP  ;
        mWakeLock = powerManager.newWakeLock(flags, "wake_up_tag");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {
        final long start = System.currentTimeMillis();

        //Toast.makeText(this, "event"+event.values[0], Toast.LENGTH_LONG).show();



        wakeUp();
    }

    private void wakeUp() {
        mWakeLock.acquire();
        mWakeLock.release();

    }

    @Override
    public void onDestroy() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.proximitySensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        this.sensorManager.registerListener(this, this.proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);




        return Service.START_STICKY;
    }
}