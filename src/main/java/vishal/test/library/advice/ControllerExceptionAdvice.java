package vishal.test.library.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vishal.test.library.exception.BookNotFoundException;
import vishal.test.library.exception.BorrowingException;
import vishal.test.library.exception.PatronNotFoundException;
import vishal.test.library.util.ResponsePayload;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponsePayload<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ResponsePayload<>(null, String.valueOf(Arrays.stream(Objects.requireNonNull(methodArgumentNotValidException.getDetailMessageArguments())).map(String::valueOf).toList()));
    }

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponsePayload<Object> bookNotFoundExceptionHandler(BookNotFoundException bookNotFoundException) {
        return new ResponsePayload<>(null, bookNotFoundException.getMsg());
    }

    @ExceptionHandler(value = PatronNotFoundException.class)
    public ResponsePayload<Object> patronNotFoundExceptionHandler(PatronNotFoundException patronNotFoundException) {
        return new ResponsePayload<>(null, patronNotFoundException.getMsg());
    }

    @ExceptionHandler(value = BorrowingException.class)
    public ResponsePayload<Object> borrowingExceptionHandler(BorrowingException borrowingException) {
        return new ResponsePayload<>(null, borrowingException.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponsePayload<Object> genericExceptionHandler(Exception exp) {
        return new ResponsePayload<>(null, exp.getMessage());
    }
}
