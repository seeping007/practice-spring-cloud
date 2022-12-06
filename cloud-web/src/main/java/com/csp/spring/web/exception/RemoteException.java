package com.csp.spring.web.exception;

import java.util.Collection;
import java.util.Map;

/**
 * Remote call exception
 * <p>
 * http status: default 500
 *
 * @author chensiping
 * @since 2020-05-15
 */
public class RemoteException extends BusinessException {

    private Map<String, Collection<String>> headers;
    
    public RemoteException(String message) {
        this("REMOTE_ERROR", message);
    }

    public RemoteException(String type, String message) {
        super(500, type, message);
    }
    
    public RemoteException(int httpCode, String type, String message) {
        super(httpCode, type, message);
    }
    
    public RemoteException(int httpCode, String type, String message, Map<String, Collection<String>> headers) {
        super(httpCode, type, message);
        this.headers = headers;
    }
    
    public Map<String, Collection<String>> getHeaders() {
        return headers;
    }
}
