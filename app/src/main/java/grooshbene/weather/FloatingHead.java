package grooshbene.weather;


import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class FloatingHead extends Service {
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;

    private ImageView mChatHeadsView;

    private float mTouchX, mTouchY;
    private int mViewX, mViewY;

    private View.OnTouchListener mViewTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    mTouchY = event.getRawY();
                    mViewX = mParams.x;
                    mViewY = mParams.y;
                    break;

                case MotionEvent.ACTION_MOVE:
                    int x = (int) (event.getRawX() - mTouchX);
                    int y = (int) (event.getRawY() - mTouchY);

                    mParams.x = mViewX + x;
                    mParams.y = mViewY + y;

                    mWindowManager.updateViewLayout(mChatHeadsView, mParams);
                    break;
            }

            return true;
        }
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mChatHeadsView = new ImageView(this);
        mChatHeadsView.setOnTouchListener(mViewTouchListener);

        mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE, //TouchEvent 받음
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, //KeyEvent, TouchEvent를 Child(앱)로 넘김
                PixelFormat.TRANSLUCENT );
        mParams.gravity = Gravity.LEFT | Gravity.TOP;

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mChatHeadsView, mParams);

    }
}