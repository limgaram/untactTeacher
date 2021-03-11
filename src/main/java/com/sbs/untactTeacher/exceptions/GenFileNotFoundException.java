package com.sbs.untactTeacher.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "genFile not Found")
public class GenFileNotFoundException extends RuntimeException {

}
