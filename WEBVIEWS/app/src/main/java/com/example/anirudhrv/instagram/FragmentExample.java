package com.example.anirudhrv.instagram;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FragmentExample extends Fragment {

    public WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v=inflater.inflate(R.layout.activity_display_web, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview);
        mWebView.loadUrl("http://bmscephaseshift.com/events.php");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }

        public void view(View v){
            // Add the code that you want
            // Or do nothing if you want
        }
}