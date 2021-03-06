package noticeme.common.exception;

import noticeme.common.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

public class CommonExceptionController {
    private CommonExceptionController() {}

    public static ApiError handleNotFound(RuntimeException e) {
        return new ApiError(HttpStatus.NOT_FOUND, e.getMessage());
    }

    public static ApiError handleInvalidParam(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("\n"));

        return new ApiError(HttpStatus.BAD_REQUEST, message);
    }
}
