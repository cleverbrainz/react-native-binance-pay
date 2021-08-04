package com.reactnativebinancepay;

import androidx.annotation.NonNull;

import com.binance.android.binancepay.api.BinancePay;
import com.binance.android.binancepay.api.BinancePayException;
import com.binance.android.binancepay.api.BinancePayFactory;
import com.binance.android.binancepay.api.BinancePayListener;
import com.binance.android.binancepay.api.BinancePayParam;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

//  // Create an order to pay
//  @ReactMethod
//  public void createAnOrder() {
//    String payload = "1627931807000" + "\n" +
//      "5K8264ILTKCH16CQ2502SI8ZNMTM67VS" + "\n" +
//      "{" +
//      "\"merchantId\": \"191977343\"," +
//      "\"merchantTradeNo\": \"9825384147292\"," +
//      "\"totalFee\": 25.17," +
//      "\"productDetail\": \"Greentea ice cream cone\"," +
//      "\"currency\": \"EUR\"," +
//      "\"returnUrl\": \"\"," +
//      "\"tradeType\": \"APP\"," +
//      "\"productType\": \"Food\"," +
//      "\"productName\": \"Ice Cream\"" +
//      "}" + "\n";
//
//    String signature = hex(hmac("sha512", payload.getBytes(), "LZLzMvweGjuZEkJKIvam9D43rNg6ICQ2XBoSz4xzbFhSDYYv8uNxHZ8QvL3W0WNn".getBytes())).toUpperCase();
//  }

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

  byte[] hmac(String algorithm,  byte[] message, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac mac = Mac.getInstance(algorithm);
    mac.init(new SecretKeySpec(key, algorithm));
    return mac.doFinal(message);
  }
}
