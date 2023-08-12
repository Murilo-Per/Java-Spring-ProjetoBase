package br.com.muriloper.api.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {

    private LocalDateTime localDateTime;
    private Integer status;
    private String error;
    private String path;
}
