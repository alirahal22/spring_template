package com.alirahal.template.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public interface HasLogger {
    default Log getLogger() {
        return LogFactory.getLog(this.getClass());
    }
}