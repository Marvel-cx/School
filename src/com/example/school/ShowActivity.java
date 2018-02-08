package com.example.school;
import android.annotation.SuppressLint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
public class ShowActivity extends Activity {
	private WebView mWebView;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		mWebView = (WebView) findViewById(R.id.Toweb);
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
		settings.setLoadWithOverviewMode(true);
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		settings.setAppCacheEnabled(true);		
		settings.setLoadsImagesAutomatically(true);
		
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		mWebView.getSettings().setAppCacheEnabled(true);
		mWebView.getSettings().setDatabaseEnabled(true);  
		mWebView.getSettings().setDomStorageEnabled(true);  
		mWebView.getSettings().setSupportZoom(false);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.getSettings().setDefaultFontSize(18);
		//js和activity交互，使用WebView的方法
        mWebView.addJavascriptInterface(this,"javatojs");
		mWebView.loadUrl("file:///android_asset/map.html");
		
		mWebView.setWebViewClient(new WebViewClient() {
			
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				
				return true;
			}
			
		});				
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			showTips();

			return false;
		}

		return super.onKeyDown(keyCode, event);
	}
	
	
	private void exit() {
		// handlerre.removeCallbacksAndMessages(null);
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	// 按返回键的时候提示退出
	private void showTips() {
		
		try {

			AlertDialog alertDialog = new AlertDialog.Builder(this)
					.setTitle("退出程序")
					.setMessage("确定退出吗？")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// 资源释放干净
									exit();

								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									return;
								}
							}).create(); // 创建对话框
			alertDialog.show(); // 显示对话框
			
		} catch (Exception e) {
			
		}
	}
}