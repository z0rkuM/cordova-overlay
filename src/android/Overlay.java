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

public class Overlay extends CordovaPlugin {
    public static final String TAG = "Overlay";

    public Overlay() {
    }


    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }


    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
		try{
			final Activity thisActitity = this.cordova.getActivity();
			Intent svc = new Intent(thisActitity, es.spaike.cordova.overlay.OverlayShowingService.class);
			thisActitity.startService(svc);
			callbackContext.success("OK"); // Thread-safe.
		}
		catch (Exception ex) {
			callbackContext.success(ex.getMessage());
		}
        return true;
    }
}