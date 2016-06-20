package in.dailytalent.www.talentkakhazana;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class PagesnewActivity extends Activity {
    private static final String AD_UNIT_ID = "ca-app-pub-7258515950688289/5693890058";
    private AdView adView;
    ConnectionDetector cd;
    Boolean isInternetPresent = Boolean.valueOf(false);
    WebView webView;

    private class MyWebViewClient extends WebViewClient {
        private MyWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("shankarraopura.blogspot.in")) {
                return false;
            }
            PagesnewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
            return true;
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pages);
        LinearLayout layout = (LinearLayout) findViewById(R.id.adv);
        this.adView = new AdView(this);
        this.adView.setAdSize(AdSize.BANNER);
        this.adView.setAdUnitId(AD_UNIT_ID);
        layout.addView(this.adView);
        this.adView.loadAd(new Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build());
        this.webView = (WebView) findViewById(R.id.webView1);
        this.webView.setWebViewClient(new MyWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        String url = getIntent().getExtras().getString("inone");
        this.cd = new ConnectionDetector(getApplicationContext());
        this.isInternetPresent = Boolean.valueOf(this.cd.isConnectingToInternet());
        if (this.isInternetPresent.booleanValue()) {
            final ProgressDialog dialog = new ProgressDialog(this);
            this.webView.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView view, String url) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                        webView.setDownloadListener(new DownloadListener() {
                            @Override
                            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));

                                startActivity(i);
                            }
                        });
                    }
                }
            });
            dialog.setMessage("Loading..Please wait.");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            this.webView.loadUrl(url);
        } else {
            Toast.makeText(getApplicationContext(), "You do not have Internet Connection Plz Check Internet Connection.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }
        ((TextView) findViewById(R.id.title)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PagesnewActivity.this.gohome();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.webView.canGoBack()) {
            return super.onKeyDown(keyCode, event);
        }
        this.webView.goBack();
        return true;
    }

    public void gohome() {
        startActivity(new Intent(this, MainActivity.class));
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
