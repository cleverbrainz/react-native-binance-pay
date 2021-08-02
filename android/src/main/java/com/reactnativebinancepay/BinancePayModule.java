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

  // Init Binance Payment Parameters
  @ReactMethod
  public void initBinancePayParam(String merchantId, String prepayId, String timeStamp, String nonceStr, String certSn, String sign) {
    binancePayParam = new BinancePayParam(merchantId, prepayId, timeStamp, nonceStr, certSn, sign);
  }

  // Make Binance Payment Pay
  @ReactMethod
  public void makePayment(Promise promise) {
    try {
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
          promise.reject("Binance Pay Failed", e);
        }
      });
    } catch (Exception e) {
      promise.reject("Binance Pay Failed", e);
    }
  }
}
