package payment.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import payment.entity.Order;

import java.util.List;

public class ResponseContext {

    private String url;
    private String errorMessage;
    private HttpStatus statusCode;

    public ResponseContext(String errorMessage, HttpStatus statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public ResponseContext(String url, String errorMessage, HttpStatus statusCode) {
        this.url = url;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

}
