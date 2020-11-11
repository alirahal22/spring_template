package com.alirahal.template.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class Utils {

    public static String hexToBase64(String hex) {
        char[] charArray = hex.toCharArray();
        byte[] decodedHex = new byte[0];
        try {
            decodedHex = Hex.decodeHex(charArray);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        String result = Base64.encodeBase64String(decodedHex);
        return result;
    }
}
