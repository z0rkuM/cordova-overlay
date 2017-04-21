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

import android.provider.Settings;

public class OverlayShowingService extends Service implements OnClickListener {
    
    private View topLeftView;

    private Button overlayedButton;
    private float offsetX;
    private float offsetY;
    private int originalXPos;
    private int originalYPos;
    private boolean moving;
    private WindowManager wm;

    @Override
    public IBinder onBind(Intent intent) {
	return null;
    }

    @Override
    public void onCreate() {
	super.onCreate();

	wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

	overlayedButton = new Button(this);
	overlayedButton.setText("Overlay button");
	//overlayedButton.setOnTouchListener(this);
	overlayedButton.setBackgroundColor(Color.BLACK);
	overlayedButton.setOnClickListener(this);

	WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
	params.gravity = Gravity.LEFT | Gravity.TOP;
	params.x = 0;
	params.y = 0;
	wm.addView(overlayedButton, params);

	topLeftView = new View(this);
	WindowManager.LayoutParams topLeftParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
	topLeftParams.gravity = Gravity.LEFT | Gravity.TOP;
	topLeftParams.x = 0;
	topLeftParams.y = 0;
	topLeftParams.width = 0;
	topLeftParams.height = 0;
	wm.addView(topLeftView, topLeftParams);
	Toast.makeText(this, "Now go to Hearthstone", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
	super.onDestroy();
	if (overlayedButton != null) {
	    wm.removeView(overlayedButton);
	    wm.removeView(topLeftView);
	    overlayedButton = null;
	    topLeftView = null;
	}
    }
    @Override
    public void onClick(View v) {
	Toast.makeText(this, "Overlay button click event", Toast.LENGTH_SHORT).show();
    }
}