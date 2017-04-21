package es.spaike.cordova.overlay;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
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

public class Overlay extends CordovaPlugin {
    public static final String TAG = "Overlay";

    public Overlay() {
    }


    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }


    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                WindowManager wm = (WindowManager) this.cordova.getActivity().getSystemService(Context.WINDOW_SERVICE);

				Button overlayedButton = new Button(this);
				overlayedButton.setText(args.getString(0));
				overlayedButton.setOnTouchListener(this);
				overlayedButton.setAlpha(0.0f);
				overlayedButton.setBackgroundColor(0x55fe4444);
				overlayedButton.setOnClickListener(this);

				WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
				params.gravity = Gravity.LEFT | Gravity.TOP;
				params.x = 0;
				params.y = 0;
				wm.addView(overlayedButton, params);

				View topLeftView = new View(this);
				WindowManager.LayoutParams topLeftParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
				topLeftParams.gravity = Gravity.LEFT | Gravity.TOP;
				topLeftParams.x = 0;
				topLeftParams.y = 0;
				topLeftParams.width = 0;
				topLeftParams.height = 0;
				wm.addView(topLeftView, topLeftParams);
                callbackContext.success(); // Thread-safe.
            }
        });
        return true;
    }
}