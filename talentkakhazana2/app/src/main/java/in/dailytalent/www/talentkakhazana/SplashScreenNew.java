package in.dailytalent.www.talentkakhazana;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

@SuppressLint({"HandlerLeak"})
public class SplashScreenNew extends Activity {
    private static final long DELAY = 3000;
    private static final int MSG_CONTINUE = 1234;
    @SuppressLint({"HandlerLeak"})
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SplashScreenNew.MSG_CONTINUE /*1234*/:
                    SplashScreenNew.this._continue();
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle args) {
        super.onCreate(args);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.splash);
        this.mHandler.sendEmptyMessageDelayed(MSG_CONTINUE, DELAY);
    }

    protected void onDestroy() {
        this.mHandler.removeMessages(MSG_CONTINUE);
        super.onDestroy();
    }

    private void _continue() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
