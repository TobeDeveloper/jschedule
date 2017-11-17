package org.myan.jschedule.web.controller;

import org.myan.jschedule.web.data.HttpResponse;
import org.myan.jschedule.web.data.Response;
import org.myan.jschedule.web.exception.ControllerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
public class BaseController {

    @ExceptionHandler({ControllerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response exceptionHandler(ControllerException e) {
        Response response = new HttpResponse();
        response.setResponseCode(e.getErrorCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }

}
