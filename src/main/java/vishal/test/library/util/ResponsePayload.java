package vishal.test.library.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponsePayload<T> {

    private T body;
    private Status status;
    private String failureMessage;

    public ResponsePayload(T body, String failureMessage) {
        this.body = body;
        this.failureMessage = failureMessage;
        if (null == failureMessage || failureMessage.isBlank()) {
            this.status = Status.SUCCESS;
        } else {
            this.status = Status.FAILURE;
        }
    }

    public enum Status {
        SUCCESS, FAILURE
    }
}
