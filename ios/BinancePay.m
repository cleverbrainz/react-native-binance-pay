#import <React/RCTBridgeModule.h>
#import <BinancePaySDK/BinancePaySDK.h>

OrderInitParameters * parameters;

@interface RCT_EXTERN_MODULE(BinancePay, NSObject)

RCT_EXTERN_METHOD(multiply:(float)a withB:(float)b
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(initBinancePayParam:(NSString *)merchantId (NSString *)prepayId (NSString *)timeStamp (NSString *)nonceStr (NSString *)certSn (NSString *)sign)

@end
