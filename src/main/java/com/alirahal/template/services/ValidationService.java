package com.alirahal.template.services;

import com.alirahal.template.error.exceptions.ValidationException;
import com.alirahal.template.utils.HasLogger;
import com.alirahal.template.utils.KeyUtils;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;


public class ValidationService implements HasLogger {

    private HmacUtils hmacUtils;

    public ValidationService() {
        //TODO Use this key for JWT validation and creation.
        String key = null;
        try {
            key = KeyUtils.getKey("/security/public.pem");
        } catch (IOException e) {
            e.printStackTrace();
        }
        hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, "a4cd1a43-a7f9-4f5d-86cf-8cbad96842fd");
    }

    public String getFullUrl(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder(request.getRequestURI());
        if (request.getQueryString() != null)
            builder.append("?").append(request.getQueryString());
        return builder.toString();
    }

    public void validateSignature(HttpServletRequest request) throws ValidationException, IOException {

        String requestMethod = request.getMethod();
        String requestTimestamp = request.getHeader("timestamp");
        String requestSignature = request.getHeader("Signature");
        String requestFullUrl = getFullUrl(request);

        InputStream inputStream = request.getInputStream();
        byte[] bodyBytes = StreamUtils.copyToByteArray(inputStream);
        String requestBody = new String(bodyBytes);
        long currentTimestamp = new Date().getTime();
        if (requestTimestamp == null) {
            throw new ValidationException("Missing timestamp in request");
        }
        if (currentTimestamp - Long.parseLong(requestTimestamp) > 10000) {
            throw new ValidationException("Expired signature");
        }


        StringBuilder builder = new StringBuilder();
        builder.append(requestMethod.trim().toLowerCase());
        if (StringUtils.isNotBlank(requestFullUrl))
            builder.append(requestFullUrl.trim().toLowerCase());
        if (StringUtils.isNotBlank(requestBody)) {
            builder.append(requestBody.trim().toLowerCase().replace("\"", ""));
        }
        builder.append(requestTimestamp);

        String signatureBody = builder.toString().toLowerCase().trim();
        getLogger().info("Hashing the value of: " + signatureBody);

        String validSignature = Base64.getEncoder().encodeToString(hmacUtils.hmac(signatureBody));
        getLogger().info("Hash result:  " + validSignature);

        if (!Objects.equals(validSignature, requestSignature)) {
            throw new ValidationException();
        }
    }


}
