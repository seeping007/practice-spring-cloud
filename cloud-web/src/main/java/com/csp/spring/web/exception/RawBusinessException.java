package com.csp.spring.web.exception;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * message作为body输出.
 *
 * @author chensiping
 * @since 2022-12-08
 */
public class RawBusinessException extends BusinessException {
    
    private final String contentType;
    
    private final Map<String, Collection<String>> responseHeaders;
    
    public RawBusinessException(int httpCode, String contentType, Map<String, Collection<String>> responseHeaders,
            String message) {
        super(httpCode, null, message);
        this.contentType = contentType;
        this.responseHeaders = responseHeaders;
    }
    
    public RawBusinessException(int httpCode, String message) {
        super(httpCode, null, message);
        contentType = null;
        responseHeaders = Collections.emptyMap();
    }
    
    public String getContentType() {
        return contentType;
    }
    
    public Map<String, Collection<String>> getResponseHeaders() {
        return responseHeaders;
    }
}
