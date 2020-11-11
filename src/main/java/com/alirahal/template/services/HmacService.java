package com.alirahal.template.services;

import com.alirahal.template.utils.KeyUtils;
import lombok.Getter;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.io.IOException;

import static com.alirahal.template.utils.Utils.hexToBase64;

public class HmacService {

    private HmacUtils hmacUtils;

    @Getter
    private static HmacService instance = null;

    private HmacService(String filename) {
        String key = null;
        try {
            key = KeyUtils.getKey(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, key);
    }

    public static HmacService getInstance() {
        if (instance == null) {
            instance = new HmacService("/security/public.pem");
        }
        return instance;
    }

    public String signHex(String data) {
        return hmacUtils.hmacHex(data);
    }

    public String sign(String data) {
        return hexToBase64(hmacUtils.hmacHex(data));
    }
}
