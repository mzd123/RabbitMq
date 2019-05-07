package com.mzd.test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 对微信小程序用户加密数据的解密示例代码.
 *
 * @author Awoke
 * @version 2018-1-24
 * @see WXBizDataCrypt
 * @since
 */
public class WXBizDataCrypt {

    private String appid;

    private String sessionKey;

    public WXBizDataCrypt(String appid, String sessionKey) {
        this.appid = appid;
        this.sessionKey = sessionKey;
    }

    /**
     * 检验数据的真实性，并且获取解密后的明文.
     *
     * @param encryptedData string 加密的用户数据
     * @param iv            string 与用户数据一同返回的初始向量
     * @return data string 解密后的原文
     */
    public String decryptData(String encryptedData, String iv) {
        byte[] aesKey = Base64.decodeBase64(sessionKey);

        byte[] aesIV = Base64.decodeBase64(iv);

        byte[] aesCipher = Base64.decodeBase64(encryptedData);

        Map<String, String> map = new HashMap<>();

        try {
            byte[] resultByte = AESUtils.decrypt(aesCipher, aesKey, aesIV);

            if (null != resultByte && resultByte.length > 0) {
                String userInfo = new String(resultByte, "UTF-8");
                map.put("code", "0000");
                map.put("msg", "succeed");
                map.put("userInfo", userInfo);

                JSONObject jsons = JSON.parseObject(userInfo);
            } else {
                map.put("status", "1000");
                map.put("msg", "false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    /**
     * @param args
     * @see
     */
    public static void main(String[] args) {
        String appId = "wx63677eb00d827375";
        //m3xNRIOTZbNJqsckMNw9xg==
        //w1p3IHMLGOT9TyWVRcVSRA==
        String sessionKey = "gkPQySR86DyYV55aswGUqw==";
        String encryptedData = "OhCJ4rUxzXMfPdk5frAIkf77jBoCZBsiCk1rlqg7it4XGmuE5GXaRSIFTCZ2uttwnr9wsU8cfuB6baY509ZdDKVazn3ifgwFgTqKG1QBBoDOcGCDNDpbeUKLyREPRU4WqAdeXuRn+FW5RhtfywmroogDlW9njD3rrBx6GUVpzRSF9H+IlhPpmTegEQz86CM/7VA5QtWBMS5MJmqvwo3haxXy2vtjVRJTfcmyoHJdMWUBKyUoFAP5/2YIh1KFnAJqf3Jf4dof1GGFmoElEwuM9P8qa6cn+M6lgOvsm5a60eRrGkJYWgkOP8qXMaWnrtelOPFnAxSVtkWrMfTTHoNgRfskAKojaF/ub1Df0aRQNJ3BKVJFL29l7XRM4MJaEvBD4odIHwpwbjpSQx7md8P1jWG1VbzlPc1dVPM5zkgpK5CfdqAoK1T3Eb3cpqFmGaeg1HWbLHlTXiuz2s0UydfBg2C7/5/r8SQCR0Xrwq0e5aI=";
        String iv = "nhSxkdTu28cd2rqQ1s06bQ==";

        WXBizDataCrypt biz = new WXBizDataCrypt(appId, sessionKey);

        System.out.println(biz.decryptData(encryptedData, iv));

    }
}