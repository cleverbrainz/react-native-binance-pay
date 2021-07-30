import { NativeModules } from 'react-native';

type BinancePayType = {
  multiply(a: number, b: number): Promise<number>;
  initBinancePayParam(
    merchantId: string,
    prepayId: string,
    timeStamp: string,
    nonceStr: string,
    certSn: string,
    sign: string
  ): any;
  makePayment(): Promise<string>;
};

const { BinancePay } = NativeModules;

export default BinancePay as BinancePayType;
