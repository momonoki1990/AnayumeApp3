package com.example.o2.anayumeapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


/**
 * Created by user on 2018/02/07.
 */

public class MyPreferenceFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    public MyPreferenceFragment() {
    }

    private ListPreference lp;
    private AlarmManager am;
    private PendingIntent pending;
    private int requestCode = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        /*
        lp = (ListPreference)findPreference("list_pref");
        lp.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(){
            public boolean onPreferenceChange(Preference preference, Object newValue){
                if(newValue != null){
                    ListPreference listpref = (ListPreference) preference;
                    int listId = listpref.findIndexOfValue((String) newValue);
                    CharSequence[] entries = listpref.getEntries();
                    preference.setSummary(entries[listId]);
                    return true;
                }
                return false;
            }
        }); */
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        Preference p = findPreference("list_pref");
        String value = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("list_pref", "default_value");
        int num = Integer.parseInt(value);

        if (num == 0) {
            p.setSummary("通知しない");
        } else if (num == 12) {
            p.setSummary("1年後");
        } else {
            p.setSummary(value + "ヵ月後");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.i("onSharedPreferenceChang", key);
        if (key.equals("list_pref")) {
            Preference p = findPreference(key);
            String value = sharedPreferences.getString(key, "default_value");
            int num = Integer.parseInt(value);

            if (num == 0) {
                p.setSummary("通知しない");
                cancelAlarm();
            } else if (num == 12) {
                p.setSummary("1年後");
                setAlarm(num);
            } else {
                p.setSummary(value + "ヵ月後");
                setAlarm(num);
            }
        }
    }

    public void setAlarm(int date) {
        // 時間をセットする
        Calendar calendar = Calendar.getInstance();
        // Calendarを使って現在の時間をミリ秒で取得
        calendar.setTimeInMillis(System.currentTimeMillis());
        // 5秒後に設定
        calendar.add(Calendar.MONTH, date);

        //明示的なBroadCast
        Intent intent = new Intent(getActivity().getApplicationContext(),
                AlarmBroadcastReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(
                getActivity().getApplicationContext(), 0, intent, 0);

        // アラームをセットする
        AlarmManager am = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        if (am != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                am.setAlarmClock(new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), null), pending);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
            }
        }
    }

    public void cancelAlarm() {
        AlarmManager aManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getActivity().getApplicationContext(),
                AlarmBroadcastReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(
                getActivity().getApplicationContext(), 0, intent, 0);
        aManager.cancel(pIntent);
//            alarmManager.cancel(pi);
        pIntent.cancel();
    }



        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        // 5秒の遅れ
        //calendar.add(Calendar.SECOND, 5);

        Intent intent = new Intent(getActivity().getApplicationContext(), AlarmBroadcastReceiver.class);
        intent.putExtra("RequestCode", requestCode);

        pending = PendingIntent.getBroadcast(
                getActivity().getApplicationContext(), requestCode, intent, 0);

        //getApplicationContext()→getActivity().getApplicationContext()

        // アラームをセットする
        am = (AlarmManager) (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

        if (am != null) {
            // 1分で 繰り返し
            am.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), 60 * 1000, pending);

            // トーストで設定されたことをを表示
            //Toast.makeText(getActivity().getApplicationContext(),
              //      "alarm start", Toast.LENGTH_SHORT).show();

        }*/
    }