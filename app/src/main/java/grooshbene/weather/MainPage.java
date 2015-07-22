package grooshbene.weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;


public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        TextView date = (TextView) findViewById(R.id.date);
        //id로 텍스트 불러오기
        Calendar calendar = Calendar.getInstance();
        //calendar 선언
        int month = calendar.get(Calendar.MONTH)+1;
        //달
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        date.setText(month+"월"+day+"일");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event){
//        switch(keyCode){
//            case KeyEvent.KEYCODE_BACK:
//                finish();
//            default:
//                return false;
//        }
//    }

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
}
