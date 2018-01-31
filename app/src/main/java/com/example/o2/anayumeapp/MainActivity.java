package com.example.o2.anayumeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * メイン画面に関連するクラス
 * MainActivity
 */
public class MainActivity extends Activity {

    private EditText mEditText01Dream;        // 夢

    private Button mButton01Regist;             // 登録ボタン

    private Intent intent;                      // インテント

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();        // 各部品の結びつけ処理
        init();             //初期値設定

        // 登録ボタン押下時処理
        mButton01Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // キーボードを非表示
                InputMethodManager inputMethodManager =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                // DBに登録
                saveList();
            }
        });
    }


    /**
     * 各部品の結びつけ処理
     * findViews()
     */
    private void findViews() {

        mEditText01Dream = (EditText) findViewById(R.id.editText01Dream);   // 夢
        mButton01Regist = (Button) findViewById(R.id.button01Regist);           // 登録ボタン
    }
    /**
     * 初期値設定 (EditTextの入力欄は空白、※印は消す)
     * init()
     */
    private void init() {
        mEditText01Dream.setText("");
        mEditText01Dream.requestFocus();      // フォーカスを品名のEditTextに指定
    }

    /**
     * EditTextに入力したテキストをDBに登録
     * saveDB()
     */
    private void saveList() {
        // 各EditTextで入力されたテキストを取得
        String strDream = mEditText01Dream.getText().toString();

        // デフォルトのCalendarオブジェクト
        Calendar cal = Calendar.getInstance();
        // Calendarクラスによる現在時表示
        String strDate = cal.get(Calendar.YEAR) + "/"
                + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE);

        if (strDream.equals("")) {
            Toast.makeText(MainActivity.this, "入力してください。",
                    Toast.LENGTH_SHORT).show();
        }  else {        // EditTextが全て入力されている場合
            // DBへの登録処理
            DBAdapter dbAdapter = new DBAdapter(this);
            dbAdapter.openDB();                    // DBの読み書き
            dbAdapter.saveDB(strDate, strDream);   // DBに登録
            dbAdapter.closeDB();                   // DBを閉じる

            init();     // 初期値設定

            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        }
    }

}

/*
// メニュー未使用
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // メニュー未使用
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
 */