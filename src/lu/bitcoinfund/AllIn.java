package lu.bitcoinfund;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.*;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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

        /*

        (HMAC SHA256)
        [linux]$ curl -H "X-MBX-APIKEY: vmPUZE6mv9SD5VNHk4HlWFsOr6aKE2zvsw0MuIgwCIPy6utIco14y7Ju91duEh8A"
        -X POST 'https://api.binance.com/api/v3/order?symbol=LTCBTC&side=BUY&type=LIMIT&timeInForce=GTC'
        -d 'quantity=1&price=0.1&recvWindow=6000000&timestamp=1499827319559&signature=0fd168b8ddb4876a0358a8d14d0c9f3da0e9b20c5d52b2a00fcf7d1c602f9a77'


        */

        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.binance.com")
                    .setPath("/api/v3/order")
                    .setParameter("symbol", "LTCBTC")
                    .setParameter("side", "BUY")
                    .setParameter("type", "LIMIT")
                    .setParameter("timeInForce", "GTC")
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpPost httppost = new HttpPost(uri);
        httppost.addHeader("X-MBX-APIKEY",apiKey);

        System.out.println(httppost.getURI());
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("quantity", "1"));
        params.add(new BasicNameValuePair("price", "0.1"));
        params.add(new BasicNameValuePair("recvWindow", "6000000"));
        params.add(new BasicNameValuePair("timestamp",Long.toString(unixTime)));
        params.add(new BasicNameValuePair("signature",hashSignature));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(httppost.toString());
        HttpClient httpclient = new DefaultHttpClient();
        //Execute and get the response.
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            InputStream instream = null;
            try {
                instream = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Response from server:"+EntityUtils.toString(entity));
                // do something useful
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    instream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
