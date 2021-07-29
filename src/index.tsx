import { NativeModules } from 'react-native';

type BinancePayType = {
  multiply(a: number, b: number): Promise<number>;
};

const { BinancePay } = NativeModules;

export default BinancePay as BinancePayType;
