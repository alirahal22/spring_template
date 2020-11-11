package com.alirahal.template.utils;

import com.alirahal.template.services.HmacService;
import org.springframework.beans.factory.annotation.Autowired;

public class EncryptionUtils {


    public static String calculateSignature(String text) {
        try {
            String key = KeyUtils.getKey("/security/private.pem");
            String signature = HmacService.getInstance().sign(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return HmacService.getInstance().sign(text);
    }
}
