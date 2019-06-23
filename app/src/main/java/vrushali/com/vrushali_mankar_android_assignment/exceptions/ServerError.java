package vrushali.com.vrushali_mankar_android_assignment.exceptions;

import java.io.IOException;

public class ServerError extends IOException {
    private int httpStatusCode;
    private String httpResponse;

    public ServerError(int httpStatusCode, String httpResponse) {
        super(httpResponse);
        this.httpStatusCode = httpStatusCode;
        this.httpResponse = httpResponse;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getHttpResponse() {
        return httpResponse;
    }
}
