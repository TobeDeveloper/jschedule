package org.myan.jschedule.web.controller;

import org.myan.jschedule.web.data.DefaultResponseEntry;
import org.myan.jschedule.web.data.HttpResponse;
import org.myan.jschedule.web.data.Response;
import org.myan.jschedule.web.entity.UserEntity;
import org.myan.jschedule.web.exception.ControllerException;
import org.myan.jschedule.web.exception.ErrorMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @RequestMapping("/{id}")
    public Response<UserEntity> getUser(@PathVariable("id") int id) throws Exception {
        Response<UserEntity> response;
        try {
            response = new HttpResponse<>();
            UserEntity user = new UserEntity();
            user.setId(id);
            response.putObject("user", user);
            return response;
        } catch (Exception e) {
            throw new ControllerException(ErrorMessage.NO_SUCH_USER);
        }
    }

    @RequestMapping("/get")
    public Response<String> testGet() {
        throw new ControllerException(ErrorMessage.EMPTY_RESULT, DefaultResponseEntry.list().setName("default"));
    }
}
