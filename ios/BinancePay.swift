@objc(BinancePay)
class BinancePay: NSObject {

    @objc(multiply:withB:withResolver:withRejecter:)
    func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        resolve(a*b)
    }
    
   @objc(initBinancePayParam:)
   func initBinancePayParam(merchantId: NSString, prepayId: NSString, timeStamp: NSString, nonceStr: NSString, certSn: NSString, sign: NSString) -> Void {
       self.parameters = OrderInitParameters(merchantId: merchantId, prepayId: prepayId, timestamp: timeStamp, noncestr: nonceStr, certSn: certSn, sign: sign, redirectScheme: nil)
   }
}
