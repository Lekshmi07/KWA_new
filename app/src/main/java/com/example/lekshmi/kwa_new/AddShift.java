package com.example.lekshmi.kwa_new;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddShift extends AppCompatActivity {

    DatePicker pickerDate;
    public TimePicker pickerTime;
    Button buttonSetAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);
        try {

            Calendar now = Calendar.getInstance();
            pickerDate = (DatePicker) findViewById(R.id.pickerdate);
            pickerTime = (TimePicker) findViewById(R.id.pickertime);

            pickerDate.init(
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH),
                    null);

            pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
            pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));

            buttonSetAlarm = (Button) findViewById(R.id.setalarm);
            buttonSetAlarm.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Calendar current = Calendar.getInstance();

                    Calendar cal = Calendar.getInstance();
                    cal.set(pickerDate.getYear(),
                            pickerDate.getMonth(),
                            pickerDate.getDayOfMonth(),
                            pickerTime.getCurrentHour(),
                            pickerTime.getCurrentMinute(),
                            00);


                    AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


                    Context context = getApplicationContext();
                    Intent myIntent = new Intent(context, AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);

                    manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                    startActivity(myIntent);
                }
            });
        }
     catch (Exception e)
     {
         Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
     }
    }
}
