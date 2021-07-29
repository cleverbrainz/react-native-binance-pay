package com.reactnativebinancepay;

import androidx.annotation.NonNull;

import com.binance.android.binancepay.api.BinancePay;
import com.binance.android.binancepay.api.BinancePayException;
import com.binance.android.binancepay.api.BinancePayFactory;
import com.binance.android.binancepay.api.BinancePayListener;
import com.binance.android.binancepay.api.BinancePayParam;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.annotations.ReactProp;

@ReactModule(name = BinancePayModule.NAME)
public class BinancePayModule extends ReactContextBaseJavaModule {
    public static final String NAME = "BinancePay";

    BinancePay binancePay;
    BinancePayParam binancePayParam;

    public BinancePayModule(ReactApplicationContext reactContext) {
        super(reactContext);
        binancePay = new BinancePayFactory.Companion().getBinancePay(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void initBinancePayParam(String merchantId, String prepayId, String timeStamp, String nonceStr, String certSn, String sign) {
      binancePayParam = new BinancePayParam(merchantId, prepayId, timeStamp, nonceStr, certSn, sign);
    }

    @ReactMethod
    public void makePayment(Promise promise) {
      binancePay.pay(binancePayParam, new BinancePayListener() {
        @Override
        public void onSuccess() {
          promise.resolve("success");
        }

        @Override
        public void onCancel() {
          promise.resolve("cancel");
        }

        @Override
        public void onError(BinancePayException e) {
          promise.resolve(e.getLocalizedMessage());
        }
      });
    }

    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void multiply(int a, int b, Promise promise) {
        promise.resolve(a * b);
    }

    public static native int nativeMultiply(int a, int b);
}
