package sample.scijoker.com.urclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.scijoker.urclient.OnResponseListener;

public class DemoActivity extends AppCompatActivity {
    private Button btnAuth;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        initUI();
        initListeners();
    }

    private void initUI() {
        btnAuth = (Button) findViewById(R.id.btn_auth);
        webView = (WebView) findViewById(R.id.webview);
    }

    private void initListeners() {
        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Requestor.makeAuthorization(onAuthResponseListener);
            }
        });
    }

    private OnResponseListener onAuthResponseListener = new OnResponseListener() {
        @Override
        public void onResponseSuccessful(Object response) {
            btnAuth.setVisibility(View.GONE);
            webView.loadDataWithBaseURL("", (String) response, "", "utf-8", "");
        }

        @Override
        public void onResponseFailed(int errorCode, String exceptionInfo) {
            webView.loadDataWithBaseURL("", "Error", "", "utf-8", "");
        }
    };
}
