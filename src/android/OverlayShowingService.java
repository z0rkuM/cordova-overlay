package es.spaike.cordova.overlay;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Service;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Color;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import android.provider.Settings;

public class OverlayShowingService extends Service implements OnClickListener {
    
	private static final String TAG = "OverlayShowingService";
    private View topLeftView;

    private Button overlayedButton;
    private float offsetX;
    private float offsetY;
    private int originalXPos;
    private int originalYPos;
    private boolean moving;
    private WindowManager wm;
	private WebView myWebView;

    @Override
    public IBinder onBind(Intent intent) {
	return null;
    }

	@Override
	public void onCreate() {
		super.onCreate();
		
		try {
			Log.i(TAG, "LOL event llega");

			wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

			RelativeLayout ll = new RelativeLayout(this);
			ll.setBackgroundColor(Color.CYAN);

			
			overlayedButton = new Button(this);
			overlayedButton.setText("Hide");
			//overlayedButton.setOnTouchListener(this);
			overlayedButton.setBackgroundColor(Color.BLACK);
			overlayedButton.setOnClickListener(this);
			RelativeLayout.LayoutParams LLParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			LLParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			//WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
			//params.gravity = Gravity.LEFT | Gravity.TOP;
			//params.x = 0;
			//params.y = 0;


			myWebView = new WebView(this);
			String summary = "<html><head><meta name=\"viewport\" content=\"initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width\"></head><body><table><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr><tr><td>1</td><td>Bichos</td><td>2</td></tr></table></body></html>";
			myWebView.loadData(summary, "text/html", null);
			
			ll.addView(myWebView);
			ll.addView(overlayedButton, LLParams);
			
			WindowManager.LayoutParams topLeftParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
			topLeftParams.gravity = Gravity.LEFT | Gravity.TOP;
			topLeftParams.x = 0;
			topLeftParams.y = 0;
			topLeftParams.width = 100;
			topLeftParams.height = 500;
			wm.addView(ll, topLeftParams);
			//Toast.makeText(this, "Now you can open Hearthstone", Toast.LENGTH_SHORT).show();
		}
		catch (Exception ex) {
			Log.e(TAG, "LOL error", ex);
		}
    }

    @Override
    public void onDestroy() {
		super.onDestroy();
		finishOverlay();
    }
	
    @Override
    public void onClick(View v) {
		finishOverlay();
    }
	
	public void finishOverlay() {
		if (overlayedButton != null) {
			wm.removeView(overlayedButton);
			overlayedButton = null;
			topLeftView = null;
		}
	}
}