package com.example.rain.webviewdemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private EditText editText;
    private Button go;
    private String myUrl;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.urlEdit);
        webView = (WebView) findViewById(R.id.webView);
        go = (Button) findViewById(R.id.webBtn);

        sharedPreferences = getSharedPreferences("URLdata", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        myUrl = sharedPreferences.getString("url", null);
        init();


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUrl = "http://" + editText.getText().toString();
                editor.putString("url", myUrl);
                editor.commit();


                webView.loadUrl(myUrl);

                webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        // 返回true则表明使用的是WebView
                        return true;
                    }
                });

                webView.requestFocus();
            }
        });


    }

    private void init() {
        editText.setText(myUrl);
        webView.loadUrl(myUrl);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                // 返回true则表明使用的是WebView
                return true;
            }
        });

        webView.requestFocus();
    }
}
