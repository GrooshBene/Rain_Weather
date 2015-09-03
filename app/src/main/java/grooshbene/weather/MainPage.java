package grooshbene.weather;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Calendar;


public class MainPage extends Activity {
    private DrawerLayout mDrawerLayout;
    private Button mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    private LinearLayout mDrawerList;
    TextView currentTemp;
    String[] asdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        TextView date = (TextView) findViewById(R.id.date);
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = (Button)findViewById(R.id.drawerToggle);
        mDrawerList = (LinearLayout) findViewById(R.id.drawer);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        currentTemp = (TextView) findViewById(R.id.current_temp);
        //id로 텍스트 불러오기
        Calendar calendar = Calendar.getInstance();
        //calendar 선언
        int month = calendar.get(Calendar.MONTH) + 1;
        //달
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        date.setText(year+"년"+month + "월" + day + "일");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements max_current = doc.select("location data tmx");
                Elements min_current = doc.select("location data tmn");
                asdf = new String[max_current.size()];
                for(int i=0;i<max_current.size();i++) {
                    asdf[i] = max_current.get(i).text().toString();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentTemp.setText("섭씨 "+asdf[0]+"도");
                    }
                });
            }
        };

        Thread thread = new Thread(task);
        thread.start();
        mDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });


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
    /** Swaps fragments in the main content view */

}
