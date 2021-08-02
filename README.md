# react-native-binance-pay

React Native Package for Binance Pay from Binance SDKs

## Installation

```sh
npm install react-native-binance-pay
```

### Add dependencies

#### Android dependencies

1. Open up `android/app/src/main/java/[...]/MainApplication.java`

- Add `import com.reactnativebinancepay.BinancePayPackage;` to the imports at the top of the file
- Add `new BinancePayPackage()` to the list returned by the `getPackages()` method

2. Append the following lines to `android/settings.gradle`:
   ```
    include ':react-native-binance-pay'
    project(':react-native-binance-pay').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-binance-pay/android')
   ```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
   ```
    implementation project(':react-native-binance-pay')
   ```
4. Insert the following lines inside the android block in `android/app/build.gradle`:
   ```
    repositories {
        flatDir {
            dirs 'libs'
        }
    }
   ```
5. Download the binance_pay_sdk_V1.0.1.aar file from [here](https://github.com/cleverbrainz/react-native-binance-pay/blob/master/android/libs/binance_pay_sdk_v1.0.1.aar) and paste to `android/app/libs/` folder

#### iOS dependencies


## Usage

```js
import BinancePay from "react-native-binance-pay";

// ...

BinancePay.initBinancePayParam(merchantId, prepayId, timeStamp, nonceStr, certSn, sign);
BinancePay.makePayment()
.then((result) => {
    console.log(result);
});
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
