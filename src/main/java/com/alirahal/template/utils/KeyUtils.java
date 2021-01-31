package com.alirahal.template.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Logger;

public class KeyUtils {

    public static String getKey(String filename) throws IOException {
        InputStream in = KeyUtils.class.getResourceAsStream(filename);
        String key = new String(in.readAllBytes(), Charset.defaultCharset());
        Logger.getAnonymousLogger().info("\n" + key);
        return key;
    }

    public static RSAPrivateKey readPrivateKey(String filename) throws Exception {
        String key = getKey(filename);
        String privateKeyPEM = key.replace("-----BEGIN PRIVATE KEY-----", "").replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.decodeBase64(privateKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}
