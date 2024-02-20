package com.jacksspring.dscommerce.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public class ValidationError extends CustomError{

  //  private List<FieldMessage> errors = new ArrayList<>();
    private Map<String, String> errors;

    public ValidationError(Instant timestamp, Integer status, String error, String path, BindingResult result) {
        super(timestamp, status, error, path);
        addErrors(result);
    }


/*
    public List<FieldMessage> getErrors() {
        return errors;
    }
*/
    public   void addErrors(BindingResult result){
        this.errors = new HashMap<>();

        for(FieldError fildError : result.getFieldErrors()){
            this.errors.put(fildError.getField(), fildError.getDefaultMessage());
        }
    }
}
