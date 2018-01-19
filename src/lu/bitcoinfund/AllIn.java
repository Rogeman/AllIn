package lu.bitcoinfund;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Roge on 18.01.2018.
 */
public class AllIn {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        //example apiKey vmPUZE6mv9SD5VNHk4HlWFsOr6aKE2zvsw0MuIgwCIPy6utIco14y7Ju91duEh8A
        //example secretKey NhqPtmdSJYdKjVHjA7PZj4Mge3R5YNiP1e3UZjInClVN65XAbvqqM6A7H5fATj0j
        final String secretKey = new String("NhqPtmdSJYdKjVHjA7PZj4Mge3R5YNiP1e3UZjInClVN65XAbvqqM6A7H5fATj0j");
        final String apiKey = new String("vmPUZE6mv9SD5VNHk4HlWFsOr6aKE2zvsw0MuIgwCIPy6utIco14y7Ju91duEh8A");

        System.out.println("All In!");
        long unixTime = System.currentTimeMillis() / 1000L;
        String request = "symbol=LTCBTC&side=BUY&type=LIMIT&timeInForce=GTCquantity=1&price=0.1&recvWindow=5000&timestamp=" + unixTime;
        System.out.println(request);
        String hashSignature = HashSigner.sign(secretKey, request);

        /*(HMAC SHA256)
        [linux]$ curl -H "X-MBX-APIKEY: vmPUZE6mv9SD5VNHk4HlWFsOr6aKE2zvsw0MuIgwCIPy6utIco14y7Ju91duEh8A"
        -X POST 'https://api.binance.com/api/v3/order?symbol=LTCBTC&side=BUY&type=LIMIT&timeInForce=GTC'
        -d 'quantity=1&price=0.1&recvWindow=6000000&timestamp=1499827319559&signature=0fd168b8ddb4876a0358a8d14d0c9f3da0e9b20c5d52b2a00fcf7d1c602f9a77'
        */


    }
}
