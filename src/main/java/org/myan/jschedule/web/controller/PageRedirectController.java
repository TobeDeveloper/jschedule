package org.myan.jschedule.web.controller;

import org.myan.log.annotation.MethodLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
@Controller
public class PageRedirectController extends BaseController {

    @RequestMapping("/")
    @MethodLog
    public String index() {
        return "Index";
    }
}
