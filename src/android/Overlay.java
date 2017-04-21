package es.spaike.cordova.overlay;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.provider.Settings;

public class Overlay extends CordovaPlugin {
    public static final String TAG = "Overlay";

    public Device() {
    }


    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Device.uuid = getUuid();
    }


    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return true;
    }
}