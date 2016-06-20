package in.dailytalent.www.talentkakhazana;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends Activity {
    private static final String AD_UNIT_ID = "ca-app-pub-7258515950688289/5693890058";
    private AdView adView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.category);
        this.adView = new AdView(this);
        this.adView.setAdSize(AdSize.BANNER);
        this.adView.setAdUnitId(AD_UNIT_ID);
        layout.addView(this.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        ((TextView) findViewById(R.id.textView4)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.goweb();
            }
        });
        ((TextView) findViewById(R.id.textView5)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.goweb1();
            }
        });
        ((TextView) findViewById(R.id.textView6)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.goweb2();
            }
        });
        ((TextView) findViewById(R.id.textView7)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.goweb3();
            }
        });
        ((TextView) findViewById(R.id.TextView02)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.goweb4();
            }
        });
        ((TextView) findViewById(R.id.TextView04)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.goapps();
            }
        });
        ((TextView) findViewById(R.id.TextView05)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.gofb();
            }
        });
        ((TextView) findViewById(R.id.TextView06)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.sharenow();
            }
        });
        ((TextView) findViewById(R.id.TextView07)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.sendmail();
            }
        });
    }

    public void sharenow() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("android.intent.extra.SUBJECT", "Talent Ka Khazana : Educational Android App");
        intent.putExtra("android.intent.extra.TEXT", "Talent Ka Khazana : Educational Android App. It is amazing app for GK improvement and other Activity . Its created by Talent Ka Khazana-Shankar yadav a well known Android app developer. You may find it at \n https://play.google.com/store/apps/developer?id=SHANKARRAOPURA");
        startActivity(Intent.createChooser(intent, "How do you want to share?"));
    }

    public void goapps() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://search?q=pub:SHANKARRAOPURA"));
        startActivity(intent);
    }

    public void gofb() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://www.facebook.com/kushi.yadav.9619"));
        startActivity(intent);
    }

    public void sendmail() {
        Intent i = new Intent("android.intent.action.SEND");
        i.setType("message/rfc822");
        i.putExtra("android.intent.extra.EMAIL", new String[]{"shankarraopura2016@gmail.com"});
        i.putExtra("android.intent.extra.SUBJECT", "Feedback from Talent Ka Khazana App");
        i.putExtra("android.intent.extra.TEXT", "Please write your feedback here.......");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goweb() {
        Intent intent = new Intent(this, PagesnewActivity.class);
        intent.putExtra("inone", "http://shankarraopura.blogspot.com/p/daily-gk.html");
        startActivity(intent);
    }

    public void goweb1() {
        Intent intent = new Intent(this, PagesnewActivity.class);
        intent.putExtra("inone", "http://shankarraopura.blogspot.com/p/background.html");
        startActivity(intent);
    }

    public void goweb2() {
        Intent intent = new Intent(this, PagesnewActivity.class);
        intent.putExtra("inone", "http://shankarraopura.blogspot.com/p/khana-khazana.html");
        startActivity(intent);
    }

    public void goweb3() {
        Intent intent = new Intent(this, PagesnewActivity.class);
        intent.putExtra("inone", "http://shankarraopura.blogspot.com/p/daily-new-png.html");
        startActivity(intent);
    }

    public void goweb4() {
        Intent intent = new Intent(this, PagesnewActivity.class);
        intent.putExtra("inone", "http://shankarraopura.blogspot.com/p/job-alert.html");
        startActivity(intent);
    }

    public void onResume() {
        super.onResume();
        if (this.adView != null) {
            this.adView.resume();
        }
    }

    public void onPause() {
        if (this.adView != null) {
            this.adView.pause();
        }
        super.onPause();
    }

    public void onDestroy() {
        if (this.adView != null) {
            this.adView.destroy();
        }
        super.onDestroy();
    }
}
