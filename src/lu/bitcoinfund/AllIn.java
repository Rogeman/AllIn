package lu.bitcoinfund;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * Created by pc on 18.01.2018.
 */
public class AllIn {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        //example apiKey vmPUZE6mv9SD5VNHk4HlWFsOr6aKE2zvsw0MuIgwCIPy6utIco14y7Ju91duEh8A
        //example secretKey NhqPtmdSJYdKjVHjA7PZj4Mge3R5YNiP1e3UZjInClVN65XAbvqqM6A7H5fATj0j
        final String secretKey = new String("NhqPtmdSJYdKjVHjA7PZj4Mge3R5YNiP1e3UZjInClVN65XAbvqqM6A7H5fATj0j");
        final String apiKey = new String("vmPUZE6mv9SD5VNHk4HlWFsOr6aKE2zvsw0MuIgwCIPy6utIco14y7Ju91duEh8A");

        System.out.println("All In!");
        //[linux]$ echo -n "symbol=LTCBTC&side=BUY&type=LIMIT&timeInForce=GTCquantity=1&price=0.1&recvWindow=5000&timestamp=1499827319559" |
        // openssl dgst -sha256 -hmac "NhqPtmdSJYdKjVHjA7PZj4Mge3R5YNiP1e3UZjInClVN65XAbvqqM6A7H5fATj0j"

        long unixTime = System.currentTimeMillis() / 1000L;
        String request = "symbol=LTCBTC&side=BUY&type=LIMIT&timeInForce=GTCquantity=1&price=0.1&recvWindow=5000&timestamp=" + unixTime;
        request = "symbol=LTCBTC&side=BUY&type=LIMIT&timeInForce=GTC&quantity=1&price=0.1&recvWindow=5000&timestamp=1499827319559";
        System.out.println(request);
        String hashSignature = HashSigner.sign(secretKey, request);

    }
}
