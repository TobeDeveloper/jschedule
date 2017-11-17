package org.myan.jschedule.web;

import org.myan.log.annotation.MethodLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
@Controller
public class BasicController {

    @RequestMapping("/")
    @MethodLog
    public String index() {
        return "Index";
    }
}
