package JpaHibernate.Burger.exceptions;

import JpaHibernate.Burger.entity.Burger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice //Ana middleware
public class GlobalExceptionHandler {

    @ExceptionHandler //KENDİ YAZDIĞIMIZ METHODLARDAKİ HATALARLA BU BÖLÜM İLGİLENİR
    public ResponseEntity handleMappingException(MethodArgumentNotValidException exception){
        List errorList = exception.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            return errorMap;
        }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler //BU BÖLÜMSE BİZİM YAZDIKLARIMIZ DIŞINDAKİ GLOBAL DÜZEYDEKİ HATALARLA İLGİLENİR
    public ResponseEntity<BurgerErrorResponse> handleException(Exception exception){
        BurgerErrorResponse response = new BurgerErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}


